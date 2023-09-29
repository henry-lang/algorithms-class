/*
 * Description: Write a program IntegerToBinary_YI.java that takes a positive integer n 
 * (in decimal) as a command-line argument (optional) and prints its binary representation.
 * Author's name: Henry Langmack
 * Date: 9/29/23
 * Input: One command-line argument, the number
 * Output: The number in binary representation
 * The program runs successfully.
 */

public class Binary_HL {
    private static void printBinaryInner(int num) {
        if (num == 0) {
            return;
        }
        printBinaryInner(num / 2);
        System.out.print(num % 2);
    }

    static void printBinary(int num) {
        if (num == 0) {
            System.out.print("0");
            return;
        }
        printBinaryInner(num);
        System.out.println();
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("ERROR: number must be provided as a command-line argument");
            System.exit(-1);
        }

        int num = Integer.parseInt(args[0]);
        if (num < 0) {
            System.out.println("ERROR: Please provide a non-negative number.");
            return;
        }

        printBinary(num);
    }
}

/*
 * Testing:
 * 
 * hlangmack@MS-Student31338 algorithms-class % java Binary_HL.java 10
 * 1010
 * hlangmack@MS-Student31338 algorithms-class % java Binary_HL.java 1000
 * 1111101000
 * hlangmack@MS-Student31338 algorithms-class % java Binary_HL.java 0
 * 0
 * hlangmack@MS-Student31338 algorithms-class % java Binary_HL.java 1
 * 1
 * hlangmack@MS-Student31338 algorithms-class % java Binary_HL.java 2
 * 10
 * hlangmack@MS-Student31338 algorithms-class % java Binary_HL.java 2
 * 10
 * hlangmack@MS-Student31338 algorithms-class % java Binary_HL.java 3
 * 11
 * hlangmack@MS-Student31338 algorithms-class % java Binary_HL.java 485865839
 * 11100111101011011100101101111
 */