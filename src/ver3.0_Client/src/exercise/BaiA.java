/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise;

import java.util.Scanner;

/**
 *
 * @author admin
 */
public class BaiA {
    public static int ucln(int a, int b){
        if(b == 0) return a;
        return ucln(b, a%b);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int test = in.nextInt();
        while (test-- > 0) {
            int a = in.nextInt();
            int b = in.nextInt();
            System.out.println(ucln(a,b));
        }
    }
}
