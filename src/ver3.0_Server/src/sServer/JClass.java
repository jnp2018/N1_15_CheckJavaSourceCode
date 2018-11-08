/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sServer;

import java.util.ArrayList;



/**
 *
 * @author Administrator
 */
public class JClass {
    public String name;
    public String modifier;
    public String extend;
    public ArrayList<String> implement;

    public JClass() {
        
    }
    @Override
    public String toString(){
        String s;
        s = "<class name=\"" + name +"\" "
                +"modifier=\"" + modifier +"\" "
                +"extends=\"" + extend +"\" "
                +"implements=\"" ;
        
        for(int i = 0; i<implement.size() ; i++){
            if(i == implement.size() - 1){
                s += implement.get(i);
                break;
            }
            s += implement.get(i) +" ";
        }
        s += "\">\n";
        return s;
    }
}
