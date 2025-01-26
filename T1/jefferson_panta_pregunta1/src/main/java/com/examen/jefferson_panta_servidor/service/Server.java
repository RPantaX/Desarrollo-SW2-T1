package com.examen.jefferson_panta_servidor.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.net.ServerSocket;
import java.net.Socket;

@Service
public class Server {

    private static final int PORT = 5566;

    @PostConstruct
    public void startServer() {
        new Thread(()->{
            try(ServerSocket socket = new ServerSocket(PORT)){
                System.out.println("Server escuchando en puerto: " + PORT);
                while(true){
                    Socket client = socket.accept();
                    new Thread(new SocketClientHandler(client)).start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
