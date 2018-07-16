package com.jmj.http;

import java.io.*;

/**
 * @author jianger
 * @Date 2018/7/16 下午2:39
 **/
public class Response {

    public static final int BUFFER_SIZE = 2048;

    private static final String WEB_ROOT = "/Users/ginger/";
    private Request request;
    private OutputStream outputStream;

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void setStaicResource() {
        byte[] bytes = new byte[BUFFER_SIZE];
        FileInputStream fis = null;

        System.out.println(request.getUrl());

        File file = new File(WEB_ROOT, request.getUrl());
        try {
            if (file.exists() && !file.isDirectory()) {
                fis = new FileInputStream(file);
//                fis = new FileInputStream(file);
//                int ch = fis.read(bytes, 0, BUFFER_SIZE);
//                while (ch != -1) {
//                    outputStream.write(ch);
//                    ch = fis.read(bytes, 0, BUFFER_SIZE);
//                }
                StringBuffer request = new StringBuffer(Response.BUFFER_SIZE);
                int i;

                byte[] buffer = new byte[Response.BUFFER_SIZE];
                try {
                    i = fis.read(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                    i = -1;
                }

                for (int j = 0; j < i; j++) {
                    request.append((char) buffer[j]);
                }
                String rsult = request.toString();
                String returnMessage = "HTTP/1.1 200 SUCCESS\r\n" +
                        "Content-Type: text/html; charset=UTF-8\r\n" +
                        "Content-Length:" + rsult.length() + "\r\n" +
                        "\r\n" + rsult;

                outputStream.write(returnMessage.getBytes());

            } else {
                String retMessage = "<h1>" + file.getName() + " file or directory not exists </h1>";

                String returnMessage = "HTTP/1.1 404 File Not Found\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Content-Length:" + retMessage.length() + "\r\n" +
                        "\r\n" + retMessage;
                outputStream.write(returnMessage.getBytes());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
