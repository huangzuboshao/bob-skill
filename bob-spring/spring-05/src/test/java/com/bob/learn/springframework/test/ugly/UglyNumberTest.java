package com.bob.learn.springframework.test.ugly;

import java.util.Arrays;

/**
 * @author Bob
 * @date 2022/8/15 10:14
 */
public class UglyNumberTest {

    /**
     * 计算第N个丑数
     * 只能被2,3,5整除的数
     * P1: 2 2*2 3*2
     * P2: 3 2*3 3*3
     * P3: 5 2*5 3*5
     *
     * @param n
     * @return
     */
    static int nthUglyNumber(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("N应该>0");
        }
        int p1 = 1, p2 = 1, p3 = 1;
        int product2 = 1, product3 = 1, product5 = 1;
        int[] numArr = new int[n + 1];

        int p = 1;
        while (p <= n) {
            int min = Math.min(product2, Math.min(product3, product5));
            numArr[p] = min;
            p++;
            if (min == product2) {
                product2 = 2 * numArr[p1];
                p1++;
            }
            if (min == product3) {
                product3 = 3 * numArr[p2];
                p2++;
            }
            if (min == product5) {
                product5 = 5 * numArr[p3];
                p3++;
            }
        }
        System.out.println(Arrays.toString(numArr));
        return numArr[p];
    }

    public static void main(String[] args) {
        System.out.println(nthUglyNumber(10));
    }
}
