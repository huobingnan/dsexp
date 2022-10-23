package algo.tree;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Vector;
import java.util.stream.Stream;

public class HuffmanTree<T> {

    private Node<T> root;

    public byte[] encode(T... content) {
        return null;
    }

    static class Node<T> {
        int weight;
        T   value;
        Node<T> left, right;
        Node(int weight, T value) { this.weight = weight; this.value = value; }
    }

    static <T> HuffmanTree<T> of(int[] weights, T... values) {
        if (weights.length != values.length)
            throw new IllegalArgumentException();
        final PriorityQueue<Node<T>> heap =
                new PriorityQueue<>((a, b) -> a.weight - b.weight);
        // construct Node<T>
        for (int i = 0; i < weights.length; i++) {
            heap.add(new Node<>(weights[i], values[i]));
        }
        while (heap.size() > 1)  {
            // a < b
            final Node<T> a = heap.poll();
            final Node<T> b = heap.poll();
            final Node<T> c = new Node<>(a.weight + b.weight, null);
            c.left = a; c.right = b;
            heap.add(c);
        }
        final HuffmanTree<T> tree = new HuffmanTree<>();
        tree.root = heap.poll();
        return tree;
    }

    public static void main(String[] args) {
        final HuffmanTree<Character> tree = of(new int[]{10, 20, 30, 40}, 'a', 'b', 'c', 'd');


    }
}
