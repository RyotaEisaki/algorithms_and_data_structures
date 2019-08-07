public class QueueArray {
    int length;    // キューのサイズ
    int front;     // 先頭側インデックス
    int rear;      // 末尾側インデックス
    int num;       // キューに入っているデータ数
    int queue[];   // キュー本体

    /* 指定した長さの配列を生成するコンストラクタ */
    public QueueArray(int len) {
	front = rear = num = 0;
	length = len;
	queue = new int[length];
    }

    /* データのエンキュー */
    public void enqueue(int val) {
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
    public int dequeue() {
	if (num <= 0)
	    System.out.println("Queue Underflow !!");
	int x = queue[front++];
	num--;
	if (front == length)
	    front = 0;
	return x;
    }
    /* キュー要素の表示 */
    public void display() {
	for (int i=0; i<num; i++)
	    System.out.print(queue[(front + i) % length]+" ");
	System.out.println();
    }

    /* キューが空ならtrue */
    public boolean isEmpty() {
	return num <=0 ? true : false;
    }
}
