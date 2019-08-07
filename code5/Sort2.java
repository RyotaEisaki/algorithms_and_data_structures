import java.util.Random;
import java.util.Scanner; //テキスト入力を簡単に扱うためのライブラリ

public class Sort {
    static long compare_count = 0;    // 比較回数を計測するためのクラス変数

    // 比較回数のリセット
    static void reset() {
	compare_count = 0;
    }

    // i と j を比較
    static int compare(int i, int j) {
	compare_count++;

	if (i < j)       return -1;
	else if (i == j) return 0;
	else             return 1;
    }

    // 配列の要素を交換
    static void swap(int a[], int i, int j) {
	int tmp = a[i];
	a[i] = a[j];
	a[j] = tmp;
    }

    // 配列の要素を表示
    static void display(int a[], int n) {
	for (int i = 0; i < n; i++)
	    System.out.print(a[i]+" ");
	System.out.println();
    }

    // 選択ソート
    static void selectionSort(int a[], int n) {
	for (int i = 0; i < n-1; i++) {
	    int min = i;
	    for (int j = i+1; j < n; j++) {
		if (compare(a[j], a[min]) == -1)
		    min = j;
	    }
	    swap(a,i,min);
    }
    }

    // 挿入ソート
    static void insertionSort(int a[], int n) {
	for (int i = 1; i < n; i++) {
	    int j;
	    int tmp = a[i];
	    for (j = i; j > 0 && compare(a[j-1], tmp) >= 1; j--) {
		a[j] = a[j-1];
	    }
	    a[j] = tmp;
	}
    }
    // ヒープソート
    // i : 着目している節点のインデックス, n:ヒープのデータ総数
    static void siftDown(int a[], int i, int n) {
	int snode,sval;
	while (2*i+1 <= n-1) {
	    if (2*i+1 == n-1) {
		snode = 2*i+1; sval = a[2*i+1];
	    } else {
		if (compare(a[2*i+1], a[2*i+2]) == 1) {
		    snode = 2*i+1; sval = a[2*i+1];
		} else {
		    snode = 2*i+2; sval = a[2*i+2];
		}
	    }
	    if (compare(a[i],sval) == -1) {
		//  System.out.println("交換:着目ノード:a["+i+"]:"+a[i]+",子:a["+snode+"]:"+sval);
		swap(a,i,snode);
		i = snode;
	    } else
		return;
	}
	return;
    }

    static void buildHeap(int a[], int n) {
	for (int i = n/2-1;i>= 0; i--) {
	    // 動作の詳細
	    // System.out.println("Build Heap");
	    // for (int j = 0; j < n; j++) {
	    // 	if (j == i)
	    // 	    System.out.print("["+a[j]+"] ");
	    // 	else
	    // 	    System.out.print(a[j]+" ");
	    // }
	    // System.out.println();
	    siftDown(a, i, n);
	}
    }

    static void heapSort(int a[], int n) {
	buildHeap(a,n);
	for (int m = n-1; m > 0; m--) {
	    swap(a, 0, m);
	    siftDown(a, 0, m);
	}
    }

    // クイックソート
    // partitionにおけるピボットは最後の要素(a[right])とする
    static int partition(int a[], int pivot, int left, int right) {
	swap(a,pivot,right);
	int l = left;
	int r = right -1;

	while (true) {
	    while (compare(a[l], a[right]) < 0)
		l++;
	    while (l <= r && compare(a[r], a[right]) >= 0)
		r--;

	    if (l < r) {
		// 動作の詳細
		// for (int i = 0; i < a.length; i++) {
		//     if (i == l || i == r)
		// 	System.out.print("("+a[i]+") ");
		//     else if (i == right)
		// 	System.out.print("["+a[i]+"] ");
		//     else
		// 	System.out.print(a[i]+" ");
		// }
		// System.out.println();
		swap(a,l,r);
	    }
	    else
		break;
	}
	// for (int i = 0; i < a.length; i++) {
	//     if (i == l)
	// 	System.out.print("("+a[i]+") ");
	//     else if (i == right)
	// 	System.out.print("["+a[i]+"] ");
	//     else
	// 	System.out.print(a[i]+" ");
	// }
	// System.out.println();
	swap(a,l,right);
	return l;
    }
    static void quickSort(int a[], int left, int right) {
	if (left < right) {
	    int pivot = right;
	    int p = partition(a, pivot,left, right);

	    quickSort(a,left, p-1);
	    quickSort(a,p+1, right);
	}
    }

    static void qsort(int a[], int n) {
	quickSort(a, 0, n-1);
    }
    // 待ち行列の配列を表示
    static void displayQueueArray(QueueArray[] b, int n) {
	System.out.println("----------------");
	for (int i=0; i<n; i++) {
	    System.out.print("b["+i+"]:");
	    b[i].display();
	}
    }

    // 基数ソート a[i]は0 <= a[i] < 10^k とする
    static void radixSort(int a[], int n, int k) {
	QueueArray[] b = new QueueArray[10];
	int buf[] = new int[n];
	boolean flag = true;

	int divisor = 1;

	for (int i = 0; i < 10; i++) {
	    b[i] = new QueueArray(n+1);
	}

	while(flag) {
	    flag = false;
	    for (int i=0; i < n; i++) {
		int hash = (a[i] / divisor) % 10;
		if (hash > 0)
		    flag  = true;
		b[hash].enqueue(a[i]);
	    }
	    divisor *= 10;
	    if (flag == true)
		displayQueueArray(b,10);
	    int i = 0;
	    for (int j=0; j < 10; j++) {
		while (!b[j].isEmpty()) {
		    a[i++] = b[j].dequeue();
		}
	    }

	}
    }

    public static void testRadix() {
	System.out.println("Radix Sort test");
	int a[] = {143, 322, 246, 755, 123, 563, 514, 522};
	radixSort(a,a.length,2);
	display(a,a.length);
	System.out.println();
    }

    public static void testSort(int a[], String option) {
	switch (option) {
	case "--i":
	    insertionSort(a,a.length);
	    break;
	case "--h":
	    heapSort(a,a.length);
	    break;
	case "--q":
	    qsort(a,a.length);
	    break;
	default:
	    helpMessage();
	    System.out.println("Argument parsing error, program exit");
	    System.exit(1);
	}
    }
    public static long testSortTimeCount(int a[], String option) {
	long start = System.nanoTime();
	testSort(a,option);
	long end = System.nanoTime();
	return (end-start)/1000000;
    }

    private static void helpMessage() {
	System.out.println("Error: unknown support argument");
	System.out.println("Usage: java Sort [--sort] [num]");
	System.out.println("[--sort]:--i Insertion Sort");
	System.out.println("        :--h Heap Sort");
	System.out.println("        :--q Quick Sort");
	System.out.println("        :--r Radix Sort (using test sample)");
    }
    public static void main(String[] args) {
	// 引数オプションの設定
	// Usage java -sorttype [num]
	if (args.length > 0) {
	    // 乱数を生成する
	    if (args.length == 1) {
		if (args[0].equals("--r")) {
		    testRadix();
		} else {
		    for (int j=1; j <=10; j++) { // 大きさn (n=1000, 2000, ..., 10000) の配列に対してテスト
			int n = 10000 * j;
			int a[] = new int[n];
			Random r = new Random();
			for (int i = 0; i < n; i++) {
			    a[i] = r.nextInt(n*10); // 0〜配列の大きさ*10 -1 のランダムな整数を要素とする
			}
			testSort(a,args[0]);
			System.out.print(a.length);
			System.out.println(" "+compare_count); // 比較回数を表示
			//System.out.println(" "+testSortTimeCount(a,args[0]));
			reset(); // 比較回数をリセット
		    }
		}
	    } else if (args.length == 2) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(args[1]);
		int a[] = new int[n];
		System.out.println(n+"個の整数を入力してください");
		for (int i = 0; i < n; i++) {
		    a[i] = sc.nextInt(); //整数を入力する
		}
		testSort(a,args[0]);
		System.out.println("整列結果");
		display(a,n);
		System.out.println("比較回数 "+compare_count);
		reset();
	    } else {
		helpMessage();
	    }
	} else {
	    helpMessage();
	}
    }
}
