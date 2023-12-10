/*
Henry Langmack and Tom Schlessinger's radix sort implementation - it creates a copy of the array
Based off of this visual animation: https://www.cs.usfca.edu/~galles/visualization/RadixSort.html
Input: One command line argument, the amount of numbers
Output: The original and sorted array using RadixSort
*/

import java.util.Arrays;

class RadixSort {
    static int[] radixSort(int[] arr) {
        // Allocate digit bookkeeping array
        int[] digit = new int[10];

        // Allocate the temporary array
        int[] newArr = new int[arr.length];

        // Find max number in the array
        int maxNum = 0;
        for (int i = 0; i < arr.length; i++) {
            maxNum = Math.max(maxNum, arr[i]);
        }

        // Find how many digits are in the max number
        int maxDigits = maxNum == 0 ? 1 : (int) (Math.log10(maxNum) + 1);

        // For each digit...
        for (int d = 0; d < maxDigits; d++) {
            int place = (int) Math.pow(10, d);

            // Count how many numbers have each digit in the place we're currently looking
            // at
            for (int i = 0; i < arr.length; i++) {
                digit[(arr[i] / place) % 10]++;
            }

            // Prefix sum on the digit tally
            for (int i = 1; i < 10; i++) {
                digit[i] = digit[i] + digit[i - 1];
            }

            // Sort numbers by the current digit by traversing the current array backwards,
            // filling up buckets
            for (int i = arr.length - 1; i >= 0; i--) {
                int thisDigit = (arr[i] / place) % 10;
                newArr[digit[thisDigit] - 1] = arr[i];
                digit[thisDigit] -= 1;
            }

            // Zero digit array for next iteration
            for (int i = 0; i < 10; i++) {
                digit[i] = 0;
            }

            // Copy temp array to original array
            for (int i = 0; i < arr.length; i++) {
                arr[i] = newArr[i];
            }
        }

        return arr;
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int) (Math.random() * 1000);
        }

        System.out.println("Before: " + Arrays.toString(arr));
        int[] newArr = radixSort(arr);
        System.out.println("After:  " + Arrays.toString(newArr));
    }
}

/*
 * Testing:
 * 
 * > java RadixSort.java 15
 * Before: [24, 21, 899, 514, 330, 997, 823, 600, 651, 631, 657, 61, 109, 931,
 * 901]
 * After: [21, 24, 61, 109, 330, 514, 600, 631, 651, 657, 823, 899, 901, 931,
 * 997]
 * 
 * > java RadixSort.java 5
 * Before: [292, 973, 78, 230, 38]
 * After: [38, 78, 230, 292, 973]
 * 
 * > java RadixSort.java 20
 * Before: [30, 134, 933, 456, 391, 702, 880, 697, 317, 61, 490, 565, 5, 662,
 * 184, 179, 560, 415, 577, 404]
 * After: [5, 30, 61, 134, 179, 184, 317, 391, 404, 415, 456, 490, 560, 565,
 * 577, 662, 697, 702, 880, 933]
 * 
 * > java RadixSort.java 0
 * Before: []
 * After: []
 * 
 * > java RadixSort.java 200
 * Before: [828, 458, 584, 610, 425, 192, 701, 123, 447, 80, 541, 712, 848, 530,
 * 492, 567, 930, 71, 422, 412, 426, 554, 703, 16, 223, 887, 870, 988, 163, 512,
 * 679, 878, 179, 111, 30, 871, 735, 557, 226, 313, 189, 731, 529, 892, 199,
 * 624, 809, 530, 781, 122, 392, 971, 935, 923, 847, 592, 33, 368, 559, 182,
 * 316, 315, 432, 307, 921, 822, 932, 419, 822, 92, 8, 28, 961, 50, 133, 116,
 * 615, 938, 807, 463, 763, 554, 395, 713, 786, 68, 112, 803, 358, 910, 261,
 * 857, 790, 846, 626, 775, 408, 231, 13, 505, 773, 372, 513, 434, 667, 84, 23,
 * 426, 821, 832, 901, 779, 305, 267, 428, 773, 326, 611, 906, 538, 961, 79,
 * 821, 710, 995, 450, 475, 680, 624, 994, 889, 287, 213, 163, 599, 513, 735,
 * 867, 145, 19, 761, 686, 691, 432, 83, 75, 222, 883, 132, 471, 195, 650, 398,
 * 233, 362, 690, 914, 501, 703, 505, 729, 394, 63, 28, 2, 449, 158, 167, 973,
 * 847, 28, 249, 612, 733, 924, 821, 185, 945, 867, 195, 335, 511, 779, 675,
 * 506, 580, 212, 621, 731, 862, 134, 362, 847, 205, 179, 534, 187, 697, 31,
 * 564]
 * After: [2, 8, 13, 16, 19, 23, 28, 28, 28, 30, 31, 33, 50, 63, 68, 71, 75, 79,
 * 80, 83, 84, 92, 111, 112, 116, 122, 123, 132, 133, 134, 145, 158, 163, 163,
 * 167, 179, 179, 182, 185, 187, 189, 192, 195, 195, 199, 205, 212, 213, 222,
 * 223, 226, 231, 233, 249, 261, 267, 287, 305, 307, 313, 315, 316, 326, 335,
 * 358, 362, 362, 368, 372, 392, 394, 395, 398, 408, 412, 419, 422, 425, 426,
 * 426, 428, 432, 432, 434, 447, 449, 450, 458, 463, 471, 475, 492, 501, 505,
 * 505, 506, 511, 512, 513, 513, 529, 530, 530, 534, 538, 541, 554, 554, 557,
 * 559, 564, 567, 580, 584, 592, 599, 610, 611, 612, 615, 621, 624, 624, 626,
 * 650, 667, 675, 679, 680, 686, 690, 691, 697, 701, 703, 703, 710, 712, 713,
 * 729, 731, 731, 733, 735, 735, 761, 763, 773, 773, 775, 779, 779, 781, 786,
 * 790, 803, 807, 809, 821, 821, 821, 822, 822, 828, 832, 846, 847, 847, 847,
 * 848, 857, 862, 867, 867, 870, 871, 878, 883, 887, 889, 892, 901, 906, 910,
 * 914, 921, 923, 924, 930, 932, 935, 938, 945, 961, 961, 971, 973, 988, 994,
 * 995]
 */