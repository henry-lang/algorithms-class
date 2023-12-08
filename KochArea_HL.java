public class KochArea_HL {
    public static double kochArea(double area, int depth) {
        if (depth == 0) {
            return 0;
        }

        

        return 4 * kochArea(area / 9, depth - 1);
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println(
                    "ERROR: both an initial triangle area and the depth of the fractal must be provided as command-line arguments");
            System.exit(-1);
        }

        double initialArea = Double.parseDouble(args[0]);
        int depth = Integer.parseInt(args[1]);

        System.out.println(kochArea(initialArea, depth));
    }
}