package pl.edu.agh.cs.app.backend.geometry;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {
    @Test
    void testEquals() {
        Vector2d vec1 = new Vector2d(10, -25);
        Vector2d vec2 = new Vector2d(10, -25);
        Vector2d vec3 = new Vector2d(10, 25);

        assertEquals(vec1, vec2);
        assertNotEquals(vec1, vec3);
    }

    @Test
    void precedes() {
        Vector2d vec1 = new Vector2d(10, 25);
        Vector2d vec2 = new Vector2d(50, 100);
        Vector2d vec3 = new Vector2d(10, 45);

        assertTrue(vec1.precedes(vec2));
        assertTrue(vec1.precedes(vec3));
        assertTrue(vec3.precedes(vec2));
        assertTrue(vec3.precedes(vec3));
    }

    @Test
    void follows() {
        Vector2d vec1 = new Vector2d(10, 25);
        Vector2d vec2 = new Vector2d(50, 100);
        Vector2d vec3 = new Vector2d(10, 45);

        assertTrue(vec2.follows(vec1));
        assertTrue(vec3.follows(vec1));
        assertTrue(vec2.follows(vec3));
        assertTrue(vec3.follows(vec3));
    }

    @Test
    void upperRight() {
        Vector2d vec1 = new Vector2d(10, 20);
        Vector2d vec2 = new Vector2d(20, 10);
        Vector2d vec3 = new Vector2d(40, 40);

        assertEquals(vec1.upperRight(vec2), new Vector2d(20, 20));
        assertEquals(vec1.upperRight(vec3), new Vector2d(40, 40));
        assertEquals(vec3.upperRight(vec2), new Vector2d(40, 40));
    }

    @Test
    void lowerLeft() {
        Vector2d vec1 = new Vector2d(10, 20);
        Vector2d vec2 = new Vector2d(20, 10);
        Vector2d vec3 = new Vector2d(-10, -10);

        assertEquals(vec1.lowerLeft(vec2), new Vector2d(10, 10));
        assertEquals(vec1.lowerLeft(vec3), new Vector2d(-10, -10));
        assertEquals(vec3.lowerLeft(vec2), new Vector2d(-10, -10));
    }

    @Test
    void add() {
        Vector2d vec1 = new Vector2d(-16, 34);
        Vector2d vec2 = new Vector2d(2, 10);
        Vector2d vec3 = new Vector2d(-14, 25);

        assertEquals(vec1.add(vec2), new Vector2d(-14, 44));
        assertEquals(vec2.add(vec3), new Vector2d(-12, 35));
        assertEquals(vec3.add(vec1), new Vector2d(-30, 59));
    }

    @Test
    void subtract() {
        Vector2d vec1 = new Vector2d(-16, 34);
        Vector2d vec2 = new Vector2d(2, 10);
        Vector2d vec3 = new Vector2d(-14, 25);

        assertEquals(vec1.subtract(vec2), new Vector2d(-18, 24));
        assertEquals(vec2.subtract(vec3), new Vector2d(16, -15));
        assertEquals(vec3.subtract(vec1), new Vector2d(2, -9));
    }

    @Test
    void opposite() {
        Vector2d vec1 = new Vector2d(-16, 34);
        Vector2d vec2 = new Vector2d(2, 10);
        Vector2d vec3 = new Vector2d(-14, 25);
        Vector2d vec4 = new Vector2d(0, 0);

        assertEquals(vec1.opposite(), new Vector2d(16, -34));
        assertEquals(vec2.opposite(), new Vector2d(-2, -10));
        assertEquals(vec3.opposite(), new Vector2d(14, -25));
        assertEquals(vec4.opposite(), new Vector2d(0, 0));
    }
}