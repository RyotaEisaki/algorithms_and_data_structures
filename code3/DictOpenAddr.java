// DictOpenAddr.java
public class DictOpenAddr {
       DictData[] H;       // 辞書の配列
       int B;              // 配列の大きさ

       // コンストラクタ
       DictOpenAddr(int len) {
         B=len;
         H= new DictData[B];
         for (int i =0; i<B; i++){
           H[i]=new DictData();
         }
      }

       // ハッシュ関数
       int h(int d, int count) {
         return (d+count)%B;
      }

       // データ d を辞書に挿入
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
         System.out.println("空きがありません．");
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

       // mainメソッド
       public static void main(String[] args) {
               DictOpenAddr dict = new DictOpenAddr(10);

               dict.insert_hash(1);
               dict.insert_hash(2);
               dict.insert_hash(3);
               dict.insert_hash(11);
               dict.insert_hash(12);
               dict.insert_hash(21);

               dict.display();
               System.out.println(" ");

               System.out.println("Search 1 ...\t" + dict.search_hash(1));

               System.out.println("Search 2 ...\t" + dict.search_hash(2));

               System.out.println("Search 21 ...\t" + dict.search_hash(21));

               System.out.println("Search 5 ...\t" + dict.search_hash(5));


               dict.delete_hash(3);
               dict.display();
               System.out.println(" ");

               dict.delete_hash(11);
               dict.display();
               System.out.println(" ");

       }
}
