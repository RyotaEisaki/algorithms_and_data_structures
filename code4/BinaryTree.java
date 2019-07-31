// BinaryTree.java
public class BinaryTree {
    // 行きがけ順のなぞり
    public static void preorder(Node n) {
	System.out.print(n.val+" ");
	if (n.left != null) {
	    preorder(n.left);
	}
	if (n.right != null) {
	    preorder(n.right);
	}
    }

    // 通りがけ順のなぞり
    public static void inorder(Node n) {
	if (n.left != null) {
	    inorder(n.left);
	}
	System.out.print(n.val+" ");
	if (n.right != null) {
	    inorder(n.right);
	}
    }

    // 帰りがけ順のなぞり
    public static void postorder(Node n) {
	if (n.left != null) {
	    postorder(n.left);
	}
	if (n.right != null) {
	    postorder(n.right);
	}
	System.out.print(n.val+" ");
    }

    // 木構造の表示
    public static void display(Node n) {
	System.out.print(n.val);
	System.out.print("(");
	if (n.left == null)
	    System.out.print("null");
	else
	    display(n.left);
	System.out.print(",");
	if (n.right == null)
	    System.out.print("null");
	else
	    display(n.right);

	System.out.print(")");
    }

    // 幅優先探索
    public static void breadth_first_search(Node n) {
	NodeQueue q = new NodeQueue(100);
	System.out.print("Breadth first search:");
	q.enqueue(n);

	while (!q.isEmpty()) {
	    Node out = q.dequeue();
	    System.out.print(out.val+" ");
	    if (out.left != null)
	     	q.enqueue(out.left);
	    if (out.right != null)
	     	q.enqueue(out.right);
	}
	System.out.println();
    }

    // 木の高さ；ただし，教科書の定義の高さ＋１とする．nullの高さが0，根のみの高さが1
    public static int height(Node n) {
	int l = 0, r = 0;
	if (n == null)
	    return 0;
	l = height(n.left);
	r = height(n.right);
	return (l > r) ? l + 1 : r + 1;
    }

	// 木構造の構築
    public static void test1() {
	Node f = new Node("F", null, null);
	Node i = new Node("I", null, null);
	Node d = new Node("D", f,    null);
	Node g = new Node("G", null, null);
	Node a = new Node("A", i,    d);
	Node l = new Node("L", null, g);
	Node c = new Node("C", a,    l);

	System.out.println("test1");
	preorder(c);
	System.out.println("[END](PREORDER)");
	inorder(c);
	System.out.println("[END](INORDER)");
	postorder(c);
	System.out.println("[END](POSTORDER)");
	System.out.println("Tree Height:"+height(c));
	breadth_first_search(c);
	display(c);
	System.out.println();
    }

    public static void test2() {
	Node d = new Node("D", null, null);
	Node i = new Node("I", null, null);
	Node e = new Node("E", null, i);
	Node c = new Node("C", d,    e);
	Node h = new Node("H", null, null);
	Node g = new Node("G", null, null);
	Node f = new Node("F", h,    g);
	Node b = new Node("B", f,    null);
	Node a = new Node("A", c,    b);

	System.out.println("test2");
	preorder(a);
	System.out.println("[END](PREORDER)");
	inorder(a);
	System.out.println("[END](INORDER)");
	postorder(a);
	System.out.println("[END](POSTORDER)");
	System.out.println("Tree Height:"+height(a));
	breadth_first_search(a);
	display(a);
	System.out.println();

    }
    public static void main(String[] args) {
	test1();
	test2();
    }
}
