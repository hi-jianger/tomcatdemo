package com.jmj.proxy.log;

import java.lang.reflect.Method;

/**
 * @author jianger
 * @Date 2018/7/19 下午3:43
 **/
public class MethodMonitor {

    private MyLog myLog;
    private Boolean isPrintLog;

    public MethodMonitor(Boolean isPrintLog) {
        this.isPrintLog = isPrintLog;
    }

    /**
     * 日志开始
     * target相关类
     *
     * @return
     */
    public MyLog begin(Object target, Method method, Object[] args) {
        myLog = new MyLog();
        myLog.setArgments(args);
        myLog.setMethodName(method.getName());
        myLog.setClassName(target.getClass().getName());
        myLog.setStartTime(System.currentTimeMillis());
        if (isPrintLog) {
            myLog.printExecutionLog();
        }
        return myLog;
    }

    /**
     * 日志结束
     *
     * @param returnObject
     * @return
     */
    public MyLog end(Object returnObject) {
        myLog.setReturnObject(returnObject);
        myLog.setEndTime(System.currentTimeMillis());
        myLog.setSpendTime(myLog.getEndTime() - myLog.getStartTime());

        if (isPrintLog) {
            myLog.printFullLog();
        }
        return myLog;
    }

    /**
     * 异常打印
     *
     * @return
     */
    public MyLog exception(Exception e) {
        myLog.setException(e.getMessage());
        myLog.setEndTime(System.currentTimeMillis());
        myLog.setSpendTime(myLog.getEndTime() - myLog.getStartTime());
        if (isPrintLog) {
            myLog.printFullLog();
        }
        return myLog;
    }
}
