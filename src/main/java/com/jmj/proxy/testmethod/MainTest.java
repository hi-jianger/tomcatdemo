package com.jmj.proxy.testmethod;

import com.jmj.proxy.logproxy.LogProxy;

/**
 * @author jianger
 * @Date 2018/7/19 下午4:27
 **/
public class MainTest {

    public static void main(String[] args) {
        TestService testService=new TestServiceImpl();
        LogProxy<TestService> logProxy=new LogProxy<TestService>(testService);
        testService=logProxy.proxy();
        testService.login("hello","world");
    }
}
