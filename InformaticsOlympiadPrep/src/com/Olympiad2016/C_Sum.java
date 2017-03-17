package com.Olympiad2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashSet;

public class C_Sum {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            long num = Long.parseLong(reader.readLine());

            if (num == 0) {
                break;
            }

            BigInteger sum = new BigInteger("0");
            int digitCount = String.valueOf(num).length();
            // System.out.println(digitCount);
            HashSet<String> permuations = new HashSet<>();
            for (int i = 0; i <= ((1 << digitCount) - 1); i++) {
                char[] numArr = String.valueOf(num).toCharArray();

                String binaryRep = Integer.toBinaryString((1 << digitCount) | i).substring(1);
                for (int j = 0; j < digitCount; j++) {
                    if (binaryRep.charAt(j) == '0') {
                        numArr[j] = '0';
                    }
                }

                permuations.add(new String(numArr));
            }
            for (String permuation : permuations) {
                sum = sum.add(new BigInteger(permuation));
            }
            System.out.println(sum.toString());

        }
    }
}
