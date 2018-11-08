/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sServer;

import SosanhXML.SoSanh;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
class WorkerThread implements Runnable {

    private Socket socket;

    public WorkerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("Processing: " + socket);
        try {
            OutputStream os = socket.getOutputStream();
            InputStream is = socket.getInputStream();
            while (true) {
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                String className = dis.readUTF();
                if (className != null){
                    System.out.println("Received from client: " + className);
                    int length = dis.readInt();
                    byte[] data = new byte[length];
                    dis.read(data);
                    String response = null;
                    if(className.equals("BaiA")) {
                        response = test(data, className, "inputA.txt", "resultA.txt");
                    }
                    else if(className.equals("BaiB")) {
                        response = test(data, className, "inputB.txt", "resultB.txt");
                    }
                    else if(className.equals("BaiC")) {
                        response = test(data, className, "inputC.txt", "resultC.txt");
                    }
                    dos.writeUTF(response);
                    deleteFile(className);
                }
                System.out.println("Complete processing: " + socket);
//            }catch (IOException e) {
//                System.err.println("Request Processing Error: " + e);
            }

        } catch (IOException ex) {
            Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void writeFileJava(byte[] data, String fileJavaName) {
        String fileJavaDir = System.getProperty("user.dir") + "/src/exercise/" + fileJavaName + ".java";
        try (FileOutputStream fos = new FileOutputStream(fileJavaDir);) {
            fos.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Class<?> compileToClassFile(String fileJavaName) {
        FileInputStream fis = null;
        Class<?> aclass = null;
        String srcDir = System.getProperty("user.dir") + "/src";
        String exerciseDir = System.getProperty("user.dir") + "/src/exercise";
        String cmd = "javac " + fileJavaName + ".java";

        try {
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", cmd);
            builder.directory(new File(exerciseDir));
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                line = r.readLine();
                if (line == null) {
                    break;
                }
                System.out.println(line);
            }
            String classFileDir = System.getProperty("user.dir") + "/src/exercise/" + fileJavaName + ".class";
            File fileClass = new File(classFileDir);
            fis = new FileInputStream(fileClass);
            byte[] data = new byte[(int) fileClass.length()];
            while (fis.available() > 0) {
                fis.read(data);
            }
            MyClassLoader myClassLoader = new MyClassLoader();
            String fullClassName = "exercise." + fileJavaName;
            aclass = myClassLoader.defineClass(fullClassName, data);
            fis.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Khong tim thay File");
            return null;
        } catch (IOException ex) {
            System.out.println("CO loi");
            
        }
        return aclass;
    }

    public String test(byte[] data, String className, String inputtxt, String resulttxt) {
        writeFileJava(data, className);
        
        Class<?> aclass = compileToClassFile(className);
        
        if(aclass == null) {
            return "Dat ten class sai!!!!";
        }
        ConvertToXML convertToXML = new ConvertToXML(className, aclass);
        
        String xmlTestDir = System.getProperty("user.dir") + "/src/xmlTest/" + className + ".xml";
        String xmlDesignDir = System.getProperty("user.dir") + "/src/xmlDesign/" + className + ".xml";
        SoSanh compareXML = new SoSanh(xmlDesignDir, xmlTestDir);
        
        String resultDesign;
        if (compareXML.check() == true) {
            resultDesign = "Design is true\n";
        } else {
            resultDesign = "Design is false\n";
        }
        CheckOutput checkOutput = new CheckOutput(className);
        String resultOutput;
        String fileTxtDir = System.getProperty("user.dir") + "/src/fileTXT/";
        if (checkOutput.check(fileTxtDir + inputtxt, fileTxtDir + resulttxt)) {
            resultOutput = "Output is true\n";
        } else {
            resultOutput = "Output is false\n";
        }
        String response = resultDesign + "    " + resultOutput;
        return response;
    }
    
    public void deleteFile(String fileJavaName){
        String path = System.getProperty("user.dir") + "/src/exercise/" + fileJavaName + ".java";
        String pathClass = System.getProperty("user.dir") + "/src/exercise/" + fileJavaName + ".class";
        File file = new File(path);
        File fileClass = new File(pathClass);
        file.delete();
        fileClass.delete();
    }
}
