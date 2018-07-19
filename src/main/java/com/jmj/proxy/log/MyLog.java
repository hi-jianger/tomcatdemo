package com.jmj.proxy.log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author jianger
 * @Date 2018/7/19 下午3:02
 **/
public class MyLog {
    /**
     * log的id
     */
    private String logId;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 类名
     */
    private String className;
    /**
     * 方法参数
     */
    private Object[] argments;
    /**
     * 开始时间
     */
    private Long startTime;
    /**
     * 结束时间
     */
    private Long endTime;
    /**
     * 执行时间
     */
    private Long spendTime;
    /**
     * 返回值
     */
    private Object returnObject;
    /**
     * 异常
     */
    private String exception;

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getArgments() {
        if (null == argments || argments.length == 0) {
            return "null";
        }
        StringBuffer buffer = new StringBuffer("[ ");
        for (Object item : argments) {
            buffer.append(item.toString());
            buffer.append(" , ");
        }
        //将最后的 ， 去掉
        buffer = new StringBuffer(buffer.substring(0, buffer.length() - 3));
        buffer.append(" ]");
        return buffer.toString();

    }

    public void setArgments(Object[] argments) {
        this.argments = argments;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Long getSpendTime() {
        return spendTime;
    }

    public void setSpendTime(Long spendTime) {
        this.spendTime = spendTime;
    }

    public Object getReturnObject() {
        return returnObject;
    }

    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public MyLog() {
        this.logId = UUID.randomUUID().toString();
    }

    /**
     * 打印执行中的log日志
     */
    public void printExecutionLog() {
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        StringBuffer buffer = new StringBuffer();
        buffer.append("============================执行日志==================================\n");
        buffer.append(className + "." + methodName + "LOG [ID->" + logId + "] : " + "\n");
        buffer.append("参数：" + getArgments() + "\n");
        buffer.append("开始时间：" + format.format(new Date(startTime)) + "\n");
        buffer.append("======================================================================\n");
        System.out.println(buffer.toString());
    }

    /**
     * 打印全部的log日志
     */
    public void printFullLog() {
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        StringBuffer buffer = new StringBuffer();
        buffer.append("============================执行日志==================================\n");
        buffer.append(className + "." + methodName + "LOG [ID->" + logId + "] : " + "\n");
        buffer.append("参数：" + getArgments() + "\n");
        buffer.append("开始时间：" + format.format(new Date(startTime)) + "\n");
        buffer.append("======================================================================\n");
        buffer.append("结束时间：" + endTime + "\n");
        buffer.append("总耗时：" + spendTime + "\n");
        if (null!=exception&&!exception.equals("")){
            buffer.append("exception: \n"+exception+"\n");
        }else {
            buffer.append("返回值：" + returnObject + "\n");
        }
        System.out.println(buffer.toString());
    }
}
