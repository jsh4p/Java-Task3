package org.jshap;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Класс сравнения производительности LinkedList и ArrayList
 * @author jshap
 */
public final class ListComparator {
    private final int MAX_SIZE;
    private final int OPERATIONS; // <= SIZE / 2

    /**
     * Конструктор по умолчанию
     */
    public ListComparator() {
        MAX_SIZE = 2000;
        OPERATIONS = 1000;
    }

    /**
     * Конструктор с параметрами
     * @param MAX_SIZE максимальный размер списка
     * @param OPERATIONS количество операций
     * @throws IllegalArgumentException при неправильных аргументах
     */
    public ListComparator(final int MAX_SIZE, final int OPERATIONS) {
        if (OPERATIONS > MAX_SIZE / 2) {
            throw new IllegalArgumentException("Invalid argument " + OPERATIONS + " > " + MAX_SIZE);
        }

        this.MAX_SIZE = MAX_SIZE;
        this.OPERATIONS = OPERATIONS;
    }

    /**
     * Метод, который собирает замеры производительности и строит таблицу
     */
    public void compare() {
        List<Integer> linkedList = new LinkedList<>();
        List<Integer> arrayList = new ArrayList<>();

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

    /**
     * Метод, где проводятся замеры
     * @param list ссылка на список, над которым будут производиться манипуляции
     * @param method название метода
     * @return время в ms
     * @throws RuntimeException при нереализованном методе/неправильном имени
     */
    private long measureOperation(List<Integer> list, final String method) {
        prepareList(list, method);

        switch (method) {
            case "addFirst" -> {
                return TimeMesurer.timing(() -> {
                    for (int i = 0; i < OPERATIONS; ++i) {
                        list.addFirst(i);
                    }
                });
            }
            case "addMid" -> {
                return TimeMesurer.timing(() -> {
                    for (int i = 0; i < OPERATIONS; ++i) {
                        list.add(list.size() / 2, i);
                    }
                });
            }
            case "addLast" -> {
                return TimeMesurer.timing(() -> {
                    for (int i = 0; i < OPERATIONS; ++i) {
                        list.addLast(i);
                    }
                });
            }
            case "getBegin" -> {
                return TimeMesurer.timing(() -> {
                    for (int i = 0; i < OPERATIONS; ++i) {
                        list.get(i);
                    }
                });
            }
            case "getMid" -> {
                return TimeMesurer.timing(() -> {
                    for (int i = 0; i < OPERATIONS; ++i) {
                        list.get((int) (Math.ceil(list.size() / 2.) - 1 - i));
                    }
                });
            }
            case "getEnd" -> {
                return TimeMesurer.timing(() -> {
                    for (int i = 0; i < OPERATIONS; ++i) {
                        list.get(list.size() - 1 - i);
                    }
                });
            }
            case "removeFirst" -> {
                return TimeMesurer.timing(() -> {
                    for (int i = 0; i < OPERATIONS; ++i) {
                        list.removeFirst();
                    }
                });
            }
            case "removeMid" -> {
                return TimeMesurer.timing(() -> {
                    for (int i = 0; i < OPERATIONS; ++i) {
                        list.remove(list.size() / 2);
                    }
                });
            }
            case "removeLast" -> {
                return TimeMesurer.timing(() -> {
                    for (int i = 0; i < OPERATIONS; ++i) {
                        list.removeLast();
                    }
                });
            }
            default -> throw new RuntimeException("Unrealized method " + method);
        }
    }

    /**
     * Метод, который занимается подготовкой списка к тесту
     * @param list ссылка на список, над которым будут производиться манипуляции
     * @param method название метода
     */
    private void prepareList(List<Integer> list, final String method) {
        if (list instanceof LinkedList<Integer>) {
            list = new LinkedList<>();
        } else {
            list = new ArrayList<>();
        }

        if (method.contains("add")) {
            return;
        }

        for (int i = 0; i < MAX_SIZE; ++i) {
            list.add(i);
        }
    }
}
