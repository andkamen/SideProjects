package com.Olympiad2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class A_Digit {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String input = reader.readLine();
            if ("".equals(input)) {
                break;
            }
            BigInteger num = new BigInteger(input);
            int exp = Integer.parseInt(input);
            String result = fastPower(num, exp).toString();
            String anotherResult = num.pow(Integer.parseInt(input)).toString();
            System.out.println(result);
            System.out.println(anotherResult);
            System.out.println(exp>result.length()?"*":result.charAt(exp-1));
        }
    }

    public static BigInteger fastPower(BigInteger x, int n) {
        if(n==0){
            return new BigInteger("1");
        }else if(n==1){
            return x;
        }else if(n%2==0){
            return fastPower(x.pow(2),n/2);
        }else{
            return x.multiply(fastPower(x.pow(2),(n-1)/2));
        }
    }
}





