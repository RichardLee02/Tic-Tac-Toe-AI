package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConfigurationTest {

    private Configuration config;

    @BeforeEach
    public void setUp() {
        config = new Configuration();
    }

    @Test
    public void testHasXWonHorizontal() {
        assertTrue(config.isCoordinateAvailable(1));
        assertTrue(config.isCoordinateAvailable(2));
        assertTrue(config.isCoordinateAvailable(3));
        assertFalse(config.hasXWon());

        config.updateGridX(1);
        config.updateGridX(2);
        config.updateGridX(3);

        assertFalse(config.isCoordinateAvailable(1));
        assertFalse(config.isCoordinateAvailable(2));
        assertFalse(config.isCoordinateAvailable(3));
        assertTrue(config.hasXWon());
    }

    @Test
    public void testHasXWonVertical() {
        assertTrue(config.isCoordinateAvailable(1));
        assertTrue(config.isCoordinateAvailable(4));
        assertTrue(config.isCoordinateAvailable(7));
        assertFalse(config.hasXWon());

        config.updateGridX(1);
        config.updateGridX(4);
        config.updateGridX(7);

        assertFalse(config.isCoordinateAvailable(1));
        assertFalse(config.isCoordinateAvailable(4));
        assertFalse(config.isCoordinateAvailable(7));
        assertTrue(config.hasXWon());
    }

    @Test
    public void testHasXWonDiagonal() {
        assertTrue(config.isCoordinateAvailable(1));
        assertTrue(config.isCoordinateAvailable(5));
        assertTrue(config.isCoordinateAvailable(9));
        assertFalse(config.hasXWon());

        config.updateGridX(1);
        config.updateGridX(5);
        config.updateGridX(9);

        assertFalse(config.isCoordinateAvailable(1));
        assertFalse(config.isCoordinateAvailable(5));
        assertFalse(config.isCoordinateAvailable(9));
        assertTrue(config.hasXWon());
    }

    @Test
    public void testHasOWonHorizontal() {
        assertTrue(config.isCoordinateAvailable(7));
        assertTrue(config.isCoordinateAvailable(8));
        assertTrue(config.isCoordinateAvailable(9));
        assertFalse(config.hasOWon());

        config.updateGridO(7);
        config.updateGridO(8);
        config.updateGridO(9);

        assertFalse(config.isCoordinateAvailable(7));
        assertFalse(config.isCoordinateAvailable(8));
        assertFalse(config.isCoordinateAvailable(9));
        assertTrue(config.hasOWon());
    }

    @Test
    public void testHasOWonVertical() {
        assertTrue(config.isCoordinateAvailable(3));
        assertTrue(config.isCoordinateAvailable(6));
        assertTrue(config.isCoordinateAvailable(9));
        assertFalse(config.hasOWon());

        config.updateGridO(3);
        config.updateGridO(6);
        config.updateGridO(9);

        assertFalse(config.isCoordinateAvailable(3));
        assertFalse(config.isCoordinateAvailable(6));
        assertFalse(config.isCoordinateAvailable(9));
        assertTrue(config.hasOWon());
    }

    @Test
    public void testHasOWonDiagonal() {
        assertTrue(config.isCoordinateAvailable(3));
        assertTrue(config.isCoordinateAvailable(5));
        assertTrue(config.isCoordinateAvailable(7));
        assertFalse(config.hasOWon());

        config.updateGridO(3);
        config.updateGridO(5);
        config.updateGridO(7);

        assertFalse(config.isCoordinateAvailable(3));
        assertFalse(config.isCoordinateAvailable(5));
        assertFalse(config.isCoordinateAvailable(7));
        assertTrue(config.hasOWon());
    }

    @Test
    public void testIsGridComplete() {
        assertFalse(config.isGridComplete());

        config.updateGridX(1);
        config.updateGridO(2);
        config.updateGridX(3);
        config.updateGridO(4);
        config.updateGridX(5);

        assertFalse(config.isGridComplete());

        config.updateGridO(6);
        config.updateGridX(7);
        config.updateGridO(8);
        config.updateGridX(9);

        assertTrue(config.isGridComplete());
    }
}
