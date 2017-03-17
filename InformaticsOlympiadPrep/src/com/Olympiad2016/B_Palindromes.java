package com.Olympiad2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class B_Palindromes {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Set<Long> allPalindromes = allPalindromic(Integer.MAX_VALUE);
        while (true) {
            String input = reader.readLine();
            if ("".equals(input)) {
                break;
            }
            int[] range = Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).toArray();
            int count = 0;
            for (Long num : allPalindromes) {

                if (num < range[0] || num > range[1]) {
                    continue;
                }
                if (isPalindrome(String.valueOf(num)) && isPalindrome(Long.toBinaryString(num))) {
                    count++;
                }
            }

            System.out.println(count);
        }
    }

    public static Set<Long> allPalindromic(long limit) {

        Set<Long> result = new LinkedHashSet<>();

        for (long i = 0; i <= 9 && i <= limit; i++) {
            result.add(i);
        }

        boolean cont = true;
        for (long i = 1; cont; i++) {
            StringBuffer rev = new StringBuffer("" + i).reverse();
            cont = false;
            for (String d : ",0,1,2,3,4,5,6,7,8,9".split(",")) {

                long n = Long.parseLong("" + i + d + rev);

                if (n <= limit) {
                    cont = true;
                    result.add(n);
                }
            }
        }
        return result;
    }

    public static boolean isPalindrome(String input) {
        for (int start = 0, end = input.length() - 1; start < end; ) {
            if (input.charAt(start++) != input.charAt(end--)) {
                return false;
            }
        }
        return true;
    }
}
