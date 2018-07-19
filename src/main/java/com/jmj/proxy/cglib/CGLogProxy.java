package com.jmj.proxy.cglib;

import com.jmj.proxy.log.MethodMonitor;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author jianger
 * @Date 2018/7/19 下午4:33
 **/
public class CGLogProxy<T> implements MethodInterceptor {

    private Enhancer enhancer;

    public T getProxy(Class cls){
        enhancer=new Enhancer();
        enhancer.setSuperclass(cls);
        enhancer.setSuperclass(cls);
        enhancer.setCallback(this);
        return (T) enhancer.create();
    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        MethodMonitor methodMonitor=new MethodMonitor(true);
        methodMonitor.begin(o,method,objects);
        Object returningObj;
        try {
            returningObj=methodProxy.invokeSuper(o,objects);
        }catch (Exception e){
            methodMonitor.exception(e);
            throw e;
        }
        methodMonitor.end(returningObj);
        return returningObj;
    }
}
