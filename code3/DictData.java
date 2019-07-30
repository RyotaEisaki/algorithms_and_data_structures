// DictData.java
// 状態を表すための定数
enum State {
       EMPTY, DELETED, OCCUPIED
};

public class DictData {
       int     name;        // データ
       State   state;       // 状態
       // コンストラクタ
       DictData() {
         state = State.EMPTY;

        }
      }
