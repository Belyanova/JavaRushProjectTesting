import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HorseTest {
    @Test
    void firstParameterIsNullTest(){
        assertThrows(IllegalArgumentException.class, () ->
                new Horse(null, new Random().nextInt(10), new Random().nextInt(10)));
    }

    @Test
    void firstParameterIsNullMessageTest(){
        assertThrows(IllegalArgumentException.class, () ->
                new Horse(null, new Random().nextInt(10), new Random().nextInt(10)),
                "Name cannot be null.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "    "})
    void firstParameterIsEmptyTest(String name) {
        assertThrows(IllegalArgumentException.class, () ->
                new Horse(name, new Random().nextInt(20), new Random().nextInt(70)));

    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "    "})
    void firstParameterIsEmptyMessageTest(String name) {
        assertThrows(IllegalArgumentException.class, () ->
                new Horse(name, new Random().nextInt(20), new Random().nextInt(70)),
                "Name cannot be blank.");

    }

    @Test
    void secondParameterIsNegativeTest() {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse("Test", -20, new Random().nextInt(70)));
    }

    @Test
    void secondParameterIsNegativeMessageTest() {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse("Test",  -20, new Random().nextInt(70)),
                "Speed cannot be negative.");
    }

    @Test
    void thirdParameterIsNegativeTest() {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse("Test",  new Random().nextInt(20),
                        -20));
    }

    @Test
    void thirdParameterIsNegativeMessageTest() {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse("Test", new Random().nextInt(20),
                        -20), "Distance cannot be negative.");
    }

    @Test
    void getNameReturnedTest() {
        Horse horse = new Horse("Test", new Random().nextInt(20), new Random().nextInt(20));
        String expected = "Test";
        String actual = horse.getName();
        assertEquals(expected, actual);
    }

    @Test
    void getSpeedReturnedTest() {
        Horse horse = new Horse("Test", new Random().nextInt(20), new Random().nextInt(20));
        int expected = new Random().nextInt(20);
        double actual = horse.getSpeed();
        assertEquals(expected, actual);
    }

    @Test
    void getDistanceWithParameterReturnedTest() {
        Horse horse = new Horse("Test", new Random().nextInt(20), new Random().nextInt(20));
        int expected = new Random().nextInt(20);
        double actual = horse.getDistance();
        assertEquals(expected, actual);
    }

    @Test
    void getDistanceWithoutParameterReturnedTest() {
        Horse horse = new Horse("Test", new Random().nextInt(20));
        double expected = new Random().nextInt(20);
        double actual = horse.getDistance();
        assertEquals(expected, actual);
    }

    @Test
    void moveWithoutParameterTest() {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Test", new Random().nextInt(20), new Random().nextInt(20));
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            horse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.6, 0.3, 0, 2, 0.88})
    void moveWithParameterTest(double rand) {

        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Test", new Random().nextInt(20), new Random().nextInt(20));
            mockedStatic.when((() -> Horse.getRandomDouble(0.2, 0.9))).thenReturn(rand);
            horse.move();
            double expected = 11 + 22 * Horse.getRandomDouble(0.2, 0.9);
            double actual = horse.getDistance();
            assertEquals(expected, actual);
        }
    }
}
