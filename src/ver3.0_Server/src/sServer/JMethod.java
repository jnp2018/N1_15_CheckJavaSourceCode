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
public class JMethod {
    public String name;
    public String modifier;
    public String returns;
    public ArrayList<String> parameter_type;

    public JMethod() {
        
    }
    @Override
    public String toString(){
        String s;
        s = "    <method name=\"" + name +"\" "
                +"return=\"" + returns +"\" "
                +"modifier=\"" + modifier +"\" "
                +"parameter_type=\"" ;
        
        for(int i = 0; i<parameter_type.size() ; i++){
            if(i == parameter_type.size() - 1){
                s += parameter_type.get(i);
                break;
            }
            s += parameter_type.get(i) +" ";
        }
        s += "\"/>\n";
        return s;
    }
}
