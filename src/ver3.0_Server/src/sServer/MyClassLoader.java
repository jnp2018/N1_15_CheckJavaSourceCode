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
public class MyClassLoader extends ClassLoader {

    byte[] data;

    public MyClassLoader() {
    }

    public Class<?> defineClass(String fullClassName, byte[] data) {
        return defineClass(fullClassName, data, 0, data.length);
    }
}
