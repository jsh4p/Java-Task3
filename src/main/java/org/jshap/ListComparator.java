package org.jshap;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

// Хотелось бы обернуть методы списков в лямбда функцию и засунуть в метод,
// который проводит замеры времени

public class ListComparator {
    private static final int SIZE = 100000;
    private static final int OPERATIONS = 50000; // <= SIZE / 2

    public static void compare() {
        List<Integer> linkedList = prepareList(new LinkedList<>());
        List<Integer> arrayList = prepareList(new ArrayList<>());

        long linkedListAddFirstTime = measureOperation(linkedList, "addFirst");
        long arrayListAddFirstTime = measureOperation(arrayList, "addFirst");
        long linkedListAddMidTime = measureOperation(linkedList, "addMid");
        long arrayListAddMidTime = measureOperation(arrayList, "addMid");
        long linkedListAddLastTime = measureOperation(linkedList, "addLast");
        long arrayListAddLastTime = measureOperation(arrayList, "addLast");
        long linkedListGetBeginTime = measureOperation(linkedList, "getBegin");
        long arrayListGetBeginTime = measureOperation(arrayList, "getBegin");
        long linkedListGetMidTime = measureOperation(linkedList, "getMid");
        long arrayListGetMidTime = measureOperation(arrayList, "getMid");
        long linkedListGetEndTime = measureOperation(linkedList, "getEnd");
        long arrayListGetEndTime = measureOperation(arrayList, "getEnd");
        long linkedListRemoveFirstTime = measureOperation(linkedList, "removeFirst");
        long arrayListRemoveFirstTime = measureOperation(arrayList, "removeFirst");
        long linkedListRemoveMidTime = measureOperation(linkedList, "removeMid");
        long arrayListRemoveMidTime = measureOperation(arrayList, "removeMid");
        long linkedListRemoveLastTime = measureOperation(linkedList, "removeLast");
        long arrayListRemoveLastTime = measureOperation(arrayList, "removeLast");

        System.out.println("----------------------------------");
        System.out.println("Operation\tLinkedList\tArrayList");
        System.out.println("-   Add    -----------------------");
        System.out.println("  addFirst \t\t" + linkedListAddFirstTime + "\t\t\t" + arrayListAddFirstTime);
        System.out.println("   addMid  \t\t" + linkedListAddMidTime + "\t\t" + arrayListAddMidTime);
        System.out.println("   addLast \t\t" + linkedListAddLastTime + "\t\t\t" + arrayListAddLastTime);
        System.out.println("-   Get    -----------------------");
        System.out.println("  getBegin \t\t" + linkedListGetBeginTime + "\t\t" + arrayListGetBeginTime);
        System.out.println("   getMid  \t\t" + linkedListGetMidTime + "\t\t" + arrayListGetMidTime);
        System.out.println("   getEnd  \t\t" + linkedListGetEndTime + "\t\t" + arrayListGetEndTime);
        System.out.println("-  Remove  -----------------------");
        System.out.println("removeFirst\t\t" + linkedListRemoveFirstTime + "\t\t\t" + arrayListRemoveFirstTime);
        System.out.println(" removeMid \t\t" + linkedListRemoveMidTime + "\t\t" + arrayListRemoveMidTime);
        System.out.println(" removeLast\t\t" + linkedListRemoveLastTime + "\t\t\t" + arrayListRemoveLastTime);
    }

    private static List<Integer> prepareList(List<Integer> list) {
        Random random = new Random();

        while (list.size() > SIZE) {
            list.removeFirst();
        }

        while (list.size() < SIZE) {
            list.addFirst(random.nextInt());
        }

        return list;
    }

    private static long measureOperation(List<Integer> list, final String operation) {
        Random random = new Random();
        prepareList(list);
        long beginTime = System.currentTimeMillis();

        for (int i = 0; i < Math.min(OPERATIONS, SIZE); ++i) {
            switch (operation) {
                case"addFirst":
                    list.addFirst(random.nextInt());
                    break;
                case"addMid":
                    list.add(list.size() / 2, random.nextInt());
                    break;
                case"addLast":
                    list.addLast(random.nextInt());
                    break;
                case"getBegin":
                    list.get(i);
                    break;
                case"getMid":
                    list.get((int) (Math.ceil(list.size() / 2.) - 1 - i));
                    break;
                case"getEnd":
                    list.get(list.size() - 1 - i);
                    break;
                case"removeFirst":
                    list.removeFirst();
                    break;
                case"removeMid":
                    list.remove(list.size() / 2);
                    break;
                case"removeLast":
                    list.removeLast();
                    break;
            }
        }

        return System.currentTimeMillis() - beginTime;
    }
}
