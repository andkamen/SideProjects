package com;


import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Check if array is sorted. If not shuffle till it is.
 */
public class BogoSort {
    public static void main(String[] args) {
        //int[] testArr = {3, 4, 2, 7, 5, 9, 8, 1, 6, 0};
        int[] testArr = {4, 2, 3, 7, 1, 5, 0, 6};
        long count = 0;
        long begin = System.currentTimeMillis();

        while (!isSorted(testArr)) {
            shuffleArray(testArr);
            count++;
        }

        long end = System.currentTimeMillis();
        long dt = (end - begin) / 1000;

        System.out.printf("Took %d seconds and %d tries to sort the array \n", dt, count);
        System.out.println(Arrays.toString(testArr));
    }

    static boolean isSorted(int[] array) {

        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

    static void shuffleArray(int[] array) {
        Random rnd = ThreadLocalRandom.current();

        for (int i = 0; i < array.length; i++) {
            int index = rnd.nextInt(i + 1);

            int a = array[index];
            array[index] = array[i];
            array[i] = a;
        }
    }

    //check if reverse shuffle is avoids reshufling already shuffled number?

//    static void shuffleArray(int[] array) {
//        Random rnd = ThreadLocalRandom.current();
//        for (int i = array.length - 1; i > 0; i--) {
//            int index = rnd.nextInt(i + 1);
//            // Simple swap
//            int a = array[index];
//            array[index] = array[i];
//            array[i] = a;
//        }
//    }
}