public class NodeStack {
    int length;    // スタックサイズ
    int ptr;       // スタックポインタ
    Node stack[];  // スタック本体

    /* 指定した長さの配列を生成するコンストラクタ */
    public NodeStack(int len) {
	ptr = 0;
	length = len;
	stack = new Node[length];
	for (int i=0; i < length; i++)
	    stack[i] = new Node(null,null,null);
    }

    /* データのポップ */
    public Node pop() {
	if (ptr >= length)
	    System.out.println("スタックがいっぱいです");
	return stack[--ptr];
    }

    /* データのプッシュ */
    public void push(Node n) {
	if (ptr < 0)
	    System.out.println("スタックが空です");
	stack[ptr++] = n;
    }
    /* 空かどうか */
    public boolean isEmpty() {
	return ptr <= 0 ? true : false;
    }
}
