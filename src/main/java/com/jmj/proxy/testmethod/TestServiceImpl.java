package com.jmj.proxy.testmethod;

/**
 * @author jianger
 * @Date 2018/7/19 下午4:26
 **/
public class TestServiceImpl implements TestService {
    public Boolean login(String username, String password) {

        return true;
    }

    public Boolean register(String username, String password) {
        return null;
    }
}
