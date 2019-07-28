// ListDLmain.java
// リストの実装をテストするためのクラス
public class ListDLmain {
     public static void main(String[] args) {
           ListDL head = new ListDL();            // ダミーセルの生成
           ListDL elem;

           head.insertNext(new ListDL(2));      // セルの先頭への追加
           head.insertNext(new ListDL(1));
           head.insertPrev(new ListDL(5));      // セルの末尾への追加
           head.display();                      // リストの表示

           elem = head.search(2);               // セルを探す
           elem.insertNext(new ListDL(3));      // 探したセルの直後にセルを追加
           head.display();

           elem = head.search(5);               // セルを探す
           elem.delete();                       // 探したセルを削除
           head.display();
     }
}
