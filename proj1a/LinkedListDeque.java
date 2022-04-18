public class LinkedListDeque<T> {
    private class Node {
        public T item;
        public Node prev;
        public Node next;

        public Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    /* create an empty LinkedListDeque */
    public LinkedListDeque() {
        // it does not matter what's the sentinel's item is null/object or ...
        sentinel = new Node(null, null, null);
        size = 0;
    }

    /* create a LinkedListDeque*/
    public LinkedListDeque(T i) {
        sentinel = new Node(null, null, null);
        sentinel.next = new Node(i, sentinel, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    /* add First Node */
    public void addFirst(T item) {
        Node addNode = new Node(item, sentinel, sentinel.next);
        if (sentinel.next != null) {
            sentinel.next.prev = addNode;
        }
        sentinel.next = addNode;
        size += 1;
    }

    public void addLast(T item) {
        Node addNode = new Node(item, sentinel.prev, sentinel);
        if (sentinel.prev != null) {
            sentinel.prev.next = addNode;
        } else {
            addNode.prev = sentinel;
            sentinel.next = addNode;
        }
        sentinel.prev = addNode;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node ptr = sentinel.next;
        while (ptr != sentinel && ptr != null) {
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (sentinel.next == null) {
            return null;
        }
        T removedFirst = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        if (sentinel.next != null) {
            sentinel.next.prev = sentinel;
        }
        size--;
        return removedFirst;
    }

    public T removeLast() {
        if (sentinel.prev == null) {
            return null;
        }
        T removedLast = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        if (sentinel.prev != null) {
            sentinel.prev.next = sentinel;
        }
        size--;
        return removedLast;
    }

    public LinkedListDeque(LinkedListDeque other) {
        //author is professor
        sentinel = new Node(null, null, null);
        size = 0;

        for (int i = 0; i < other.size; i++) {
            addLast((T) other.get(i));
        }
    }

    public T get(int index) {
        Node ptr = sentinel.next;
        int currIndex = 0;
        while (ptr != sentinel && ptr != null) {
            if (currIndex == index) {
                return ptr.item;
            }
            ptr = ptr.next;
            currIndex++;
        }
        return null;
    }

    public T getRecursive(int index) {
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(Node front, int i) {
        if (front == null) {
            return null;
        }
        if (i == 0) {
            return front.item;
        }
        return getRecursiveHelper(front.next, i - 1);
    }


    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        LinkedListDeque<Integer> test = new LinkedListDeque<>();
        System.out.println(test.isEmpty());
        test.addLast(1);
        test.addFirst(12);
        test.addFirst(13);
        test.addLast(20);
        System.out.println(test.isEmpty());
        System.out.println(test.getRecursive(2));
        test.printDeque();
    }

}
