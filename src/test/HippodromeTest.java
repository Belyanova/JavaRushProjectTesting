import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HippodromeTest {
    @Test
    void isNullParameterTest() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void isNullParameterMessageTest() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null),
                "Horses cannot be null.");
    }

    @Test
    void isEmptyParameterTest() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    void isEmptyParameterMessageTest() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()),
                "Horses cannot be empty.");
    }

    @Test
    void getHorsesReturnedTest() {
        List<Horse> expected = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            expected.add(new Horse("test + " + i,
                    new Random().nextInt(20), new Random().nextInt(20)));
        }
        Hippodrome hippodrome = new Hippodrome(expected);
        List<Horse> actual = hippodrome.getHorses();
        assertEquals(expected, actual);
    }

    @Test
    void moveReturnedTest() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (Horse horse : horses) {
            Mockito.verify(horse, Mockito.times(1)).move();
        }
    }

    @Test
    void getWinnerReturnedTest() {
        Hippodrome hippodrome = new Hippodrome(List.of(new Horse("Test", 11, 21),
                new Horse("tEst", 22, 32), new Horse("test", 33, 43)));
        String expected="test";
        String actual=hippodrome.getWinner().getName();
        assertEquals(expected, actual);
    }
}
