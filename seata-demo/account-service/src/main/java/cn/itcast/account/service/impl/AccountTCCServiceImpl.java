package cn.itcast.account.service.impl;

import cn.itcast.account.entity.AccountFreeze;
import cn.itcast.account.mapper.AccountFreezeMapper;
import cn.itcast.account.mapper.AccountMapper;
import cn.itcast.account.service.AccountTCCService;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author qinhao
 * @date 2022/1/24 - 16:27
 */

@Slf4j
@Service
public class AccountTCCServiceImpl implements AccountTCCService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private AccountFreezeMapper accountFreezeMapper;

    @Override
    @Transactional
    public void deduct(String userId, int money) {
        String xid = RootContext.getXID();
        //判断cancel是否执行过，即freeze表有没有数据,防止业务悬挂
        AccountFreeze freeze = accountFreezeMapper.selectById(xid);
        if (freeze == null) {
            return;
        }

        //0.数据库money字段设置无服务，直接扣除为负数的时候直接报错回滚，所以不用判断扣减
        // 1.扣钱
        accountMapper.deduct(userId, money);
        //2.记录冻结金额以及事务状态
        AccountFreeze accountFreeze = new AccountFreeze();
        accountFreeze.setUserId(userId);
        accountFreeze.setFreezeMoney(money);
        accountFreeze.setState(AccountFreeze.State.TRY);
        accountFreeze.setXid(xid);
        accountFreezeMapper.insert(accountFreeze);
    }

    @Override
    public boolean confirm(BusinessActionContext context) {
        //获取事务Xid
        String xid = context.getXid();
        //删除冻结金额数据
        int i = accountFreezeMapper.deleteById(xid);

        return i==1;
    }

    @Override
    public boolean cancel(BusinessActionContext context) {
        String xid = context.getXid();
        AccountFreeze accountFreeze = accountFreezeMapper.selectById(xid);
        //如果try没有执行，没有冻结金额，添加一条冻结金额为0的数据
        if (accountFreeze == null) {
            accountFreeze = new AccountFreeze();
            accountFreeze.setUserId(context.getActionContext("userId").toString());
            accountFreeze.setFreezeMoney(0);
            accountFreeze.setState(AccountFreeze.State.CANCEL);
            accountFreeze.setXid(xid);
            accountFreezeMapper.insert(accountFreeze);
        }

        //已经处理过了
        if (accountFreeze.getState().equals(AccountFreeze.State.CANCEL)) {
            return true;
        }
        //1.恢复余额
        accountMapper.refund(accountFreeze.getUserId(), accountFreeze.getFreezeMoney());
        //2.冻结金额清0，设置事务状态为cancel
        accountFreeze.setFreezeMoney(0);
        accountFreeze.setState(AccountFreeze.State.CANCEL);
        int i = accountFreezeMapper.updateById(accountFreeze);
        //
        return i==1;
    }

}
