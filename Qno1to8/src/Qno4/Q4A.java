package Qno4;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


//Design and Implement LFU caching
class Q4A {

    private Map<Integer, Node> valueMap = new HashMap<>();
    private Map<Integer, Integer> countMap = new HashMap<>();
    private TreeMap<Integer, DoubleLinkedList> freqMap = new TreeMap<>();
    private final int size;

    public Q4A(int n) {
        size = n;
    }

    public int get(int key) {
        if (!valueMap.containsKey(key) || size == 0) {
            return -1;
        }

        // Remove element from current freq list and move it to the next one in O(1) time
        Node deleteNode = valueMap.get(key);
        Node node = new Node(key, deleteNode.value());
        int freq = countMap.get(key);
        freqMap.get(freq).remove(deleteNode);
        removeIfListEmpty(freq);
        valueMap.remove(key);
        countMap.remove(key);
        valueMap.put(key, node);
        countMap.put(key, freq + 1);
        freqMap.computeIfAbsent(freq + 1, k -> new DoubleLinkedList()).add(node);
        return valueMap.get(key).value;
    }

    public void put(int key, int value) {
        if (!valueMap.containsKey(key) && size > 0) {

            Node node = new Node(key, value);

            if (valueMap.size() == size) {
                int lowestCount = freqMap.firstKey();
                Node deleteNode = freqMap.get(lowestCount).head();
                freqMap.get(lowestCount).remove(deleteNode);
                removeIfListEmpty(lowestCount);

                int keyToDelete = deleteNode.key();
                valueMap.remove(keyToDelete);
                countMap.remove(keyToDelete);
            }
            freqMap.computeIfAbsent(1, k -> new DoubleLinkedList()).add(node);
            valueMap.put(key, node);
            countMap.put(key, 1);

        } else if (size > 0) {
            Node node = new Node(key, value);
            Node deleteNode = valueMap.get(key);
            int freq = countMap.get(key);
            freqMap.get(freq).remove(deleteNode);
            removeIfListEmpty(freq);
            valueMap.remove(key);
            countMap.remove(key);
            valueMap.put(key, node);
            countMap.put(key, freq + 1);
            freqMap.computeIfAbsent(freq + 1, k -> new DoubleLinkedList()).add(node);


        }
    }

    private void removeIfListEmpty(int freq) {
        if (freqMap.get(freq).len() == 0) {
            freqMap.remove(freq);
        }
    }

    private class Node {
        private int key;
        private int value;
        Node next;
        Node prev;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int key() {
            return key;
        }

        public int value() {
            return value;
        }
    }

    private class DoubleLinkedList {
        private int n;
        private Node head;
        private Node tail;

        public void add(Node node) {
            if (head == null) {
                head = node;
            } else {
                tail.next = node;
                node.prev = tail;
            }
            tail = node;
            n++;
        }

        public void remove(Node node) {

            if (node.next == null) tail = node.prev;
            else node.next.prev = node.prev;

            if (node.prev == null) head = node.next;
            else node.prev.next = node.next;

            n--;
        }

        public Node head() {
            return head;
        }

        public int len() {
            return n;
        }
    }

    public static void main(String[] args) {
        Q4A cache = new Q4A(2); // create a cache with capacity of 2

        // add elements to cache
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));    // returns 1
        cache.put(3, 3);                     // evicts key 2
        System.out.println(cache.get(2));    // returns -1 (not found)
        System.out.println(cache.get(3));    // returns 3
        cache.put(4, 4);                     // evicts key 1
        System.out.println(cache.get(1));    // returns -1 (not found)
        System.out.println(cache.get(3));    // returns 3
        System.out.println(cache.get(4));    // returns 4
    }

}