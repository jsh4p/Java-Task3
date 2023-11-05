package org.jshap;

/**
 * Класс для замера времени
 * @author jshap
 */
public final class TimeMesurer {
    /**
     * Метод, который возвращает время выполнения операции
     * @param fun лямбда функция
     * @return время в ms
     */
    public static long timing (Runnable fun) {
        long beginTime = System.currentTimeMillis();

        fun.run();

        return System.currentTimeMillis() - beginTime;
    }
}
