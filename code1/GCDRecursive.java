public class GCDRecursive {
    public static void main(final String[] args) {
        // Process arguments.
        if (args.length != 2) {
            System.err.println("Usage: java GCDRecursive <int1> <int2>");
            System.exit(1);
        }
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        // Find the greatest common divisor.
        int gcd = gcd(n, m);
        System.out.println("The GCD of " + m + " and " + n + " is " + gcd + ".") ;
    }

    // Find the greatest common divisor of the two integers, n and m.
    static int gcd(int n, int m) {
        // Let n be the smaller number.
        if (n > m) {
            int tmp = m;
            m = n;
            n = tmp;
        }
	if (n==0) return m;
        return gcd(n,m%n);
    }
}