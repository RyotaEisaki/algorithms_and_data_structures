public class Double_NoShift {
    DictData[] H;              // 辞書の配列
    int B;                     // 配列の大きさ

    Double_NoShift(int len) {
	B = len;
	H = new DictData[B];
	for (int i = 0; i < B; i++) {
	    H[i] = new DictData();
	}
    }

    // ハッシュ関数
    // Multiplication Method (計算時間が非常に大きくなる)
    int hash1(int d) {
      //Let A be some random-looking real number.
      //Knuth suggests M = 0.5*(sqrt(5) - 1).
    	double A = 0.5 * (Math.sqrt(5) -1);
      //s = k*A
      //x = fractional part of s
    	double s = (d * A) % 1;
      // h(k) = floor(m*x)
    	return (int)Math.floor((double)B * s);
    }
    int hash2(int d) {
	    return d % 7 + 1;
    }
    int h(int d, int count) {
	int hx = hash1(d);
	int gx = hash2(d);
	return (hx + count * gx) % B;
    }
    // データdを辞書に挿入
    void insert_hash(int d) {
      if (search_hash(d) !=-1) return; //dが格納済みなら終了
      int count = 0; int b=h(d,count); //ハッシュ値の計算(初回)
      int init_b=b; //ハッシュ値の初期化
      do{
        if(H[b].state==State.EMPTY || H[b].state==State.DELETED){
          //空セル(EMPTYかDELETED)に到達
          H[b].name=d; //そのセルにdを格納
          H[b].state=State.OCCUPIED; //セルの状態をOCCUPIEDに変更
          return;
        }
        count = count+1; b=h(d,count); //再ハッシュ
      }while (b !=init_b);
      return;
   }

   // データ d が辞書内に含まれるかを探索（戻り値はboolean型でも可）
   int search_hash(int d) {
     int count =0; int b=h(d,count); //ハッシュ値の計算(初回)
     int init_b=b; //ハッシュ値の初期化
     do{
       if (H[b].state==State.OCCUPIED){
         if(H[b].name==d) return b; //dをセル中に発見(探索成功)
       }
       else if (H[b].state==State.EMPTY) //空セルに到達(該当データなし)
       return -1;  //FALSE(-1)を出力
       //調べたセルに他のデータが入っているか, DELETEDの場合
       count =count+1; b=h(d,count); //再ハッシュ
     }  while (b!=init_b);
     return -1; //ハッシュ値が初期値と等しい時，無限ループを防ぐため
                //に終了
   }

   // データ d を辞書から削除
   void delete_hash(int d) {
     int b=search_hash(d);
     if (b==-1) return; //該当するデータがないなら終了
     H[b].state=State.DELETED; return; //セルの状態をDELETEDに変更
   }


    // 配列要素の表示
    void display() {
      for(int i = 0; i < B; i++) {
         if(H[i].state==State.OCCUPIED){
        System.out.print(H[i].name+" ");
      }else if(H[i].state==State.EMPTY){
        System.out.print("e ");
      }else if(H[i].state== State.DELETED){
        System.out.print("d ");
      }
    }
  }
}
