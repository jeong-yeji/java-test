package me.yeji.inflearnthejavatest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestInstance(Lifecycle.PER_CLASS)
class StudyTest {

    int value = 1;

    @Test
    @DisplayName("스터디 만들기 fast")
    @FastTest
    void create_new_study() {
        System.out.println(this);
        System.out.println(value++);
        Study study = new Study(1);
        assertNotNull(study);
    }

    @Test
    @DisplayName("스터디 만들기 slow")
    @SlowTest
    void create_new_study_again() {
        System.out.println(this);
        System.out.println("Create Again" + value++);
    }
}
