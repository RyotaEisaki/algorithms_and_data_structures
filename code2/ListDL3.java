// ListDL.java
public class ListDL {
     private ListDL prev, next;      // 前と次のセルを指す
     int val;                              // セルには整数値を格納

     // コンストラクタ
     ListDL() {
       this.prev=this;
       this.next=this;
      }
     ListDL(int val) {
       this.val=val;
      }

     // セルを挿入、削除する操作を共通化するメソッド
     private static void __Insert(ListDL cell, ListDL prev, ListDL next) {
       cell.prev=prev;
       cell.next=next;
       prev.next=cell;
       next.prev=cell;
     }
     private static void __Delete(ListDL prev, ListDL next) {
       prev.next=next;
       next.prev=prev;
     }

     // 前後のセルへのリンクの初期化
     private void initLinks() {
       this.prev=null;
       this.next=null;
     }

     // このセル (this) の次に cell を挿入
     void insertNext(ListDL cell) {
       __Insert(cell, this, this.next);
     }

     // このセル (this) の前に cell を挿入
     void insertPrev(ListDL cell) {
     __Insert(cell, this.prev, this);
   }

     // このセル (this) をリストから削除
     void delete() {
     if(this.prev==this|| this.next==this||this.prev==null||this.next==null){
       System.err.println("This cell is not linked！！");
       System.exit(1);
     }
    __Delete(this.prev, this.next);
    this.initLinks();
}
     // 与えられた整数 i を保持しているセルを探し、そのセルを返す．
     // 見つからなければ null を返す．
     ListDL search(int i) {
     if(this.val==i) return this;
     ListDL this_next = this.next;
     while(this_next != this) {
       if(this_next.val==i) return this_next;
       this_next=this_next.next;
     }
     return null;
    }

    //配列からリストの要素を読み込むメソッド
    void readFromArray(){

    }

    //リストの要素を書き出した配列を返すメソッド
    void writeToArray(){

    }

    //ファイルからリストの要素を読み込むメソッド
    void readFromFile(){

    }

    //ファイルにリストの要素を書き出すメソッド
    void writeToFile(){

    }
     // リストの要素を順に出力
     void display() {
     if (this.val!=0) System.out.print(this.val +" ");
   ListDL this_next = this.next;
   while(this_next !=this){
     if(this_next.val!=0) System.out.print(this_next.val+ " ");
     this_next =this_next.next;
  }
  System.out.println();
}
}
