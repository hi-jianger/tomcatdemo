package com.jmj.proxy.logproxy;

import com.jmj.proxy.log.MethodMonitor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author jianger
 * @Date 2018/7/19 下午4:12
 **/
public class LogProxy<T> implements InvocationHandler {
    Object target;

    public LogProxy(Object target) {
        this.target = target;
    }

    public T proxy() {
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MethodMonitor methodMonitor = new MethodMonitor(true);
        methodMonitor.begin(target, method, args);
        Object returnObject = null;
        try {
            returnObject = method.invoke(target, args);
        } catch (Exception e) {
            methodMonitor.exception(e);
            throw e;
        }
        methodMonitor.end(returnObject);
        return returnObject;
    }
}
