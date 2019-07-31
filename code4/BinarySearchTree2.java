// 削除の実装で使用するデータ型
enum Lr {
    L, R
};

public class BinarySearchTree2 {
    static IntNode2 root;        // 2分探索木の根(nullで初期化)

    // 木構造の表示(2分木の実装メソッドのdisplayを利用)
    public static void display(IntNode2 n) {
	System.out.print(n.val+"#"+n.height);
	System.out.print("(");
	if (n.left == null)
	    System.out.print("null");
	else
	    display(n.left);
	System.out.print("#"+n.left.height+",");
	if (n.right == null)
	    System.out.print("null");
	else
	    display(n.right);
	System.out.print("#"+n.right.height+")");
    }

    // 最小値の探索
    public static int minBst() {
	if (root == null)       // 2分木が空
	    return -1;
	IntNode2 p = root;
	while (p.left != null) {
	    p = p.left;
	}
	return p.val;
    }

    // 値の探索
    public static boolean searchBst(int d) {
	if (root == null)
	    return false;
	IntNode2 p = root;
	while (p != null) {
	    if (p.val == d)
		return true;
	    else if (p.val > d)
		p = p.left;
	    else
		p = p.right;
	}
	return false;
    }

    // 値の挿入
    public static void insertBst(int d) {
	// 根が存在しない場合 = 根にしてしまう
	if (root == null) {
	    root = new IntNode2(d, null, null);
	    return;
	}
	IntNode2 p = root;
	while (true) {
	    if (p.val == d)
		return;
	    if (p.val > d) {
		if (p.left == null) {
		    p.left = new IntNode2(d, null, null);
		    return;
		} else
		    p = p.left;
	    } else {
		if (p.right == null) {
		    p.right = new IntNode2(d, null, null);
		    return;
		} else
		    p = p.right;
	    }
	}
}

    // 値の削除 (削除対象の節点が2つ以上ある場合)
    public static void deleteBst(int d) {
	// 2分探索木が空の場合はそのまま終了する
	if (root == null)
	    return;

	IntNode2 p = root;        // 根に着目
	IntNode2 parent = null;   // 現在着目している節の親を指す
	Lr lr = null;            // 親の左の子か右の子かを表す

	while (p != null) {
	    if (p.val > d) {     // dが着目している節より小さい場合
		parent = p;      // ポインタを左に進める
		p = p.left;
		lr = Lr.L;
	    } else if (p.val < d) { // dが着目している節より大きい場合
		parent = p;         // ポインタを右に進める
		p = p.right;
		lr = Lr.R;
	    } else {                // 見つかった場合
		if (p.left == null || p.right == null) { // 1つ以下の子を持つ
		    deleteBstEl(p, parent, lr);
		    return;
		} else {            // 2つの子を持つ
		    IntNode2 r = p.right;    // p の右部分木の最小値を探す
		    parent = p;
		    lr = Lr.R;              // 右の子である

		    while (r.left != null) {
			parent = r;
			r = r.left;
			lr = Lr.L;
		    }
		    System.out.println("右部分木の最小値:"+r.val);
		    // この段階でrは右部分木の最小節点
		    p.val = r.val;
		    r.val = d;
		    deleteBstEl(r, parent, lr);
		}
	    }
	}
    }

    /*
     * 値の削除 (削除対象の節点が1つ以下の子を持つ場合)
     *
     * @param p      : 削除対象のポインタ
     * @param parent : 削除対象の親を指すポインタ
     * @param lr     : 削除対象が親の左の子か右の子かを表すラベル
     */

    public static void deleteBstEl(IntNode2 p, IntNode2 parent, Lr lr) {
	if (p.left == null && p.right == null) {  // 葉である場合
	    if (parent == null)   // 根である場合
		root = null;
	    else {                // 根ではない場合
		if (lr == Lr.L)   // 親の左の子の場合
		    parent.left = null;
		else
		    parent.right = null;
	    }
	    return;
	}
	// 子を1つ持つ場合
	if (p.left == null || p.right == null) {
	    if (p.left == null) { // 右の子を持つ場合
		if (parent == root)
		    root = p.right;
		else {
		    if (lr == Lr.L)
			parent.left = p.right;
		    else
			parent.right = p.right;
		}
	    } else {
		if (parent == null) // 左の子を持つ場合
		    root = p.left;
		else {
		    if (lr == Lr.L)
			parent.left = p.left;
		    else
			parent.right = p.left;
		}
	    }
	    return;
	}
    }

    public static void main(String[] args) {

	// 値の挿入
	insertBst(10);
	insertBst(15);
	insertBst(18);
	insertBst(6);
	insertBst(12);
	insertBst(20);
  insertBst(19);
	insertBst(9);
	display(root);
	System.out.println();


	int num = 15;
	System.out.println("DELETED:"+num);
	deleteBst(num);
	display(root);
	System.out.println();


	num = 19;
	System.out.println("DELETED:"+num);
	deleteBst(num);
	display(root);
	System.out.println();
    }
}
