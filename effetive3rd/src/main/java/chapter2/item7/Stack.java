package chapter2.item7;

import java.util.Arrays;

public class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() throws Exception {
        if (size == 0) {
            throw new IllegalStateException("stack 사이즈가 0일 경우 오류가 발생합니다");
        }
        return elements[--size];
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }

//    public Object pop() {
//        if (size == 0) {
//            throw new IllegalStateException("stack 사이즈가 0일 경우 오류가 발생합니다");
//        }
//        Object result = elements[--size];
//        elements[size] = null; // 다 쓴 참조 해제
//        return result;
//    }
}
