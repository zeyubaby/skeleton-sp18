public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    /**
     * Creates an empty array deque with starting size 8.
     */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }

//    public ArrayDeque(ArrayDeque other) {
//        items = (T[]) new Object[other.items.length];
//        size = 0;
//        nextFirst = 3;
//        nextLast = 4;
//        for (int i = 0; i < other.size; i++) {
//            addLast((T) other.get(i));
//        }
//    }

    /**
     * Resizes the underlying array to the target capacity.
     */

    private void resize() {
        if (size == items.length) {
            expand();
        }
        if (size < (double) items.length / 4 && items.length > 16) {
            reduce();
        }
    }

    private void expand() {
        resizeHelper(items.length * 2, true);
    }

    private void reduce() {
        resizeHelper(items.length / 2, false);
    }

    private void resizeHelper(int capacity, boolean isExpanded) {
        T[] a = (T[]) new Object[capacity];
//        int smaller = nextFirst < nextLast ? nextFirst : nextLast;
//        int bigger = nextFirst > nextLast ? nextFirst : nextLast;
        if (isExpanded) {
            System.arraycopy(items, 0, a, 0, nextLast);
            System.arraycopy(items, nextLast, a, nextLast + capacity / 2, size - nextLast);
            nextFirst = nextFirst + capacity / 2;
        } else {
            System.arraycopy(items, nextFirst + 1, a, 1, size);
            nextFirst = 0;
            nextLast = nextFirst + 1 + size;
        }
        items = a;
    }

    public void addFirst(T item) {
        resize();
        items[nextFirst] = item;
        size++;
        nextFirst = nextFirst - 1 < 0 ? items.length - 1 : nextFirst - 1;
    }

    public void addLast(T item) {
        resize();
        items[nextLast] = item;
        size++;
        nextLast = nextLast + 1 > items.length - 1 ? 0 : nextLast + 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int ptr = nextFirst + 1;
        int currI = 0;
        while (currI < items.length) {
            if (ptr > items.length - 1) {
                ptr = 0;
            }
            System.out.print(items[ptr] + " ");
            ptr++;
            currI++;
        }
        System.out.println();
    }

    public T removeFirst() {
        resize();
        int index = nextFirst + 1 > items.length - 1 ? 0 : nextFirst + 1;
        T first = items[index];
        items[index] = null;
        size -= 1;
        nextFirst = index;
        return first;
    }

    public T removeLast() {
        resize();
        int index = nextLast - 1 < 0 ? items.length - 1 : nextLast - 1;
        T last = items[index];
        items[index] = null;
        size -= 1;
        nextLast = index;
        return last;
    }

    public T get(int index) {
        if (index < 0 || index > items.length - 1) {
            return null;
        } else {
            int arrayI = (nextFirst + 1) + index;
            if (arrayI > items.length - 1) {
                arrayI = arrayI - items.length;
            }
            return items[arrayI];
        }
    }

//    public static void main(String[] args) {
//        System.out.println("Running tests.\n");
//        ArrayDeque<Integer> test = new ArrayDeque<>();
//        test.addLast(9);
//        test.addLast(8);
//        test.addLast(7);
//        test.addLast(6);
//        test.addLast(5);
//        test.addLast(4);
//        test.addLast(3);
//        test.addLast(2);
//        test.addLast(1);
//        test.addLast(0);
//        test.addFirst(9);
//        test.addFirst(8);
//        test.addFirst(7);
//        test.addFirst(6);
//        test.addFirst(5);
//        test.addFirst(4);
//        test.addFirst(3);
//        test.addFirst(2);
//        test.addFirst(1);
//        test.addFirst(0);
//        test.addLast(15);
//        test.addFirst(99);
//        test.addFirst(91);
//        test.addLast(12);
//        test.addLast(13);
//        test.addFirst(20);
//        test.addLast(10);
//        test.addLast(11);
//        test.addFirst(23);
//        test.addFirst(21);
//        //test.printDeque();
//        test.addLast(22);
//        test.addFirst(89);
//        test.addLast(90);
//        test.addLast(1);
//        test.addLast(0);
//        test.addLast(11);
//        test.addFirst(45);
//        for (int i = 0; i < 25; i++) {
//            test.removeLast();
//        }
//        test.addFirst(2);
//        test.printDeque();
//        // test2.printDeque();
//    }
}
