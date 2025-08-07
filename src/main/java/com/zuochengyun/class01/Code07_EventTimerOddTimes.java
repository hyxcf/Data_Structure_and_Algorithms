package com.zuochengyun.class01;

public class Code07_EventTimerOddTimes {

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 5, 7, 7, 3, 1};
        printOddTimesNum2(arr);
    }


    /**
     * 两个数是1奇数，其余的都是偶数，找出两个奇数
     * arr = {1,3,1,4}
     * 0000 0001 ^ 0000 0011 = 0000 0010
     * 0000 0010 ^ 0000 0001 = 0000 0011
     * 0000 0011 ^ 0000 0100 = 0000 0111
     * 此时 eor = 3 ^ 4
     * 0000 0111 取反 1111 1000 + 1 = 1111 1001  & 0000 0111 = 0000 0001 rightOne = 1
     * <p>
     * 0000 0001 & 0000 0001 = 1 onlyOne = 1
     * 0000 0011 & 0000 0001 = 1 onlyOne = 0000 0001 ^ 0000 0011 = 0000 0010
     * 0000 0001 & 0000 0001 = 1 onlyOne = 0000 0010 ^ 0000 0001 = 0000 0011
     * 0000 0001 & 0000 0100 = 0
     * 故 onlyOne = 3
     * eor = 4
     * nb!!!
     */
    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        for (int i : arr) {
            eor ^= i; // 此时 eor 就是 a ^ b
        }
        // ^ 相同为0 不同为1
        // 又因为他们是奇数，所以 eor != 0，必然有一位上两者，一个为0，一个为1
        int rightOne = eor & (~eor + 1); // fixme：这样是得到 eor 最右侧的1的位置
        int onlyOne = 0;
        for (int j : arr) {
            if ((j & rightOne) == 1) {
                onlyOne ^= j;
            }
        }
        System.out.println(onlyOne + " " + (eor ^ onlyOne));
    }

}
