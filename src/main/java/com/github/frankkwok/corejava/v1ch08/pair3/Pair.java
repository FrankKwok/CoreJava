package com.github.frankkwok.corejava.v1ch08.pair3;

/**
 * @author Frank Kwok on 2017/3/30.
 */
class Pair<T> {
    private T first;
    private T second;

    Pair() {
        first = null;
        second = null;
    }

    Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    T getFirst() {
        return first;
    }

    void setFirst(T first) {
        this.first = first;
    }

    T getSecond() {
        return second;
    }

    void setSecond(T second) {
        this.second = second;
    }
}
