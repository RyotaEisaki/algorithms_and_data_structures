import java.util.Random;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

class StringMatch {
    static boolean isVerbose = false;	// 比較回数表示スイッチ
    static int Ncmp = 0;		// 比較回数のカウンタ

    static char[] text;	// テキスト
    static char[] pat;	// 検索パターン

    /**
     * ファイルを読み込み，内容を文字列として返す．
     * @param path 読み込むファイルのパス
     * @return ファイルの内容の文字列
     */
    public static String readFile(String path) {
	StringBuffer sb = new StringBuffer();
	try {
	    File file = new File(path);
	    BufferedReader br = new BufferedReader(new FileReader(file));
	    String str;
	    while ((str = br.readLine()) != null)
		sb.append(str);
	    br.close();
	} catch(Exception e){
	    System.out.println(e);
	}
	return new String(sb);
    }

    /**
     * 文字比較関数．比較回数をカウントする．
     * @param a 比較文字
     * @param b 比較文字
     * @return a と b が等しければ true, 等しくなければ false.
     */
    public static boolean cmp(char a, char b) {
	if (isVerbose)
	    System.out.println("cmp(" + a + ", " + b + ")");
	Ncmp++;
	if (a == b)
	    return true;
	else
	    return false;
    }

    /**
     * 単純照合法による文字列照合．
     * @param text テキスト
     * @param pat 検索パターン
     * @return 照合に成功した位置．失敗した場合は -1．
     */
    public static int naive(char[] text, char[] pat) {
	int j = 0;
	int i = 0;
	while (j < text.length) {
	    if (!cmp(pat[i], text[j])) {
		j = j - i + 1;
		i = 0;
	    } else {
		if (i == pat.length -1)
		    return j - pat.length + 1;
		else {
		    i++; j++;
		}
	    }
	}
	return -1;
    }

    /** 
     * nextを計算する
     */
    public static int[] compnext(char[] pat) {
	int next[] = new int[pat.length];
	for (int i=0; i < pat.length; i++)
	    next[i] = 0;
	if (pat.length <= 1)
	    return next;
	for (int i=1; i < pat.length; i++) {
	    int j = 0;
	    while (pat[i+j] == pat[j] && i + j < pat.length - 1)
		j++;
	    if (next[i+j] < j + 1 && pat[i+j] != pat[j])
		next[i+j] = j + 1;
	}
	return next;
    }
    /**
     * KMP法による文字列照合．
     * @param text テキスト
     * @param pat 検索パターン
     * @return 照合に成功した位置．失敗した場合は -1．
     */
    public static int kmp(char[] text, char[] pat) {
	int next[] = new int[pat.length];
	
	int i = 0;
	int j = 0;
	next = compnext(pat);
	while (j < text.length) {
	    while (i >= 0 && !cmp(pat[i], text[j]))
		i = next[i] -1;
	    if (i != pat.length -1) {
		i++;
		j++;
	    } else
		return j - pat.length + 1;
	}
	return -1;
    }

    /**
     * オプション課題用
     * テキスト中の位置xを決めそこからm文字をパターンとして取り出し検索を行う.
     * mmax文字までをパターンとする
     * @param text テキスト
     */
    public static void generatepat(char text[]) {
	Random rnd = new Random();
	final int AVE_MAX = 100;            // AVE_MAX回の平均値を取る
	int mmax = text.length;                 // mmax 文字まで
	int total;
	int total2;
	
	for (int m = 1000; m <= mmax; m += 2000) {
		total = 0;
		total2=0;
	    for (int j = 0; j < AVE_MAX; j++) {
		int x = rnd.nextInt(text.length - m);
		char pat[] = new char[m];
		
		int i = 0;
		while (i < m) {
		    pat[i] = text[x+i];
		    i++;
		}
		Ncmp = 0;
		int z = naive(text, pat);
		
		total += Ncmp;
		Ncmp = 0;
		int z2 = kmp(text, pat);
		
		total2 += Ncmp;
	    }
	    // 平均回数の出力 pat文字数 平均値
	    System.out.println((total/AVE_MAX)+" "+(total2/AVE_MAX));
	}
    }
    
    public static void main(String[] args) {
	boolean isTest = false;  // オプション課題用
	// 引数処理
	if (args.length < 2) {
	    System.err.println("Usage: java StringMatch [-v] [-t] [-t2]<text file> <pattern file>");
	    System.exit(1);
	}
	int i = 0;
	if (args[i].equals("-v")) {
	    isVerbose = true;
	    i++;
	} else if (args[i].equals("-t")) {
	    isTest = true;
	    i++;
	} else if (args[i].equals("-t2")) {
	    i++;
	}
	
	// ファイルを読み込んで，char型の配列に格納．
	text = readFile(args[i++]).toCharArray();
	pat = readFile(args[i]).toCharArray();

	if (isVerbose) {
	    System.out.println("text size: " + text.length);
	    System.out.println("pattern size: " + pat.length);
	}
	// オプション課題用
	if (isTest) {
	    System.out.println("text size: " + text.length);
	    generatepat(text);
	    System.exit(0);
	}
	System.out.println("Naive:Pattern found at " + naive(text, pat) + ".");
	if (isVerbose)
	    System.out.println("# of comparisons: " + Ncmp + ".");
	Ncmp = 0;
	System.out.println("KMP:  Pattern found at " + kmp(text,pat)    + ".");
	if (isVerbose) {
	    System.out.println("# of comparisons: " + Ncmp + ".");
	    System.out.println("----------- next -------------");
	    int next[] = compnext(pat);
	    for (int j=0; j < pat.length; j++)
		System.out.print(pat[j]+" ");
	    System.out.println();

	    for (int j=0; j < pat.length; j++)
		System.out.print(next[j]+" ");
	    System.out.println();

	}
    }
}