/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Nam Anh
 */
public class XMLClass {
    private String name;
    private String modifier;
    private String extend;
    private String implement;
    
    private ArrayList<Variable> varList;
    private ArrayList<Method> methodList;

    public XMLClass() {
    }

    public XMLClass(String name, String modifier, String extend, String implement, ArrayList<Variable> varList, ArrayList<Method> methodList) {
        this.name = name;
        this.modifier = modifier;
        this.extend = extend;
        this.implement = implement;
        this.varList = varList;
        this.methodList = methodList;
    }

    public String getName() {
        return name;
    }

    public String getModifier() {
        return modifier;
    }

    public String getExtend() {
        return extend;
    }

    public String getImplement() {
        return implement;
    }

    public ArrayList<Variable> getVarList() {
        return varList;
    }

    public ArrayList<Method> getMethodList() {
        return methodList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public void setImplement(String implement) {
        this.implement = implement;
    }

    public void setVarList(ArrayList<Variable> varList) {
        this.varList = varList;
    }

    public void setMethodList(ArrayList<Method> methodList) {
        this.methodList = methodList;
    }

}
