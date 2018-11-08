/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sServer;

import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 */
public final class ConvertToXML extends ClassLoader {

    public String strXML;
    public String strClassname;
    public StringBuilder sb;

    public ConvertToXML(String classname, Class<?> aclass) {
        this.strClassname = classname;
        convertToXML(aclass);
        //System.out.println(sb.toString());
        strXML = System.getProperty("user.dir") + "/src/xmlTest/" + strClassname + ".xml";
        try (FileOutputStream fos = new FileOutputStream(strXML);) {
            byte[] data = sb.toString().getBytes();
            fos.write(data);
        } catch (Exception e) {
            System.err.println("error: " + e.getMessage());
        }
    }

    public void convertToXML(Class aclass) {
        sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
        JClass jClass = new JClass();
        jClass.name = aclass.getSimpleName();
        jClass.modifier = Modifier.toString(aclass.getModifiers());
        jClass.extend = aclass.getSuperclass().getSimpleName();

        Class<?>[] interfaces = aclass.getInterfaces();
        ArrayList<String> list = new ArrayList<String>();
        for (Class i : interfaces) {
            list.add(i.getSimpleName());
        }
        jClass.implement = list;
        sb.append(jClass.toString());

        Field[] fields = aclass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            JVariable jVariable = new JVariable();
            jVariable.name = fields[i].getName();
            jVariable.type = fields[i].getType().getSimpleName();
            int m = fields[i].getModifiers();
            jVariable.modifier = Modifier.toString(m);
            sb.append(jVariable.toString());
        }
        Method[] methods = aclass.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            JMethod jMethod = new JMethod();
            jMethod.name = methods[i].getName();
            jMethod.returns = methods[i].getReturnType().getSimpleName();
            int m = methods[i].getModifiers();
            jMethod.modifier = Modifier.toString(m);
            Parameter[] parameters = methods[i].getParameters();
            ArrayList<String> lp = new ArrayList<String>();
            for (Parameter para : parameters) {
                lp.add(para.getType().getSimpleName());
            }
            jMethod.parameter_type = lp;
            sb.append(jMethod.toString());
        }
        sb.append("</class>");
    }

    public String getXML() {
        return strXML;
    }

}
