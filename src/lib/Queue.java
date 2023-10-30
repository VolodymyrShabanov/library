package lib;
/*
@date 19.10.2023
@author Sergey Bugaienko
*/

public interface Queue <T>{
    void addLast(T value); // добавить в конец
    void addFirst(T value); //добавить в начало
    T getFirst(); // значение "головы" списка
    T getLast(); // значение самого правого элемента списка
    T remove(); // Удаляет первый элемент списка
    T removeFirst(); // Удаляет первый элемент списка
    T removeLast(); //Удаляет последний элемент списка
}