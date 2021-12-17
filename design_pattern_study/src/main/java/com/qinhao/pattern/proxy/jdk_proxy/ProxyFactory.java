package com.qinhao.pattern.proxy.jdk_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @version v1.0
 * @ClassName: ProxyFactory
 * @Description: 获取代理对象的工厂类   代理是通过继承、实现被代理的类的父类和接口在内存中生成动态代理的类通过反射的方式进行方法的增强
 *      代理类也实现了对应的接口
 * @Author: qh
 */
public class ProxyFactory {

    //声明目标对象
    private TrainStation station = new TrainStation();

    //获取代理对象的方法
    public SellTickets getProxyObject() {
        //返回代理对象
        /*
            ClassLoader loader : 类加载器，用于加载代理类。可以通过目标对象获取类加载器
            Class<?>[] interfaces ： 代理类实现的接口的字节码对象
            InvocationHandler h ： 代理对象的调用处理程序
         */
        /*
            Object proxy : 代理对象。和proxyObject对象是同一个对象，在invoke方法中基本不用
            Method method ： 对接口中的方法进行封装的method对象
            Object[] args ： 调用方法的实际参数

            返回值： 方法的返回值。
         */
        SellTickets proxyObject = (SellTickets)Proxy.newProxyInstance(
                station.getClass().getClassLoader(),
                station.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    //System.out.println("invoke方法执行了");
                    System.out.println("代售点收取一定的服务费用(jdk动态代理)");
                    //执行目标对象的方法
                    Object obj = method.invoke(station, args);
                    return obj;
                }
        );
        return proxyObject;
    }
}
