import java.util.*;

public class TwoWayLinkedList<E> extends AbstractSequentialList<E> {
    private Node<E> head, tail;
    private int size = 0;

    // Inner node class
    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> previous;

        public Node(E e) {
            element = e;
        }
    }

    public TwoWayLinkedList() {}

    public int size() {
        return size;
    }

    public void add(int index, E e) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();

        if (index == 0) {
            Node<E> newNode = new Node<>(e);
            newNode.next = head;

            if (head != null) head.previous = newNode;
            head = newNode;
            if (tail == null) tail = newNode;
        } else if (index == size) {
            Node<E> newNode = new Node<>(e);
            newNode.previous = tail;

            if (tail != null) tail.next = newNode;
            tail = newNode;
            if (head == null) head = newNode;
        } else {
            Node<E> current = getNode(index);
            Node<E> newNode = new Node<>(e);

            newNode.previous = current.previous;
            newNode.next = current;

            current.previous.next = newNode;
            current.previous = newNode;
        }

        size++;
    }

    public E get(int index) {
        return getNode(index).element;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();

        Node<E> removed = getNode(index);

        if (removed.previous != null)
            removed.previous.next = removed.next;
        else
            head = removed.next;

        if (removed.next != null)
            removed.next.previous = removed.previous;
        else
            tail = removed.previous;

        size--;
        return removed.element;
    }

    private Node<E> getNode(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node<E> current;

        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++)
                current = current.next;
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--)
                current = current.previous;
        }

        return current;
    }

    public ListIterator<E> listIterator(int index) {
        return new TwoWayListIterator(index);
    }

    public ListIterator<E> listIterator() {
        return new TwoWayListIterator(0);
    }

    private class TwoWayListIterator implements ListIterator<E> {
        private Node<E> current;
        private int currentIndex;

        TwoWayListIterator(int index) {
            currentIndex = index;
            current = (index == size) ? null : getNode(index);
        }

        public boolean hasNext() {
            return currentIndex < size;
        }

        public E next() {
            E e = current.element;
            current = current.next;
            currentIndex++;
            return e;
        }

        public boolean hasPrevious() {
            return currentIndex > 0;
        }

        public E previous() {
            current = (current == null) ? tail : current.previous;
            currentIndex--;
            return current.element;
        }

        public int nextIndex() {
            return currentIndex;
        }

        public int previousIndex() {
            return currentIndex - 1;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public void set(E e) {
            throw new UnsupportedOperationException();
        }

        public void add(E e) {
            throw new UnsupportedOperationException();
        }
    }
}
