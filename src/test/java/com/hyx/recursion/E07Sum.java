package com.hyx.recursion;

/**
 * hyx
 * 递归求和 n + n-1,...,+ 1
 */
public class E07Sum {

    // f(n) = f(n-1) + n
    public static long sum(long n) {
        if (n == 1) {
            return 1;
        }
        return sum(n - 1) + n;
    }

}
