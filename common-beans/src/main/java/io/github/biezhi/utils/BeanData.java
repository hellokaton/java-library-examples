package io.github.biezhi.utils;

import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;
import io.github.biezhi.model.Person;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Bean对象生成器
 *
 * @author biezhi
 * @date 2018/1/15
 */
public final class BeanData {

    private static final EnhancedRandom enhancedRandom = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
            .seed(123L)
            .objectPoolSize(100)
            .randomizationDepth(3)
            .charset(StandardCharsets.UTF_8)
            .dateRange(LocalDate.now().plusMonths(-2), LocalDate.now().plusMonths(3))
            .stringLengthRange(5, 20)
            .collectionSizeRange(1, 10)
            .scanClasspathForConcreteTypes(true)
            .overrideDefaultInitialization(false)
            .build();

    public static Person randPerson() {
        return enhancedRandom.nextObject(Person.class);
    }

    public static List<Person> randPersons(int count) {
        return enhancedRandom.objects(Person.class, count).collect(Collectors.toList());
    }

}
