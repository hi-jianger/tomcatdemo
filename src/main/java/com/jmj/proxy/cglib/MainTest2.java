package com.jmj.proxy.cglib;

import com.jmj.proxy.testmethod.TestService;
import com.jmj.proxy.testmethod.TestServiceImpl;

/**
 * @author jianger
 * @Date 2018/7/19 下午5:20
 **/
public class MainTest2 {
    public static void main(String[] args) {
        TestServiceImpl testService= (TestServiceImpl) new CGLogProxy<TestService>().getProxy(TestServiceImpl.class);
        testService.login("kok","lll");
    }


}
