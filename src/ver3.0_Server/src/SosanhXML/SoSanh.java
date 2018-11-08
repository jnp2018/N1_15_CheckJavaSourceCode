/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SosanhXML;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import model.Method;
import model.Variable;
import model.XMLClass;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Nam Anh
 */
public class SoSanh {

    ArrayList<XMLClass> alClassesD = new ArrayList<>();
    ArrayList<Variable> alVariablesD = new ArrayList<>();
    ArrayList<Method> alMethodsD = new ArrayList<>();

    ArrayList<XMLClass> alClassesT = new ArrayList<>();
    ArrayList<Variable> alVariablesT = new ArrayList<>();
    ArrayList<Method> alMethodsT = new ArrayList<>();
    int classErr = 0;
    int varErr = 0;
    int methodErr = 0;
    public String XmlDesign, XmlTest;

    public SoSanh(String XmlDesign, String XMLTest) {
        this.XmlDesign = XmlDesign;
        this.XmlTest = XMLTest;
        inDesign();
        inTest();
        boolean classNotFound = true;
        boolean varNotFound = true;
        boolean methodNotFound = true;

        for (int i = 0; i < alClassesD.size(); i++) {
            XMLClass dCl = alClassesD.get(i);
            for (int j = 0; j < alClassesT.size(); j++) {
                XMLClass tCl = alClassesT.get(j);
                if (dCl.getName().equals(tCl.getName())) {
                    classNotFound = false;
                    if (!dCl.getModifier().equals(tCl.getModifier())
                            || !dCl.getExtend().equals(tCl.getExtend())
                            || !dCl.getImplement().equals(tCl.getImplement())) {
                        classErr++;
                    }

                    for (int m = 0; m < alClassesD.get(i).getVarList().size(); m++) {
                        Variable dVar = alClassesD.get(i).getVarList().get(m);
                        for (int n = 0; n < alClassesT.get(j).getVarList().size(); n++) {
                            Variable tVar = alClassesT.get(j).getVarList().get(n);
                            if (dVar.getName().equals(tVar.getName())) {
                                varNotFound = false;
                                if (!dVar.getModifier().equals(tVar.getModifier())
                                        || !dVar.getType().equals(tVar.getType())) {
                                    varErr++;
                                }
                            }
                        }
                        if (varNotFound) {
                            varErr++;
                        }
                    }

                    for (int m = 0; m < alClassesD.get(i).getMethodList().size(); m++) {
                        Method dMethod = alClassesD.get(i).getMethodList().get(m);
                        for (int n = 0; n < alClassesT.get(j).getMethodList().size(); n++) {
                            Method tMethod = alClassesT.get(j).getMethodList().get(n);
                            if (dMethod.getName().equals(tMethod.getName())) {
                                methodNotFound = false;
                                if (!dMethod.getReturnType().equals(tMethod.getReturnType())
                                        || !dMethod.getModifier().equals(tMethod.getModifier())
                                        || !dMethod.getParameterType().equals(tMethod.getParameterType())) {
                                    methodErr++;
                                }
                            }
                        }
                        if (methodNotFound) {
                            methodErr++;
                        }
                    }
                }
            }
            if (classNotFound) {
                classErr++;
            }
        }

        System.out.println("So Class loi: " + classErr);
        System.out.println("So Variable loi: " + varErr);
        System.out.println("So Method loi: " + methodErr);
    }

    public boolean check() {
        if (classErr == 0 && varErr == 0 && methodErr == 0) {
            return true;
        }
        return false;
    }

    public void inDesign() {
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(XmlDesign));

            doc.getDocumentElement().normalize();
            NodeList nlClasses = doc.getElementsByTagName("class");

            for (int i = 0; i < nlClasses.getLength(); i++) {
                Node classNode = nlClasses.item(i);

                if (classNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element classElement = (Element) classNode;
                    XMLClass cl = new XMLClass();

                    cl.setName(classElement.getAttribute("name"));
                    cl.setModifier(classElement.getAttribute("modifier"));
                    cl.setExtend(classElement.getAttribute("extends"));
                    cl.setImplement(classElement.getAttribute("implements"));

                    NodeList nlVariables = classElement.getElementsByTagName("variable");

                    for (int j = 0; j < nlVariables.getLength(); j++) {
                        Node varNode = nlVariables.item(j);

                        if (varNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element varElement = (Element) varNode;
                            Variable var = new Variable();

                            var.setName(varElement.getAttribute("name"));
                            var.setType(varElement.getAttribute("type"));
                            var.setModifier(varElement.getAttribute("modifier"));
                            alVariablesD.add(var);
                        }
                    }

                    cl.setVarList(alVariablesD);

                    NodeList nlMethods = classElement.getElementsByTagName("method");

                    for (int k = 0; k < nlMethods.getLength(); k++) {
                        Node methodNode = nlMethods.item(k);

                        if (methodNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element methodElement = (Element) methodNode;
                            Method method = new Method();

                            method.setName(methodElement.getAttribute("name"));
                            method.setReturnType(methodElement.getAttribute("return"));
                            method.setModifier(methodElement.getAttribute("modifier"));
                            method.setParameterType(methodElement.getAttribute("parameter_type"));
                            alMethodsD.add(method);
                        }
                    }

                    cl.setMethodList(alMethodsD);
                    alClassesD.add(cl);
                }
            }
            /*
            for (int i = 0; i < alClassesD.size(); i++){
                XMLClass tempCl = alClassesD.get(i);
                
                System.out.println("Class name: " + tempCl.getName());
                System.out.println("Class modifier: " + tempCl.getModifier());
                System.out.println("Class extends: " + tempCl.getExtend());
                System.out.println("Class implements: " + tempCl.getImplement());
                System.out.println();
                
                ArrayList<Variable> tempVL = tempCl.getVarList();
                for (int j = 0; j < tempVL.size(); j++) {
                    System.out.println("Variable name: " + tempVL.get(j).getName());
                    System.out.println("Variable type: " + tempVL.get(j).getType());
                    System.out.println("Variable modifier: " + tempVL.get(j).getModifier());
                    System.out.println();
                }
                
                ArrayList<Method> tempML = tempCl.getMethodList();
                for (int k = 0; k < tempML.size(); k++) {
                    System.out.println("Method name: " + tempML.get(k).getName());
                    System.out.println("Method return: " + tempML.get(k).getReturnType());
                    System.out.println("Method modifier: " + tempML.get(k).getModifier());
                    System.out.println("Method parameter type: " + tempML.get(k).getParameterType());
                    System.out.println();
                }
            }
             */
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void inTest() {
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(XmlTest));

            doc.getDocumentElement().normalize();
            NodeList nlClasses = doc.getElementsByTagName("class");

            for (int i = 0; i < nlClasses.getLength(); i++) {
                Node classNode = nlClasses.item(i);

                if (classNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element classElement = (Element) classNode;
                    XMLClass cl = new XMLClass();

                    cl.setName(classElement.getAttribute("name"));
                    cl.setModifier(classElement.getAttribute("modifier"));
                    cl.setExtend(classElement.getAttribute("extends"));
                    cl.setImplement(classElement.getAttribute("implements"));

                    NodeList nlVariables = classElement.getElementsByTagName("variable");

                    for (int j = 0; j < nlVariables.getLength(); j++) {
                        Node varNode = nlVariables.item(j);

                        if (varNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element varElement = (Element) varNode;
                            Variable var = new Variable();

                            var.setName(varElement.getAttribute("name"));
                            var.setType(varElement.getAttribute("type"));
                            var.setModifier(varElement.getAttribute("modifier"));
                            alVariablesT.add(var);
                        }
                    }

                    cl.setVarList(alVariablesT);

                    NodeList nlMethods = classElement.getElementsByTagName("method");

                    for (int k = 0; k < nlMethods.getLength(); k++) {
                        Node methodNode = nlMethods.item(k);

                        if (methodNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element methodElement = (Element) methodNode;
                            Method method = new Method();

                            method.setName(methodElement.getAttribute("name"));
                            method.setReturnType(methodElement.getAttribute("return"));
                            method.setModifier(methodElement.getAttribute("modifier"));
                            method.setParameterType(methodElement.getAttribute("parameter_type"));
                            alMethodsT.add(method);
                        }
                    }

                    cl.setMethodList(alMethodsT);
                    alClassesT.add(cl);
                }
            }
            /*            
            for (int i = 0; i < alClassesT.size(); i++){
                XMLClass tempCl = alClassesT.get(i);
                
                System.out.println("Class name: " + tempCl.getName());
                System.out.println("Class modifier: " + tempCl.getModifier());
                System.out.println("Class extends: " + tempCl.getExtend());
                System.out.println("Class implements: " + tempCl.getImplement());
                System.out.println();
                
                ArrayList<Variable> tempVL = tempCl.getVarList();
                for (int j = 0; j < tempVL.size(); j++) {
                    System.out.println("Variable name: " + tempVL.get(j).getName());
                    System.out.println("Variable type: " + tempVL.get(j).getType());
                    System.out.println("Variable modifier: " + tempVL.get(j).getModifier());
                    System.out.println();
                }
                
                ArrayList<Method> tempML = tempCl.getMethodList();
                for (int k = 0; k < tempML.size(); k++) {
                    System.out.println("Method name: " + tempML.get(k).getName());
                    System.out.println("Method return: " + tempML.get(k).getReturnType());
                    System.out.println("Method modifier: " + tempML.get(k).getModifier());
                    System.out.println("Method parameter type: " + tempML.get(k).getParameterType());
                    System.out.println();
                }
            }
             */
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
