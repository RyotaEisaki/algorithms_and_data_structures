// ListDLmain.java
// リストの実装をテストするためのクラス
public class ListDLmain2 {
     public static void main(String[] args) {
           ListDL2 head = new ListDL2();            // ダミーセルの生成
           ListDLInt elem;

           head.insertNext(new ListDL2(2));      // セルの先頭への追加
           head.insertNext(new ListDL2(1));
           head.insertPrev(new ListDL2(5));      // セルの末尾への追加
           head.display();                      // リストの表示

           elem = head.search(2);               // セルを探す
           elem.insertNext(new ListDL2(3));      // 探したセルの直後にセルを追加
           head.display();

           elem = head.search(5);               // セルを探す
           elem.delete();                       // 探したセルを削除
           head.display();
     }
}
