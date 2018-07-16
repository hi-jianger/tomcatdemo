package com.jmj.http;


import java.io.IOException;
import java.io.InputStream;

/**
 * @author jianger
 * @Date 2018/7/16 下午2:33
 **/
public class Request {

    private InputStream is;

    private String url;

    public Request(InputStream is) {
        this.is = is;
    }

    public void parse() {

        StringBuffer request = new StringBuffer(Response.BUFFER_SIZE);
        int i;
        byte[] buffer = new byte[Response.BUFFER_SIZE];
        try {
            i = is.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            i = -1;
        }

        for (int j = 0; j < i; j++) {
            request.append((char) buffer[j]);
        }

        System.out.println(request.toString());
        url = parseUrl(request.toString());

    }

    public String parseByteToString(){
        StringBuffer request = new StringBuffer(Response.BUFFER_SIZE);
        int i;
        byte[] buffer = new byte[Response.BUFFER_SIZE];
        try {
            i = is.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            i = -1;
        }

        for (int j = 0; j < i; j++) {
            request.append((char) buffer[j]);
        }
        return request.toString();
    }


    private String parseUrl(String requestString) {
        int index1, index2;
        index1 = requestString.indexOf(' ');
        if (index1 != -1) {
            index2 = requestString.indexOf(' ', index1 + 1);
            if (index2 > index1) {
                return requestString.substring(index1 + 1, index2);
            }
        }
        return null;
    }


    public String getUrl() {
        return url;
    }
}
