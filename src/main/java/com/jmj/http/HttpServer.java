package com.jmj.http;








import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author jianger
 * @Date 2018/7/16 下午2:10
 **/
public class HttpServer {

    private boolean shutdown=false;

    public void acceptServer(){
        ServerSocket serverSocket=null;
        try {
            serverSocket=new ServerSocket(8080,1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        while (!shutdown){
            try {
                Socket socket=serverSocket.accept();
                InputStream is=socket.getInputStream();
                OutputStream os=socket.getOutputStream();

                Request request=new Request(is) ;
                request.parse();

                Response response=new Response(os);
                response.setRequest(request);
                response.setStaicResource();

                socket.close();

                if (null!=request){
                    shutdown=request.getUrl().equals("/shutdown");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        HttpServer server=new HttpServer();
        server.acceptServer();
    }

}
