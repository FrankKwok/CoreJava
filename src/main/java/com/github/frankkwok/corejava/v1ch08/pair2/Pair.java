package com.github.frankkwok.corejava.v1ch08.pair2;

/**
 * @author Frank Kwok on 2017/3/30.
 */
class Pair<T> {
    private T first;
    private T second;

    Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    T getFirst() {
        return first;
    }

    T getSecond() {
        return second;
    }
}
