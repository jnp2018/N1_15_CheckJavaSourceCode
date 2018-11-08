/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sServer;

/**
 *
 * @author Administrator
 */
public class JVariable {
    public String name;
    public String modifier;
    public String type;

    public JVariable() {
    }
    @Override
    public String toString(){
        String s;
        s = "    <variable name=\"" + name +"\" "
                +"type=\"" + type +"\" "
                +"modifier=\"" + modifier +"\"/>\n";
        return s;
    }
}
