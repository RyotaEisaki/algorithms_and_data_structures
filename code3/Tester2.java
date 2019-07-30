//擬似乱数を生成するメソッドを提供するクラスRamdomを使用するために記述．
import java.util.Random;
public class Tester2 {
    public static void main(String[] args) {
	final int DICT_SIZE = 5000;                     // 辞書の大きさ
	final int MAX_DATA  = 1000000;                  // データサイズ上限値
	final int INSERTED  = (int)(DICT_SIZE * 0.5);           // 挿入数

	final int N = 10;                                 // 測定回数
	long time[] = new long[N];
	Random rnd = new Random();

//線形走査方を使用したプログラムについて
	System.out.println("LinerSearch.java");
	System.out.println("-----------------------------");
	for (int j = 0; j < N; j++) {
	    LinerSearch linersearch = new LinerSearch(DICT_SIZE);
	    int inserted[] = new int[INSERTED];

	    for (int i=0; i< INSERTED; i++) {
		inserted[i] = rnd.nextInt(MAX_DATA + 1);
	  linersearch.insert_hash(inserted[i]);
	    }


	    for (int i = 0; i < DICT_SIZE * 0.3; i++) {
		int num, r;
		if (Math.random() >= 0.5) {
		    r = rnd.nextInt(INSERTED);
        long start = System.nanoTime();
		    num = linersearch.search_hash(inserted[r]);
        long end = System.nanoTime();
  	    time[j] = end - start;
		} else {
		    r = rnd.nextInt(MAX_DATA + 1);
          long start = System.nanoTime();
		    num = linersearch.search_hash(r);
        long end = System.nanoTime();
        time[j] = end - start;
		}
  }


	    System.out.println("No."+(j+1)+": "+time[j]+" nsec");
	}
	long total = 0;
	for (int i=0; i< N; i++) {
	    total += time[i];
	}

	System.out.println("Dictionary Size:"+DICT_SIZE+": Elements:"+INSERTED);
	System.out.println(N+" times Average:"+ total / N+" nsec");
	System.out.println("-----------------------------");


	// 2重ハッシュ法(NoShift)について
	System.out.println("Double_NoShift.java");
	for (int j=0; j < N; j++) {
	    Double_NoShift double_noshift = new Double_NoShift(DICT_SIZE);
	    int inserted[] = new int[INSERTED];

	    for (int i=0; i< INSERTED; i++) {
		inserted[i] = rnd.nextInt(MAX_DATA + 1);
		double_noshift.insert_hash(inserted[i]);
	    }


	    for (int i = 0; i < DICT_SIZE * 0.3; i++) {
		int num, r;
		if (Math.random() >= 0.5) {
		    r = rnd.nextInt(INSERTED);
        long start = System.nanoTime();
		    num = double_noshift.search_hash(inserted[r]);
        long end = System.nanoTime();
       time[j] = end - start;
		} else {
		    r = rnd.nextInt(MAX_DATA + 1);
        long start = System.nanoTime();
		    num = double_noshift.search_hash(r);
        long end = System.nanoTime();
       time[j] = end - start;
		}
	    }


	    System.out.println("No."+(j+1)+": "+time[j]+" nsec");
	}
	total = 0;
	for (int i=0; i< N; i++) {
	    total += time[i];
	}

	System.out.println("Dictionary Size:"+DICT_SIZE+": Elements:"+INSERTED);
	System.out.println(N+" times Average:"+ total / N+" nsec");
	System.out.println("-----------------------------");

    // 2重ハッシュ法(シフト演算)にういて
  	System.out.println("Doube_Shift.java");
  	for (int j=0; j < N; j++) {
  	    Double_Shift double_shift = new Double_Shift(DICT_SIZE);
  	    int inserted[] = new int[INSERTED];

  	    for (int i=0; i< INSERTED; i++) {
  		inserted[i] = rnd.nextInt(MAX_DATA + 1);
  		double_shift.insert_hash(inserted[i]);
  	    }


  	    for (int i = 0; i < DICT_SIZE * 0.3; i++) {
  		int num, r;
  		if (Math.random() >= 0.5) {
  		    r = rnd.nextInt(INSERTED);
           long start = System.nanoTime();
  		    num = double_shift.search_hash(inserted[r]);
          long end = System.nanoTime();
    	    time[j] = end - start;
  		} else {
  		    r = rnd.nextInt(MAX_DATA + 1);
           long start = System.nanoTime();
  		    num = double_shift.search_hash(r);
          long end = System.nanoTime();
    	    time[j] = end - start;
  		}
  	    }


  	    System.out.println("No."+(j+1)+": "+time[j]+" nsec");
  	}
  	total = 0;
  	for (int i=0; i< N; i++) {
  	    total += time[i];
  	}

  	System.out.println("Dictionary Size:"+DICT_SIZE+": Elements:"+INSERTED);
  	System.out.println(N+" times Average:"+ total / N+" nsec");
  	System.out.println("-----------------------------");
}
}
