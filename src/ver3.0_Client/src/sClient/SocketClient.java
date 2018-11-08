/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class SocketClient {
    Socket socket;
    
    public SocketClient(String host, int port) {
        try {
            socket = new Socket(host, port);
        } catch (IOException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public byte[] toByteArray(File submitFile){
        byte[] data = null;
        try (FileInputStream fis = new FileInputStream(submitFile);){
            data = new byte[(int) submitFile.length()];
            while (fis.available() > 0) {
                fis.read(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    
    public String send(String exerciseName, byte[] data, int length){
        String response = null;
        try {
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            dos.writeUTF(exerciseName);
            dos.flush();
            dos.writeInt(length);
            dos.flush();
            dos.write(data);
            dos.flush();
            response = dis.readUTF();
            if(response != null){
                return response;
            }
        } catch (IOException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
}
