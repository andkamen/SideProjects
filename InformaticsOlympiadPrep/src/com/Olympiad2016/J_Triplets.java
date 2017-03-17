package com.Olympiad2016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class J_Triplets {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> answers = new ArrayList<>();
        while (true) {
            String input = reader.readLine();
            if ("".equals(input)) {
                break;
            }

            int[] nums = Arrays.stream(input.split("\\s+")).mapToInt(Integer::parseInt).toArray();


            //TODO too slow dynamical programing required
            int count = 0;
            for (int i = 0; i < nums.length ; i++) {
                for (int j = i + 1; j < nums.length ; j++) {
                    for (int k = j + 1; k < nums.length; k++) {
//                        if (nums[i] + nums[j] > nums[k]) {
//                            break;
//                        }
                        if (nums[i] + nums[j] == nums[k]) {
                            count++;
                        }

                    }
                }
             //   System.out.println("at :"+ i);
            }
             System.out.println(count);
           // answers.add(count);
        }
        System.out.println(answers.toString());
    }
}
