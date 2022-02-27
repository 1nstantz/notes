package com.qinhao.springboot.cicledi;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.AnnotationTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

/**
 * describ
 *
 * @author qinhao
 * @date 2022/2/26 - 17:45
 */
@Component
public class A {
    private B b;
    public A(){
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAA");
        TransactionInterceptor.currentTransactionStatus().setRollbackOnly();

    }
}
