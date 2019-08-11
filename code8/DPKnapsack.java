import java.util.Random;

public class DPKnapsack {
    /* 教科書
       v[1] ~ v[4] : 価格
       w[1] ~ w[4] : 重さ
    */
    static int v[] = {0, 250, 380, 420, 520};
    static int w[] = {0,   1,   2,   4,   3};

    /**
     * ナップザック問題の最適解を探索(動的計画法を用いない)
     * @param v 価格
     * @param w 重さ
     * @param k 対象とする荷物の数
     * @param i ナップザックの容量
     */
    public static int knapsack (int v[], int w[], int n, int C) {
	int G[][] = new int[n+1][C+1];
	for (int k = 0; k <= n ; k++) {
	    for (int i = 0; i <= C ; i++) {
		G[k][i] = 0;
	    }
	}
	for (int k = 1; k <=n ; k++) {
	    for (int i = 0; i <= C ; i++) {
		if (i < w[k])
		    G[k][i] = G[k-1][i];
		else {
		    int v1 = G[k-1][i-w[k]] + v[k];
		    if (G[k-1][i] < v1)
			G[k][i] = v1;
		    else
			G[k][i] = G[k-1][i];
		}
	    }
	}
	return G[n][C];
    }

    public static void main(String args[]) {
	if (args.length == 2) {
	    int k = Integer.parseInt(args[0]);
	    int i = Integer.parseInt(args[1]);
	    System.out.println(knapsack(v,w,k,i));
	} else if (args.length == 1) {
            int n = Integer.parseInt(args[0]);
            int [] v = new int[n+1]; 
            int [] w = new int[n+1]; 
            Random rnd = new Random();
            for (int i = 1; i <= n; i++) { 
                v[i] = rnd.nextInt(100);
                w[i] = rnd.nextInt(10)+1;
            }
            long t1 = System.nanoTime(); 
            int i = knapsack(v, w, n, n*5);
            long t2 = System.nanoTime(); 
            System.out.println(i);   
            System.out.println();
            System.out.println(((t2-t1)/1000000)+"ミリ秒");
        } else 
            System.out.println("1〜2個の引数を与えてください");
    }
}