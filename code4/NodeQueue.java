public class NodeQueue {
    int length;    // キューのサイズ
    int front;     // 先頭側インデックス
    int rear;      // 末尾側インデックス
    int num;       // キューに入っているデータ数
    Node queue[];   // キュー本体

    /* 指定した長さの配列を生成するコンストラクタ */
    public NodeQueue(int len) {
	front = rear = num = 0;
	length = len;
	queue = new Node[length];
	for (int i=0; i < length; i++)
	    queue[i] = new Node(null,null,null);
    }

    /* データのエンキュー */
    public void enqueue(Node val) {
	if (num >= length)
	    System.out.println("Queue Overflow !!");
	else {
	    queue[rear++] = val;
	    num++;
	    if (rear == length)
		rear = 0;
	}
    }

    /* データのデキュー */
    public Node dequeue() {
	if (num <= 0)
	    System.out.println("Queue Underflow !!");
	Node x = queue[front++];
	num--;
	if (front == length)
	    front = 0;
	return x;
    }
    /* 空かどうか */
    public boolean isEmpty() {
	return num <= 0 ? true : false;
    }
}
