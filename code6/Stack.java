public class Stack {
    int length;    // スタックサイズ
    int ptr;       // スタックポインタ
    int stack[];  // スタック本体

    /* 指定した長さの配列を生成するコンストラクタ */
    public Stack(int len) {
	ptr = 0;
	length = len;
	stack = new int[length];
    }

    /* データのポップ */
    public int pop() {
	if (ptr < 0)	
	    System.out.println("スタックが空です");
	return stack[--ptr];
    }

    /* データのプッシュ */
    public void push(int n) {
if (ptr >= length)
	    System.out.println("スタックが満杯です");
	stack[ptr++] = n;
    }
    /* 空かどうか */
    public boolean isEmpty() {
	return ptr <= 0 ? true : false;
    }
    /* display */
    public void display() {
	System.out.println("-*- Stack Display -*-");
	if (isEmpty())
	    System.out.println("Stack is Empty");
	else {
	    for (int i = 0; i < ptr; i++) {
		System.out.print(stack[i]+" ");
	    }
	    System.out.println();
	}
    }
}
