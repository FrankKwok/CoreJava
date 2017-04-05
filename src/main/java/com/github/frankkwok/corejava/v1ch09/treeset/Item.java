package com.github.frankkwok.corejava.v1ch09.treeset;

import java.util.Objects;

/**
 * @author Frank Kwok on 2017/4/5.
 */
public class Item implements Comparable<Item> {
    private String description;
    private int partNumber;

    Item(String description, int partNumber) {
        this.description = description;
        this.partNumber = partNumber;
    }

    String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object otherObject) {
        // this == otherObject
        if (super.equals(otherObject)) {
            return true;
        }
        if (otherObject == null || getClass() != otherObject.getClass()) {
            return false;
        }
        Item other = (Item) otherObject;
        return Objects.equals(description, other.description) &&
                partNumber == other.partNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, partNumber);
    }

    @Override
    public String toString() {
        return "{description=" + description + ", partNumber=" + partNumber + "}";
    }

    @Override
    public int compareTo(Item other) {
        int diff = Integer.compare(partNumber, other.partNumber);
        return diff != 0 ? diff : description.compareTo(other.description);
    }
}
