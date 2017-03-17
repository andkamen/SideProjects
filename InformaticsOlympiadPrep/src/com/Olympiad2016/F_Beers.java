package com.Olympiad2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class F_Beers {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int caseCount = 0;
        while(true){
            String input = reader.readLine();
            caseCount++;
//            StringBuilder sb = new StringBuilder();
//            sb.append(reader.readLine());

            System.out.printf("Case #%d: %s\n",caseCount,isWinPossible(input)?"yes":"no");

        }
    }

    private static boolean isWinPossible(String input) {
        if(input.length()==0){
            return true;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(input);

        Pattern pattern = Pattern.compile("(.)\\1+");
        Matcher matcher = pattern.matcher(input);

       // System.out.println(sb);
        while(matcher.find()){
            sb.replace(matcher.start(),matcher.end(),"");
            if(isWinPossible(sb.toString())){
                return true;
            }
            sb.insert(matcher.start(),matcher.group());
        }

        return false;
    }

}
