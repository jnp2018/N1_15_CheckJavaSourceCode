package exercise;


import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author admin
 */
public class BaiC {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int test = in.nextInt();
        while (test-- > 0) {
            String str = in.next();
            String str1 = "", str2 = "";
            for(int i = 0; i< str.length(); i++){
                char c = str.charAt(i);
                if(Character.isAlphabetic(c) || Character.isDigit(c)){
                    str1 += c;
                }
                else str2 += c;
            }
            System.out.println(str1);
            System.out.println(str2);
        }
    }
}
