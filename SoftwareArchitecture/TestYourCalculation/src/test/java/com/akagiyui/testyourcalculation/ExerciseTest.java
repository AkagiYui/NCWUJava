package com.akagiyui.testyourcalculation;

import com.akagiyui.testyourcalculation.equation.Equation;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseTest {

    @Test
    void recreateEquation() {
        Exercise exercise = new Exercise(100);
        List<Equation> equations1 = new ArrayList<>();
        exercise.recreateEquation(10);
        exercise.forEach(equations1::add);
        exercise.recreateEquation(10);
        List<Equation> equations2 = new ArrayList<>();
        exercise.forEach(equations2::add);
        assertNotEquals(equations1, equations2);

        exercise.recreateEquation(123);
        List<Equation> equations3 = new ArrayList<>();
        exercise.forEach(equations3::add);
        assertEquals(123, equations3.size());
    }

    @Test
    void saveAndLoad() {
        File file = new File("exercise.json");
        file.deleteOnExit();

        Exercise exercise = new Exercise(100);
        List<Equation> equations1 = new ArrayList<>();
        exercise.forEach(equations1::add);

        assertTrue(exercise.save(file));
        exercise.recreateEquation();
        assertTrue(exercise.load(file));
        List<Equation> equations2 = new ArrayList<>();
        exercise.forEach(equations2::add);
        assertEquals(equations1, equations2);
    }

    @Test
    void hasNext() {
        Exercise exercise = new Exercise(100);
        exercise.recreateEquation(10);
        assertTrue(exercise.hasNext());
        for (int i = 0; i < 10; i++) {
            exercise.next();
        }
        assertFalse(exercise.hasNext());
    }

    @Test
    void next() {
        Exercise exercise = new Exercise(100);
        exercise.recreateEquation(10);
        for (int i = 0; i < 10; i++) {
            assertNotNull(exercise.next());
        }
    }

    @Test
    void iterator() {
        Exercise exercise = new Exercise(100);
        exercise.recreateEquation(10);
        int count = 0;
        for (Equation ignored : exercise) {
            count++;
        }
        assertEquals(10, count);
    }
}
