public class Hadamard {
    static boolean square(int r, int c, int n) {
        if (n == 1) {
            return false;
        }

        boolean invert = r >= n / 2 && c >= n / 2;
        return invert ^ square(r % (n / 2), c % (n / 2), n / 2);
    }

    public static void main(String[] args) throws Exception {
        int n = args.length > 0 ? Integer.parseInt(args[0]) : 8;
        if (Math.log10((double) n) / (Math.log10(2)) % 1 != 0) {
            System.out.println("ERROR: n must be a power of 2");
            System.exit(-1);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // \u2588 is a unicode code for a block character, so it looks nice in the
                // terminal
                System.out.print(square(j, i, n) ? "\u2588\u2588" : "  ");
            }
            System.out.println();
        }
    }
}