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
        long linkedListRemoveIfTime = measureOperation(linkedList, "removeIf");
        long arrayListRemoveIfTime = measureOperation(arrayList, "removeIf");
        long linkedListContainsBeginTime = measureOperation(linkedList, "containsBegin");
        long arrayListContainsBeginTime = measureOperation(arrayList, "containsBegin");
        long linkedListContainsMidTime = measureOperation(linkedList, "containsMid");
        long arrayListContainsMidTime = measureOperation(arrayList, "containsMid");
        long linkedListContainsEndTime = measureOperation(linkedList, "containsEnd");
        long arrayListContainsEndTime = measureOperation(arrayList, "containsEnd");
        long linkedListSetBeginTime = measureOperation(linkedList, "setBegin");
        long arrayListSetBeginTime = measureOperation(arrayList, "setBegin");
        long linkedListSetMidTime = measureOperation(linkedList, "setMid");
        long arrayListSetMidTime = measureOperation(arrayList, "setMid");
        long linkedListSetEndTime = measureOperation(linkedList, "setEnd");
        long arrayListSetEndTime = measureOperation(arrayList, "setEnd");

        System.out.println("----------------------------------");
        System.out.println("Operation\tLinkedList\tArrayList");
        System.out.println("-     Add    ---------------------");
        System.out.println("   addFirst  \t" + linkedListAddFirstTime + "\t\t\t" + arrayListAddFirstTime);
        System.out.println("    addMid   \t" + linkedListAddMidTime + "\t\t\t" + arrayListAddMidTime);
        System.out.println("   addLast   \t" + linkedListAddLastTime + "\t\t\t" + arrayListAddLastTime);
        System.out.println("-    Get     ---------------------");
        System.out.println("   getBegin  \t" + linkedListGetBeginTime + "\t\t" + arrayListGetBeginTime);
        System.out.println("    getMid   \t" + linkedListGetMidTime + "\t\t" + arrayListGetMidTime);
        System.out.println("    getEnd   \t" + linkedListGetEndTime + "\t\t" + arrayListGetEndTime);
        System.out.println("-   Remove   ---------------------");
        System.out.println(" removeFirst \t" + linkedListRemoveFirstTime + "\t\t\t" + arrayListRemoveFirstTime);
        System.out.println("  removeMid  \t" + linkedListRemoveMidTime + "\t\t" + arrayListRemoveMidTime);
        System.out.println("  removeLast \t" + linkedListRemoveLastTime + "\t\t\t" + arrayListRemoveLastTime);
        System.out.println("   removeIf  \t" + linkedListRemoveIfTime + "\t\t\t" + arrayListRemoveIfTime);
        System.out.println("-  Contains  ---------------------");
        System.out.println("containsBegin\t" + linkedListContainsBeginTime + "\t\t" + arrayListContainsBeginTime);
        System.out.println(" containsMid \t" + linkedListContainsMidTime + "\t\t" + arrayListContainsMidTime);
        System.out.println(" containsEnd \t" + linkedListContainsEndTime + "\t\t" + arrayListContainsEndTime);
        System.out.println("-    Set     ---------------------");
        System.out.println("   setBegin  \t" + linkedListSetBeginTime + "\t\t" + arrayListSetBeginTime);
        System.out.println("    setMid   \t" + linkedListSetMidTime + "\t\t" + arrayListSetMidTime);
        System.out.println("    setEnd   \t" + linkedListSetEndTime + "\t\t" + arrayListSetEndTime);
    }

    /**
     * Метод, где проводятся замеры
     * @param list ссылка на список, происхождение которого будем узнавать
     * @param method название метода
     * @return время в ms
     * @throws RuntimeException при нереализованном методе/неправильном имени
     */
    private long measureOperation(List<Integer> list, final String method) {
        List<Integer> preparedList = prepareList(list, method);

        switch (method) {
            case "addFirst" -> {
                return TimeMesurer.timing(() -> {
                    for (int i = 0; i < OPERATIONS; ++i) {
                        preparedList.addFirst(-1);
                    }
                });
            }
            case "addMid" -> {
                return TimeMesurer.timing(() -> {
                    for (int i = 0; i < OPERATIONS; ++i) {
                        preparedList.add(preparedList.size() / 2, -1);
                    }
                });
            }
            case "addLast" -> {
                return TimeMesurer.timing(() -> {
                    for (int i = 0; i < OPERATIONS; ++i) {
                        preparedList.addLast(-1);
                    }
                });
            }
            case "getBegin" -> {
                return TimeMesurer.timing(() -> {
                    for (int i = 0; i < OPERATIONS; ++i) {
                        preparedList.get(i);
                    }
                });
            }
            case "getMid" -> {
                return TimeMesurer.timing(() -> {
                    for (int i = 0; i < OPERATIONS; ++i) {
                        preparedList.get(preparedList.size() / 2 - i);
                    }
                });
            }
            case "getEnd" -> {
                return TimeMesurer.timing(() -> {
                    for (int i = 0; i < OPERATIONS; ++i) {
                        preparedList.get(preparedList.size() - 1 - i);
                    }
                });
            }
            case "removeFirst" -> {
                return TimeMesurer.timing(() -> {
                    for (int i = 0; i < OPERATIONS; ++i) {
                        preparedList.removeFirst();
                    }
                });
            }
            case "removeMid" -> {
                return TimeMesurer.timing(() -> {
                    for (int i = 0; i < OPERATIONS; ++i) {
                        preparedList.remove(preparedList.size() / 2);
                    }
                });
            }
            case "removeLast" -> {
                return TimeMesurer.timing(() -> {
                    for (int i = 0; i < OPERATIONS; ++i) {
                        preparedList.removeLast();
                    }
                });
            }
            case "removeIf" -> {
                return TimeMesurer.timing(() -> {
                    preparedList.removeIf(n -> (n % 2 == 0));
                });
            }
            case "containsBegin" -> {
                return TimeMesurer.timing(() -> {
                    for (int i = 0; i < OPERATIONS; ++i) {
                        preparedList.contains(i);
                    }
                });
            }
            case "containsMid" -> {
                return TimeMesurer.timing(() -> {
                    for (int i = 0; i < OPERATIONS; ++i) {
                        preparedList.contains(preparedList.size() / 2 - i);
                    }
                });
            }
            case "containsEnd" -> {
                return TimeMesurer.timing(() -> {
                    for (int i = 0; i < OPERATIONS; ++i) {
                        preparedList.contains(preparedList.size() - 1 - i);
                    }
                });
            }
            case "setBegin" -> {
                return TimeMesurer.timing(() -> {
                    for (int i = 0; i < OPERATIONS; ++i) {
                        preparedList.set(i, -1);
                    }
                });
            }
            case "setMid" -> {
                return TimeMesurer.timing(() -> {
                    for (int i = 0; i < OPERATIONS; ++i) {
                        preparedList.set(preparedList.size() / 2 - i, -1);
                    }
                });
            }
            case "setEnd" -> {
                return TimeMesurer.timing(() -> {
                    for (int i = 0; i < OPERATIONS; ++i) {
                        preparedList.set(preparedList.size() - 1 - i, -1);
                    }
                });
            }
            default -> throw new RuntimeException("Unrealized method " + method);
        }
    }

    /**
     * Метод, который занимается подготовкой списка к тесту
     * @param list ссылка на список, происхождение которого будем узнавать
     * @param method название метода
     * @return ссылка на созданный для замеров список
     */
    private List<Integer> prepareList(List<Integer> list, final String method) {
        if (list instanceof LinkedList<Integer>) {
            list = new LinkedList<>();
        } else {
            list = new ArrayList<>();
        }

        if (!method.contains("add")) {
            for (int i = 0; i < MAX_SIZE; ++i) {
                list.add(i);
            }
        }

        return list;
    }
}
