/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Nam Anh
 */
public class Method {
    private String name;
    private String returnType;
    private String modifier;
    private String parameterType;

    public Method() {
    }

    public Method(String name, String returnType, String modifier, String parameterType) {
        this.name = name;
        this.returnType = returnType;
        this.modifier = modifier;
        this.parameterType = parameterType;
    }

    public String getName() {
        return name;
    }

    public String getReturnType() {
        return returnType;
    }

    public String getModifier() {
        return modifier;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }
    
}
