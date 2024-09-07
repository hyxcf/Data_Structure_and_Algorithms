package com.hyx.recursion;


/**
 * hyx
 * 递归反向打印字符串
 */
public class ReverserPrintString {

    public static void f(int n,String str){
        if (n == str.length()){
            return;
        }
        f(n+1,str);
        System.out.print(str.charAt(n));
    }

    public void test(){
        f(0,"abcd");
    }

}



