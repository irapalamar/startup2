package com.company;

import java.util.NoSuchElementException;

public class ArrayList implements List {
    private Object[] array;
    private int size;

    public ArrayList() {
        array = new Object[10];
    }

    @Override
    public void add(int index, Object item) {
        extendArrayAsNeeded();
        if (array[index] == null) {
            array[index] = item;
            size++;
        } else {
            for (int i = size; i >= index; i--) {
                array[i] = array[i - 1];
            }
            array[index] = item;
            size++;
        }
    }


    @Override
    public void set(int index, Object item) {
        checkForRange(index);
        array[index] = item;
    }

    private void checkForRange(int index) {
        if ((index < 0) || (index >= size)) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Object get(int index) {
        checkForRange(index);
        if (array[index] != null) {
            return array[index];
        } else
            throw new NoSuchElementException();
    }

    @Override
    public int indexOf(Object item) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(item))
                return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object item) {
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(item))
                return i;
        }
        return -1;
    }

    @Override
    public void remove(int index) {
        if (array[index] != null) {
            for (Object anArray : array) {
                if (indexOf(anArray) == index) {
                    for (int i = index; i < size; i++) {
                        array[i] = array[i + 1];
                    }
                    size--;
                }
            }
        } else throw new NoSuchElementException();
    }

    @Override
    public List subList(int from, int to) {
        if ((array[from] != null) && ((array[to] != null))) {
            List newArray = new ArrayList();
            for (int i = from; i <= to - 1; i++) {
                newArray.add(array[i]);
            }
            return newArray;
        } else throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object item) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean add(Object item) {
        extendArrayAsNeeded();
        array[size++] = item;
        return true;
    }

    private void extendArrayAsNeeded() {
        if (array.length == size) {
            Object[] newArray = new Object[array.length * 3 / 2 + 1];
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
    }

    @Override
    public boolean remove(Object item) {
        boolean isDelete = false;
        for (int i = 0; i < size; i++) {
            if (array[i].equals(item)) {
                for (int j = indexOf(array[i]); j < size; j++) {
                    array[j] = array[j + 1];
                }
                size--;
                isDelete = true;
            }
        }
        return isDelete;
    }

    @Override
    public void clear() {
        array = new Object[10];
        size = 0;
    }
}