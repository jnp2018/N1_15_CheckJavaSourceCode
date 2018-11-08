/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class Server extends Thread {
    public static final int NUM_OF_THREAD = 100;
    public final static int SERVER_PORT = 1105;
    
    public static void main(String[] args) throws IOException {
            ExecutorService executor = Executors.newFixedThreadPool(NUM_OF_THREAD);
            ServerSocket serverSocket = null;
        try {
            System.out.println("Binding to port " + SERVER_PORT);
            serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Server started: " + serverSocket);
            System.out.println("Waiting a client");
            Socket socket = null;
            
                while(true) {
                socket = serverSocket.accept();
                
                System.out.println("Client accepted: " + socket);
                
                WorkerThread wt = new WorkerThread(socket);
                executor.execute(wt);
                //executor.shutdown();
            
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
        
    }
}
