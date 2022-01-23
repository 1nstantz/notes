package top.daji.feign.clients.fallback;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import top.daji.feign.clients.UserClient;
import top.daji.feign.pojo.User;

/**
 * describ
 *
 * @author qinhao
 * @date 2022/1/23 - 20:53
 */
@Slf4j
public class UserClientFallbackFactory implements FallbackFactory<UserClient> {
    @Override
    public UserClient create(Throwable cause) {
        return new UserClient() {
            @Override
            public User findById(Long id) {
                log.error("查询用户异常");
                return new User();
            }
        };
    }
}
