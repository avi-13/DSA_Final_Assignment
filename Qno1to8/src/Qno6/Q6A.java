package Qno6;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


public class Q6A {
    Character ch;
    Integer freq;
    Q6A left = null;
    Q6A right = null;

    Q6A(Character ch, Integer freq) {
        this.ch = ch;
        this.freq = freq;
    }

    public Q6A(Character ch, Integer freq, Q6A left, Q6A right) {
        this.ch = ch;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }
}

//main class
class HuffmanCode {

    public static void createHuffmanTree(String text) {
        if (text == null || text.length() == 0) {

            return;
        }

        Map<Character, Integer> freq = new HashMap<>();
        for (char c : text.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Q6A> pq = new PriorityQueue<>(Comparator.comparingInt(l -> l.freq));
        for (var entry : freq.entrySet()) {
            pq.add(new Q6A(entry.getKey(), entry.getValue()));
        }

        while (pq.size() != 1) {
            Q6A left = pq.poll();
            Q6A right = pq.poll();
            int sum = left.freq + right.freq;
            pq.add(new Q6A(null, sum, left, right));
        }

        Q6A root = pq.peek();
        Map<Character, String> huffmanCode = new HashMap<>();
        encodeData(root, "", huffmanCode);
        System.out.println("Huffman Codes of the characters are: " + huffmanCode);
        System.out.println("The initial string is: " + text);
        StringBuilder sb = new StringBuilder();

        for (char c : text.toCharArray()) {
            sb.append(huffmanCode.get(c));
        }
        System.out.println("The encoded string is: " + sb);

        System.out.print("The decoded string is: ");

        if (isLeaf(root)) {
            while (root.freq-- > 0) {
                System.out.print(root.ch);
            }
        } else {

            int index = -1;

            while (index < sb.length() - 1) {
                index = decodeData(root, index, sb);
            }
        }
    }

    public static void encodeData(Q6A root, String str, Map<Character, String> huffmanCode) {
        if (root == null) {
            return;
        }
        if (isLeaf(root)) {

            huffmanCode.put(root.ch, str.length() > 0 ? str : "1");
        }
        encodeData(root.left, str + '0', huffmanCode);

        encodeData(root.right, str + '1', huffmanCode);
    }

    public static int decodeData(Q6A root, int index, StringBuilder sb) {
        if (root == null) {
            return index;
        }
        if (isLeaf(root)) {
            System.out.print(root.ch);
            return index;
        }
        index++;

        root = (sb.charAt(index) == '0') ? root.left : root.right;
        index = decodeData(root, index, sb);

        return index;
    }

    public static boolean isLeaf(Q6A root) {

        return root.left == null && root.right == null;

    }

    //driver code
    public static void main(String args[]) {

        String text = "javatpoint";

        createHuffmanTree(text);

    }
}
