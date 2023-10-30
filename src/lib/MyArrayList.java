package lib;
/*
@date 17.10.2023
@author Sergey Bugaienko
*/

import java.util.Iterator;
import java.util.Random;

public class MyArrayList<T> implements MyList<T>, Iterable<T> {
    private T[] array;
    private int cursor;
    static final int MULTIPLIER = 2;

    Random random = new Random();

    public MyArrayList() {
        array = (T[]) new Object[10];
    }

    public MyArrayList(int sizeDefault) {
        array = (T[]) new Object[sizeDefault];
    }

    @Override
    public void add(T number) {
        if (cursor == array.length - 1) {
            expandArray();
        }
        array[cursor++] = number;
    }


    @Override
    public void addAll(T... values) {
        /* могу обращаться как с массивом (перебирать по индексам)
        for (int i = 0; i < ints.length; i++) {
            ints[i]
        }
         */
        // ints[0] = 100;

        for (T value : values) {
            add(value);
        }
    }

    /* не используем
    public void addRandomValues(int count, int bound) {
        for (int i = 0; i < count; i++) {
            add(random.nextInt(bound));
        }
    }

    public void addRandomValues(int count) {
        addRandomValues(count, 101);
    }
     */

    private void expandArray() {
//        int[] newArray = Arrays.copyOf(array, array.length * 2);
        T[] newArray = (T[]) new Object[array.length * MULTIPLIER];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    public String toString() {
        if (cursor == 0) return "[]";
        String result = "[";
        for (int i = 0; i < cursor; i++) {
            result += array[i] + ((i < cursor - 1) ? ", " : "]");
        }
        return result;
    }

    //    public int length() {
//        return cursor;
//    }
    @Override
    public int size() {
        return cursor;
    }

    @Override
    public int indexOf(T value) {
        // 1. Перебрать массив, сравнить элементы, вернуть индекс
        // Если совпадений нет - вернуть минус 1.

        for (int i = 0; i < cursor; i++) {
//            if (array[i] == value) return i;
            if (array[i].equals(value)) return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T value) {

        for (int i = cursor - 1; i >= 0; i--) {
            if (array[i].equals(value)) return i;
        }
        return -1;
    }

    @Override
    public boolean contains(T value) {
        return indexOf(value) >= 0; //переиспользуем уже написанный код (метод)
    }

    @Override
    public boolean remove(T value) {
        int index = indexOf(value);
        remove(index);
        return index >= 0;
    }


    @Override
    public T remove(int index) {
        if (index < 0 || index > cursor - 1) return null;

        T value = array[index];

        T[] result = (T[]) new Object[array.length - 1];

        /* // Option1
        System.arraycopy(array, 0, result, 0, index);
        System.arraycopy(array, index + 1, result, index, cursor - 1- index);
        System.out.println(Arrays.toString(result));
         */

        //Option2
//        for (int i = 0; i < cursor - 1; i++) {
        for (int i = 0; i < result.length; i++) {
            if (i < index) {
                result[i] = array[i];
            } else { // i >= index
                result[i] = array[i + 1];
            }
        }

        cursor--;
        array = result;

        return value;
    }

    @Override
    public boolean isEmpty() {
        return cursor == 0;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index > cursor - 1) return null;
        return array[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T> {

        int currentIndex;

        @Override
        public boolean hasNext() {
            return currentIndex < cursor;
        }

        @Override
        public T next() {
//            T value = array[currentIndex];
//            currentIndex++;
//            return value;
            return array[currentIndex++];
        }
    }

    @Override
    public T[] toArray() {
        T[] result = (T[]) new Object[cursor];
        for (int i = 0; i < cursor; i++) {
            result[i] = array[i];
        }
        return result;
    }

    public void trim() {
        array = toArray();
    }
}


/*
-+ 1. Динамическое изменение размера массива
-- 2. Добавление элемента (значения) в массив
-- 9. Добавлять в массив сразу несколько элементов
-- 3. Поиск значений в массиве
-- 3.1. Поиск последнего вхождения элемента
-- 4. Удаление элемента по значению из массива
-- 5. Удаление элемента по индексу из массива
6. Найти мин, макс, среднее, сумму элементов
-- 7. Количество элементов в массиве
-- 8. Строковое представление массива (для вывода в консоль, например)
-- 10. Добавить в массив Х случайных значений
-- 11. Добавить в массив Х случайных значений в диапазоне от 0 до У;
-- 12. Вернуть наш резиновый массив в виде обычного массива


 */


/*
        int[] ints = new int[1000];
        int[] ints1 = {1, 2, 3, 4, 5};
        int number = 100;
        // добавь значение number в конец массива
        // {1, 2, 3, 4, 5, 100};
        // удаление элемента по значение (5) -> {1, 2, 3, 4, 100};

        // добавь в массив 6,7,8,9 -> {1, 2, 3, 4, 100, 6, 7, 8, 9};


        for (int i = 0; i < ints.length; i++) {

*/