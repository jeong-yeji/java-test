package me.yeji.inflearnthejavatest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
class StudyTest {

    int value = 1;

    @Test
    @DisplayName("스터디 만들기 fast")
    @FastTest
    @Order(2)
    void create_new_study() {
        System.out.println(this);
        System.out.println(value++);
        Study study = new Study(1);
        assertNotNull(study);
    }


    @Test
    @SlowTest
    @Order(1)
    @Disabled
    void create_new_study_again() {
        System.out.println(this);
        System.out.println("Create Again" + value++);
    }
}
