package org.jshap;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ListComparatorTest {
    // Проверка на проброс ошибки при некорректных параметрах конструктора
    @Test
    void testListComparatorWithParametersException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ListComparator(2000, 4000);
        });
    }
}