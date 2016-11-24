package com;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Bogosort the first element. Check to see if that one element is sorted.
 * Being one element, it will be. Then you add the second element, and Bogosort those two until it's sorted.
 * Then you add one more element, then Bogosort. Continue adding elements and Bogosorting until you have finally done every element.
 */
public class BogoBogoSort {
    public static void main(String[] args) {
        int[] testArr = {4, 2, 8, 3, 9, 7, 1, 5, 0, 6};

        long count = 0;
        long begin = System.currentTimeMillis();

        for (int n = 1; n <= testArr.length; ++n) {
            while (!isSorted(testArr, 0, n)) {
                shuffleArray(testArr, 0, n);
                count++;
            }
        }

        long end = System.currentTimeMillis();
        long dt = (end - begin) / 1000;

        System.out.printf("Took %d seconds and %d tries to sort the array \n", dt, count);
        System.out.println(Arrays.toString(testArr));
    }

    static boolean isSorted(int[] array, int startIndex, int endIndex) {
        for (int i = startIndex; i < endIndex - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

    static void shuffleArray(int[] array, int startIndex, int endIndex) {
        Random rnd = ThreadLocalRandom.current();

        for (int i = startIndex; i < endIndex; i++) {
            int index = rnd.nextInt(i + 1);

            int a = array[index];
            array[index] = array[i];
            array[i] = a;
        }
    }
}
