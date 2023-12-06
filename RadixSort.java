/*
Henry Langmack and Tom Schlessinger's radix sort implementation - it creates a copy of the array
Based off of this visual animation: https://www.cs.usfca.edu/~galles/visualization/RadixSort.html
*/

import java.util.Arrays;

class RadixSort {
    static int[] radixSort(int[] arr) {
        int[] digit = new int[10];
        int[] newArr = new int[arr.length];

        int maxNum = 0;
        for(int i = 0; i < arr.length; i++) {
            maxNum = Math.max(maxNum, arr[i]);
        }
    
        int maxDigits = maxNum == 0 ? 1 : (int) (Math.log10(maxNum) + 1);

        for(int d = 0; d < maxDigits; d++) {
            int place = (int) Math.pow(10, d);
            for(int i = 0; i < arr.length; i++) {
                digit[(arr[i] / place) % 10]++;
            }

            for(int i = 1; i < 10; i++) {
                digit[i] = digit[i] + digit[i - 1];
            }

            for(int i = arr.length - 1; i >= 0; i--) {
                int thisDigit = (arr[i] / place) % 10;
                // System.out.println(arr[i] + " " + thisDigit);
                newArr[digit[thisDigit] - 1] = arr[i];
                digit[thisDigit] -= 1;
            }

            for(int i = 0; i < 10; i++) {
                digit[i] = 0;
            }

            for(int i = 0; i < arr.length; i++) {
                arr[i] = newArr[i];
            }
            
            System.out.println(Arrays.toString(newArr));
        }

        return arr;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = (int)(Math.random() * 10000);
        }

        System.out.println(Arrays.toString(arr));
        radixSort(arr);
    }
}
