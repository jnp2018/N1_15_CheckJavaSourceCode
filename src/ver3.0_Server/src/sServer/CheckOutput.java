/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sServer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class CheckOutput {

    String className;
    String strCMD;

    public CheckOutput(String className) {
        this.className = className;
    }

    public boolean check(String InputDir, String ResultDir) {
       
        String srcDir = System.getProperty("user.dir") + "/src";
        String fullClassName = "exercise." + className; 
        strCMD = "java " + fullClassName + " <" + InputDir;
        try {
            ProcessBuilder builder = new ProcessBuilder("cmd.exe", "/c", strCMD);
            builder.directory(new File(srcDir));
            Process p = builder.start();
            BufferedReader r1 = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader r2 = new BufferedReader(new FileReader(ResultDir));
            String line1, line2;
            while (true) {
                 line1 = r1.readLine();
                 line2 = r2.readLine();
                if (line1 != null || line2 != null) {
                    if (line2 == null ? line1 == null : line2.equals(line1)) {
                        System.out.println("Đáp án gửi: "+line1 + " Đáp án đúng: " + line2);
                    } else {
                        System.out.println("Đáp án gửi: "+line1 + " Đáp án đúng: " + line2);
                        return false;
                    }
                } else {
                    break;
                }
            }
            return true;
        } catch (IOException ex) {
            Logger.getLogger(CheckOutput.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
