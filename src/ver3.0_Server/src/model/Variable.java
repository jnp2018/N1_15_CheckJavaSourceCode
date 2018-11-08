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
public class Variable {
    private String name;
    private String type;
    private String modifier;

    public Variable() {
    }

    public Variable(String name, String type, String modifier) {
        this.name = name;
        this.type = type;
        this.modifier = modifier;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getModifier() {
        return modifier;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
    
}
