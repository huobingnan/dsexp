package algo.tree;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class BiTree<T> {

    // binary linked list
    public static class Node<T> {
        T data;
        Node<T> left, right;
        public Node(T data, Node<T> left, Node<T> right) { this.data = data; this.left = left; this.right = right; }
        public T       getData() { return this.data; }
        public void    setData(T data) { this.data = data; }
        public Node<T> getLeft() { return this.left; }
        public Node<T> getRight() { return this.right; }
    }

    private Node<T> root;

    // construct a binary tree via  level traversal sequence
    @SuppressWarnings({ "unchecked" })
    public static <T> BiTree<T> fromLevelTraversalSeq(T[] seq) {
        if (seq == null || seq.length == 0) return null;
        final Node<T>[] tree = new Node[seq.length]; // auxiliary array
        final BiTree<T> res = new BiTree<>();

        for (int i = 0; i < seq.length; i++) {
            if (seq[i] == null) continue;
            tree[i] = new Node<>(seq[i], null, null);
            if (res.root == null) res.root = tree[i];
            final int parent = Integer.max((i-1) / 2, 0);
            if (i == parent) continue; // root node
            final Node<T> pn = tree[parent];
            if (2 * parent + 1 == i) {
                // left child
                pn.left = tree[i];
            } else {
                pn.right = tree[i];
            }

        }
        return res;
    }

    public void firstOrderTraverse(Consumer<Node<T>> visitor) { doFistOrderTraverseNonRecursion(visitor); }

    public void inOrderTraverse(Consumer<Node<T>> visitor) { doInOrderTraverseNonRecursion(visitor); }

    public void postOrderTraverse(Consumer<Node<T>> visitor) { doPostOrderTraverseNonRecursion(visitor); }

    public void levelTraverse(Consumer<Node<T>> visitor) {
        if (root == null) return;
        final Queue<Node<T>> queue = new ArrayDeque<>(); // auxiliary queue
        queue.add(root);
        while (!queue.isEmpty()) {
            final Node<T> p = queue.poll();
            visitor.accept(p);
            if (p.left != null) queue.add(p.left);
            if (p.right != null) queue.add(p.right);
        }
    }

    // First order traverse non-recursion
    private void doFistOrderTraverseNonRecursion(Consumer<Node<T>> visitor) {
        if (root == null) return; // empty tree
        // auxiliary stack
        final Stack<Node<T>> auxStack = new Stack<>();
        auxStack.push(root);
        while (!auxStack.isEmpty()) {
            final Node<T> node = auxStack.pop();
            visitor.accept(node);
            // push right subtree into auxiliary stack
            if (node.right != null) auxStack.push(node.right);
            if (node.left != null) auxStack.push(node.left);
        }
    }

    private void doInOrderTraverseNonRecursion(Consumer<Node<T>> visitor) {
        if (root == null) return;
        final Stack<Node<T>> auxStack = new Stack<>();
        Node<T> p = root;
        for(;;) {
            // go along left branch -> find the extremely left node
            while (p != null) { auxStack.push(p); p = p.left; }
            if (auxStack.isEmpty()) break;
            p = auxStack.pop(); // this is an extremely left node
            visitor.accept(p);
            // transfer the control to right subtree
            p = p.right;
        }
    }

    private void doPostOrderTraverseNonRecursion(Consumer<Node<T>> visitor) {
        if (root == null) return;
        final Stack<Node<T>> auxStack = new Stack<>();
        Node<T> p = root; Node<T> lastVisited = null; // backtrace check flag
        // go along left branch
        while (p != null) { auxStack.push(p); p = p.left; }
        for (;;) {
            if (auxStack.isEmpty()) break;
            p = auxStack.peek(); // get the top of stack: extremely left node
            if (p.right == null || p.right == lastVisited) {
                // visit
                visitor.accept(p); lastVisited = auxStack.pop();
            } else {
                // transfer the control
                p = p.right;
                // go along left branch
                while (p != null) { auxStack.push(p); p = p.left; }
            }
        }
    }


    static int number = 0;

    static int getNumber() { return number++; }

    public static void main(String... args) {
        final BiTree<String> tree =
                BiTree.<String>fromLevelTraversalSeq(new String[]{"A", "B", "C", null ,"D",  "E", "F"});

        tree.firstOrderTraverse(node -> System.out.printf("%s ", node.getData()));
        System.out.println();

        final BiTree<String> expression =
                BiTree.fromLevelTraversalSeq(new String[]{"+", "*", "/", "a", "b", "c", "d"});
        expression.inOrderTraverse(node -> System.out.printf("%s ", node.getData()));
        System.out.println();
        expression.levelTraverse(node -> System.out.printf("%s ", node.getData()));
        System.out.println();
        expression.postOrderTraverse(node -> System.out.printf("%s ", node.getData()));
        System.out.println();

        final BiTree<String> postExpr =
                BiTree.fromLevelTraversalSeq(new String[]{ "-", "*", "d", "a", "+", null, null, null, null, "b", "c"});
        postExpr.postOrderTraverse(node -> System.out.printf("%s ", node.getData()));
        System.out.println();

        final Integer[] nodes =
                IntStream.range(0, 16).map(x -> 0).mapToObj(Integer::valueOf).toArray(size -> new Integer[size]);

        final BiTree<Integer> n = BiTree.fromLevelTraversalSeq(nodes);
        n.levelTraverse(node -> System.out.printf("%d ", node.getData()));
        System.out.println();

        n.postOrderTraverse(node -> node.setData(getNumber()));

        n.levelTraverse(node -> System.out.printf("%d ", node.getData()));
        System.out.println();

    }
}
