package org.jshap;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ListComparatorTest {
    // �������� �� ������� ������ ��� ������������ ���������� ������������
    @Test
    void testListComparatorWithParametersException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new ListComparator(2000, 4000);
        });
    }
}