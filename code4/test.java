public class test {
  public static void main(String[] args) throws IOException {
    int[] values = { 49, 83, 97, 99, 92, 71, 72, 69, 28, 40, 44, 32, 18, 19, 11 };

    Node<Integer> tree = new Node<>();
    for(int v : values) tree.insertToTree(v);

    tree.printTree(System.out);
  }
}
