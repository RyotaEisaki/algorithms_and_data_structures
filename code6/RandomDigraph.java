import java.util.Random;

class RandomDigraph {
    static final int M = Integer.MAX_VALUE;

    int[][] matrix;
    int size;

    RandomDigraph(int size, double edgerate, int maxweight) {
	this.matrix = new int[size][size];
	this.size = size;

        Random rnd = new Random();

	for (int i = 0; i < size; i++)
	    for (int j = 0; j < size; j++)
		if (i == j)
		    this.matrix[i][j] = 0;
		else {
		    if (Math.random() < edgerate)
			this.matrix[i][j] = rnd.nextInt(maxweight) + 1;
		    else
			this.matrix[i][j] = M;
		}
    }

    public int[][] getMatrix() {
	return matrix;
    }

    public String getString() {
	String preamble = "static int w[][] = {\n";
	String postamble = "};";

	String rslt = "";
	rslt = rslt + preamble;
	for (int i = 0; i < this.size; i++) {
	    rslt = rslt + "{";
	    for (int j = 0; j < this.size; j++) {
		if (this.matrix[i][j] == M)
		    rslt = rslt + "M";
		else
		    rslt = rslt + this.matrix[i][j];
		if (j < this.size - 1)
		    rslt = rslt + ",";
	    }
	    if (i < this.size - 1)
		rslt = rslt + "},\n";
	    else
		rslt = rslt + "}\n";
	}
	rslt = rslt + postamble;
	return rslt;
    }

    public static void main(String[] args) {
	if (args.length != 3) {
	    System.err.println("Usage: java RandomDigraph <頂点数> <辺の生成確率 [0, 1]> <辺の重みの最大値>");
	    System.exit(1);
	}

        int n = Integer.parseInt(args[0]);
        double r = Double.valueOf(args[1]);
        int w = Integer.parseInt(args[2]);

	RandomDigraph rd = new RandomDigraph(n, r, w);
	System.out.println(rd.getString());
    }
}
