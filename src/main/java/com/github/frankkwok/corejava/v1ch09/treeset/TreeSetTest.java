package com.github.frankkwok.corejava.v1ch09.treeset;

import java.util.Comparator;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author Frank Kwok on 2017/4/5.
 */
public class TreeSetTest {
    public static void main(String[] args) {
        SortedSet<Item> items = new TreeSet<>();
        items.add(new Item("Toaster", 1234));
        items.add(new Item("Widget", 4562));
        items.add(new Item("Modem", 9912));
        System.out.println(items);

        NavigableSet<Item> sortByDescription = new TreeSet<>(Comparator.comparing(Item::getDescription));
        sortByDescription.addAll(items);
        System.out.println(sortByDescription);
    }
}
