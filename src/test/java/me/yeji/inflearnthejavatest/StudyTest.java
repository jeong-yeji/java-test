package me.yeji.inflearnthejavatest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

    @Test
    @DisplayName("스터디 만들기 fast")
    @FastTest
    void create_new_study() {

        Study study = new Study(10);
        assertNotNull(study);
    }

    @Test
    @DisplayName("스터디 만들기 slow")
    @SlowTest
    void create_new_study_again() {
        System.out.println("Create Again");
    }

    @DisplayName("반복 스터디")
    @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
    void repeat_test(RepetitionInfo repetitionInfo) {
        System.out.println(
                "test " + repetitionInfo.getCurrentRepetition() + "/" + repetitionInfo.getTotalRepetitions());
    }

    @DisplayName("다른 매개변수")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(strings = {"날씨가", "많이", "춥다" })
    @NullAndEmptySource
        // @EmptySource + @NullSource
    void parameterizedTest(String message) {
        System.out.println(message);
    }

    @DisplayName("다른 매개변수")
    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @ValueSource(ints = {10, 20, 40})
    void parameterizedTest(@ConvertWith(StudyConverter.class) Study study) {
        System.out.println(study.getLimit());
    }

    // 매개변수가 하나일 때
    static class StudyConverter extends SimpleArgumentConverter {
        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            assertEquals(Study.class, targetType, "Can only convert to Study");
            return new Study(Integer.parseInt(source.toString()));
        }
    }

    @CsvSource({"10, '자바 스터디'", "20, 스프링" })
    void csv_test1(Integer limit, String name) {
        Study study = new Study(limit, name);
        System.out.println(study);
    }

    @CsvSource({"10, '자바 스터디'", "20, 스프링" })
    void csv_test2(ArgumentsAccessor argumentsAccessor) {
        Study study = new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
        System.out.println(study);
    }

    @CsvSource({"10, '자바 스터디'", "20, 스프링" })
    void csv_test3(@AggregateWith(StudyAggregator.class) Study study) {
        System.out.println(study);
    }

    // 매개변수가 여러개일 때
    static class StudyAggregator implements ArgumentsAggregator {
        @Override
        public Object aggregateArguments(ArgumentsAccessor argumentsAccessor, ParameterContext parameterContext)
                throws ArgumentsAggregationException {
            Study study = new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
            return study;
        }
    }
}
