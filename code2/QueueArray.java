public class QueueArray {
     int length, front, rear;
     int[] queue;

     // 指定された長さの配列を生成するコンストラクタ
    QueueArray(int len) {
    queue = new int[len];
    length = len;
    front = 0;
    rear = 0;
     }

     // データのエンキュー
   void enqueue(int val) {
   int rearnext;
   rearnext =rear+1;
   if (rearnext != length){
     if (rearnext==front){
     System.err.println("Queue Overflow !!");
     System.exit(1);
   } else {
     queue[rear]=val;
     rear = rear+1;
   }
 }
    else {
      if (rearnext==front){
      System.err.println("Queue Overflow !!");
      System.exit(1);
    } else{
       queue[rear]=val;
       rear = 0;
       }
     }
   }

     // データのデキュー
    int dequeue() {
    int x;
    if(front == rear){
    System.err.println("Queue Underflow !!");
    System.exit(1);
  }
    int frontnext;
    frontnext=front+1;
    if (frontnext != length){
    x = queue[front];
    front=front+1;
    return x;
    }
    else {
      x = queue[front];
      front=0;
      return x;
    }
  }

     // キューの要素の表示
     void display(){
     for(int i = 0; i < length; i++)
     {
       System.out.print(queue[i]+" ");
     }
    }
     // main メソッド
     public static void main(String[] args) {
           QueueArray queue = new QueueArray(3);

           queue.enqueue(1);
           queue.enqueue(2);

           queue.display();
           System.out.println(" ");

           System.out.println(queue.dequeue());
           System.out.println(queue.dequeue());

     }
}
