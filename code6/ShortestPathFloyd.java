public class ShortestPathFloyd {

    static final int M = Integer.MAX_VALUE;          // 到達不能点(無限大)


    // static int w[][] = {                             // グラフの隣接行列
      //   { 0, M,  M, 8, 15, M},
        // {10, 0, 24, M,  8, M},
        // { M, M,  0, M,  M, 6},
        // { M, M,  M, 0,  5, M},
        // { M, M, 12, M,  0, 7},
        // { M, M,  3, M,  M, 0}};

         static int w[][] = {
         {0,4,22,M,M,14,M,M},
         {M,0,M,M,M,M,M,M},
         {M,M,0,M,30,19,M,M},
         {25,M,M,0,M,29,22,M},
         {M,16,M,7,0,M,M,M},
         {M,21,M,M,M,0,M,30},
         {M,M,M,13,M,M,0,2},
         {8,M,16,M,M,M,M,0}
         };

    static final int N = w.length;                          // グラフの頂点数
    static int d[][] = new int[N][N];
    static int p[][] = new int[N][N];

    static Stack stack = new Stack(100);
    /*
     * Floyd のアルゴリズム
     */
    static void floyd() {
	// Initialized
	for (int i = 0; i < N; i++) {
	    for (int j = 0; j < N; j++) {
		d[i][j] = w[i][j];
		p[i][j] = i;
	    }
	}
	for (int k = 0; k < N; k++) {
	    for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++) {
		    if (d[i][k] != M && d[k][j] != M) {
			int can = d[i][k] + d[k][j];
			if (can < d[i][j]) {
			    d[i][j] = can;
			    p[i][j] = p[k][j];
			}
		    }
		}
	}
    }
    /**
     * mを始点としてnまでの最短路を表示
     */
    static void shortestPath(int m, int n) {
	int x;
	if (d[m][n] == M)
	    System.out.println("There is no path");
	else {
	    x = n;
	    stack.push(x);
	    while (x != m) {

		x = p[m][x];
		stack.push(x);
	    }
	    while(!stack.isEmpty())
		System.out.print(stack.pop()+"->");
	    System.out.println("END");
	}
    }
    public static void main(String[] args) {
	if (args.length != 1) {
	    System.err.println("Usage: java ShortestPathDijkstra <出発点>");
	    System.exit(1);
	}

	int p = Integer.parseInt(args[0]);
	if (p >= N) {
	    System.err.println("Error:Argument size is too large");
	    System.exit(1);
	}

	floyd();
	for (int j = 0; j < N; j++) {
	    System.out.print(p+"->"+j+": ");
	    shortestPath(p,j);
	}
    }
}
