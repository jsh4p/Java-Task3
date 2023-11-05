package org.jshap;

public class Main {
    public static void main(String[] args) {
        ListComparator comparator = new ListComparator(100000, 50000);
        comparator.compare();
    }
}