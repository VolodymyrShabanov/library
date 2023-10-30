package lib;
/*
@date 19.10.2023
@author Sergey Bugaienko
@version 0.2
*/



import java.lang.reflect.Array;
import java.util.Iterator;

public class MyLinkedList<T> implements MyList<T>, Queue<T>, Iterable<T> {

    private int size;
    private Node<T> first;
    private Node<T> last;

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {
        Node <T> item;

        public MyIterator() {
            this.item = first;
        }
        @Override
        public boolean hasNext() {
            return item != null;
        }

        @Override
        public T next() {
            T value = item.value;
            item = item.next;
            return value;
        }


    }


    // [1,2,3,4]

    // метод добавляет элемент в конец списка
    public void add(T value) {
        if (first == null) {
            // не существует ни одного узла
            first = new Node<>(value, null, null);
        } else if (last == null) {
            // существует только один узел (first)
            last = new Node<>(value, first, null);
            // следующий узел за first будет равен last
            first.next = last;
        } else {
            Node<T> temp = last;
//            System.out.println(temp.previous);
//            System.out.println(first.next);
            last = new Node<>(value, temp, null);
            temp.next = last;

//            System.out.println("First: " + first);
//            System.out.println("last: " + last);
//            System.out.println("temp: " + temp);
//            System.out.println("=================");
        }
        size++;
    }


    // @Override
    public void addLast(T value) {
        add(value);
    }

    // Добавить элемент в начало списка
    // @Override
    public void addFirst(T value) {

        if (first == null) {
            // Если список пустой
            first = new Node<>(value, null, null);
        } else if (last == null) {
            // кейс: есть только 1 нода.
            last = first;
            first = new Node<>(value, null, last);
            last.previous = first;
        } else {
            // остальные состояния списка
            Node<T> temp = first;
            first = new Node<>(value, null, temp);
            temp.previous = first;
        }
        size++;
    }

    // @Override
    public boolean contains(T value) {
        // если вызвать indexOf со значением, которое есть в списке - вернется какой-то положительный индекс
        // если вызвать indexOf со значением, которое нет в списке - вернется -1.
        // следовательно, если возвращенное значение не равно -1 -> элемент с таким значением есть в списке
        return indexOf(value) != -1;
    }

    //@Override
    public T get(int index) {
        // size = 3, maxIndex = 2; get(3) // index >= size
        if (index < 0 || index >= size) return null;

        Node<T> cursor = searchNodeByIndex(index);


        return cursor.value;
    }

    private Node<T> searchNodeByIndex(int index) {
        Node<T> cursor;
        if (index <= size / 2) {
//            System.out.println("Get if");
            cursor = first;
            for (int i = 0; i < index; i++) {
                cursor = cursor.next;
            }
        } else {
//            System.out.println("Get else");
            cursor = last;
            for (int i = size - 1; i < index; i++) {
                cursor = cursor.previous;
            }
        }
        return cursor;
    }

    // @Override
    public boolean remove(T value) {
        Node<T> cursor = first;
        while (cursor != null) {
            if (cursor.value.equals(value)) {
                removeNode(cursor);
                return true;
            }
            cursor = cursor.next;
        }
        return false;
    }

    private void removeNode(Node<T> node) {
        if (node == first) {
            remove();
            return;
        }
        if (node == last) {
            removeLast();
            return;
        }
        node.previous.next = node.next;
        node.next.previous = node.previous;
        // не обязательно
        node = null;
        size--;

    }

    // @Override
    public T remove(int index) {
        if (index < 0 || index >= size) return null;

        Node<T> cursor = searchNodeByIndex(index);

        T value = cursor.value;

        removeNode(cursor);

        return value;
    }


    @Override
    public T[] toArray() {

        if (first == null) return (T[])new Object[0];
//        T[] result = (T[]) new Object[size];
        T[] result = (T[]) Array.newInstance(first.value.getClass(), size);
        Node<T> cursor = first;
        int index = 0;
        while (cursor != null) {
            result[index++] = cursor.value;
            cursor = cursor.next;
        }

        return result;
    }

    // Удалить самый(первый) левый узел
    // @Override
    public T remove() {
        if (size == 0) return null;
        // если в списке есть ноды, то удаляться будет нода first
        // записываем ее значение для возврата из метода
        T value = first.value;
        if (size == 1) {
            // Если в списке только 1 нода (first)
            first = null;

        } else if (size == 2) {
            // в списке 2 ноды (first и last)
            first = last;
            first.previous = null;
            last = null;
        } else {
            // больше 2х нод
            first = first.next;
            first.previous = null;
        }

        size--;
        return value;
    }

    //@Override
    public T removeFirst() {
        return remove();
    }

    // @Override
    public T removeLast() {
        if (size == 0) return null;
        T value;
        if (size == 1) {
            // только нода first
            value = first.value;
            first = null;
        } // сценарии, в которых мы удаляем ноду last
        else if (size == 2) {
            // есть две ноды
            value = last.value;
            last = null;
            first.next = null;
        } else {
            value = last.value;
            last = last.previous;
            last.next = null;
        }


        size--;
        return value;
    }

    //@Override
    public T getFirst() {
        return (first == null ? null : first.value);
    }

    //@Override
    public T getLast() {
        //TODO переписать. Если в листе только 1 элемент - вернуть его значение
        T value = null;
        if (last != null) {
            value = last.value;
        }
        return value;
    }

    //    @Override
    public void addAll(T... values) {
        for (T value : values) {
            add(value);
        }
    }

    //    @Override
    public int size() {
        return size;
    }

    //@Override
    public boolean isEmpty() {
        return size == 0;
    }

    //    @Override
    public int indexOf(T value) {
        int index = 0;
        // перебираем список, начиная с головы
        Node<T> cursor = first;
        while (cursor != null) {
            if (cursor.value.equals(value)) {
                return index;
            }
            index++;
            cursor = cursor.next;
        }
        return -1;
    }

    public int lastIndexOfV2(T value) {
        int result = -1;
        int index = 0;
        Node<T> cursor = first;
        while (cursor != null) {
            if (cursor.value.equals(value)) {
                result = index;

            }
            index++;
            cursor = cursor.next;
        }

        return result;
    }


    // Индекс последнего (самого правого) вхождения элемента
    //@Override
    public int lastIndexOf(T value) {
        if (size == 0) return -1;
        int index = size - 1;
        Node<T> cursor = last;
        while (cursor != null) {
            if (cursor.value.equals(value)) {
                return index;
            }
            index--;
            cursor = cursor.previous;
        }

        // обрабатываем кейс - когда у нас только 1 элемент
        if (size == 1) {
            if (first.value.equals(value)) {
                return 0;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        if (first != null) {
            sb.append(first.value);
            Node<T> cursor = first.next;
            while (cursor != null) {
                sb.append(", ");
                sb.append(cursor.value);
                cursor = cursor.next;
            }
        }

        sb.append("]");
        return sb.toString();
    }

    private static class Node<T> {
        T value;
        Node<T> previous;
        Node<T> next;

        public Node(T value, Node<T> previous, Node<T> next) {
            this.value = value;
            this.previous = previous;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + (value != null ? value : "null") +
                    ", previous=" + (previous != null ? previous.value : "null") +
                    ", next=" + (next != null ? next.value : "null") +
                    '}';
        }
    }
}