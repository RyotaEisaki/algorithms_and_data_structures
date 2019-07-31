public class IntNodeWithHeight {
    int val;
    IntNodeWithHeight left, right;
    int height;

    // 子供の木の高さの情報からこの節の高さを再設定
    public void resetHeight() {
	this.height = Math.max(height(left), height(right)) +1;
    }

    // 高さの情報を返す なおnがnullのときは0を返す
    public static int height(IntNode n) {
	return n == null ? 0 : n.height;
    }

    public IntNode (int val, IntNodeWithHeight left, IntNodeWithHeight right) {
	this.val = val;
	this.left = left;
	this.right = right;
	this.height = Math.max(height(left), height(right)) +1;
    }
}
