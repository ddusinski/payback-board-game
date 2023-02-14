package com.dusinski;

import com.dusinski.model.Direction;
import com.dusinski.model.Pointee;
import com.dusinski.service.Checkerboard;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CheckerboardTest {

    private static final byte START_ROW = 1;
    private static final byte START_COLUMN = 1;

    private final Checkerboard testCheckerboard = new Checkerboard();

    @Test
    public void shouldChangePositionProperlyWhenDirectionUp() {
        Pointee testPointee = new Pointee(START_ROW, START_COLUMN);
        testPointee.changePosition(Direction.UP);
        assertEquals(START_ROW - 1, testPointee.getRow());
        assertEquals(START_COLUMN, testPointee.getColumn());
    }

    @Test
    public void shouldChangePositionProperlyWhenDirectionDown() {
        Pointee testPointee = new Pointee(START_ROW, START_COLUMN);
        testPointee.changePosition(Direction.DOWN);
        assertEquals(START_ROW + 1, testPointee.getRow());
        assertEquals(START_COLUMN, testPointee.getColumn());
    }

    @Test
    public void shouldChangePositionProperlyWhenDirectionLeft() {
        Pointee testPointee = new Pointee(START_ROW, START_COLUMN);
        testPointee.changePosition(Direction.LEFT);
        assertEquals(START_ROW, testPointee.getRow());
        assertEquals(START_COLUMN - 1, testPointee.getColumn());
    }

    @Test
    public void shouldChangePositionProperlyWhenDirectionRight() {
        Pointee testPointee = new Pointee(START_ROW, START_COLUMN);
        testPointee.changePosition(Direction.RIGHT);
        assertEquals(START_ROW, testPointee.getRow());
        assertEquals(START_COLUMN + 1, testPointee.getColumn());
    }

    @Test
    public void shouldSelectProperDirectionsWhenPositionLeftUpCorner() {
        Pointee testPointee = new Pointee((byte) 0, (byte) 0);
        List<Direction> directionList = testCheckerboard.genPossibleDirectionList(testPointee);
        assertEquals(2, directionList.size());
        assertTrue(directionList.contains(Direction.RIGHT));
        assertTrue(directionList.contains(Direction.DOWN));
    }

    @Test
    public void shouldSelectProperDirectionsWhenPositionRightUpCorner() {
        Pointee testPointee = new Pointee((byte) 0, Checkerboard.BOARD_ROWS);
        List<Direction> directionList = testCheckerboard.genPossibleDirectionList(testPointee);
        assertEquals(2, directionList.size());
        assertTrue(directionList.contains(Direction.LEFT));
        assertTrue(directionList.contains(Direction.DOWN));
    }

    @Test
    public void shouldSelectProperDirectionsWhenPositionLeftDownCorner() {
        Pointee testPointee = new Pointee(Checkerboard.BOARD_ROWS, (byte) 0);
        List<Direction> directionList = testCheckerboard.genPossibleDirectionList(testPointee);
        assertEquals(2, directionList.size());
        assertTrue(directionList.contains(Direction.RIGHT));
        assertTrue(directionList.contains(Direction.UP));
    }

    @Test
    public void shouldSelectProperDirectionsWhenPositionRightDownCorner() {
        Pointee testPointee = new Pointee(Checkerboard.BOARD_ROWS, Checkerboard.BOARD_COLS);
        List<Direction> directionList = testCheckerboard.genPossibleDirectionList(testPointee);
        assertEquals(2, directionList.size());
        assertTrue(directionList.contains(Direction.LEFT));
        assertTrue(directionList.contains(Direction.UP));
    }

    @Test
    public void shouldSelectProperDirectionsWhenPositionMiddle() {
        Pointee testPointee = new Pointee(START_ROW, START_COLUMN);
        List<Direction> directionList = testCheckerboard.genPossibleDirectionList(testPointee);
        assertEquals(4, directionList.size());
        assertTrue(directionList.contains(Direction.LEFT));
        assertTrue(directionList.contains(Direction.UP));
        assertTrue(directionList.contains(Direction.DOWN));
        assertTrue(directionList.contains(Direction.RIGHT));
    }

    @Test
    public void shouldSelectProperDirectionsWhenPositionTopEdge() {
        Pointee testPointee = new Pointee((byte) 0, START_COLUMN);
        List<Direction> directionList = testCheckerboard.genPossibleDirectionList(testPointee);
        assertEquals(3, directionList.size());
        assertTrue(directionList.contains(Direction.LEFT));
        assertTrue(directionList.contains(Direction.DOWN));
        assertTrue(directionList.contains(Direction.RIGHT));
    }

    @Test
    public void shouldSelectProperDirectionsWhenPositionDownEdge() {
        Pointee testPointee = new Pointee(Checkerboard.BOARD_ROWS, START_COLUMN);
        List<Direction> directionList = testCheckerboard.genPossibleDirectionList(testPointee);
        assertEquals(3, directionList.size());
        assertTrue(directionList.contains(Direction.LEFT));
        assertTrue(directionList.contains(Direction.UP));
        assertTrue(directionList.contains(Direction.RIGHT));
    }

    @Test
    public void shouldSelectProperDirectionsWhenPositionLeftEdge() {
        Pointee testPointee = new Pointee(START_ROW, (byte) 0);
        List<Direction> directionList = testCheckerboard.genPossibleDirectionList(testPointee);
        assertEquals(3, directionList.size());
        assertTrue(directionList.contains(Direction.UP));
        assertTrue(directionList.contains(Direction.DOWN));
        assertTrue(directionList.contains(Direction.RIGHT));
    }

    @Test
    public void shouldSelectProperDirectionsWhenPositionRightEdge() {
        Pointee testPointee = new Pointee(START_ROW, Checkerboard.BOARD_COLS);
        List<Direction> directionList = testCheckerboard.genPossibleDirectionList(testPointee);
        assertEquals(3, directionList.size());
        assertTrue(directionList.contains(Direction.LEFT));
        assertTrue(directionList.contains(Direction.DOWN));
        assertTrue(directionList.contains(Direction.UP));
    }

    @Test
    public void shouldJumpPointeeToTheProperPositionWhenLeftUpCorner() {
        Pointee testPointee = new Pointee((byte) 0, (byte) 0);
        testCheckerboard.jump(testPointee);
        if (testPointee.getRow() == 0) {
            assertEquals(1, testPointee.getColumn());
        } else {
            assertEquals(1, testPointee.getRow());
        }
    }

    @Test
    public void shouldJumpPointeeToTheProperPositionWhenRightUpCorner() {
        Pointee testPointee = new Pointee((byte) 0, Checkerboard.BOARD_COLS);
        testCheckerboard.jump(testPointee);
        if (testPointee.getRow() == 0) {
            assertEquals(Checkerboard.BOARD_COLS - 1, testPointee.getColumn());
        } else {
            assertEquals(1, testPointee.getRow());
        }
    }

    @Test
    public void shouldJumpPointeeToTheProperPositionWhenLeftDownCorner() {
        Pointee testPointee = new Pointee(Checkerboard.BOARD_ROWS, (byte) 0);
        testCheckerboard.jump(testPointee);
        if (testPointee.getRow() == Checkerboard.BOARD_ROWS) {
            assertEquals(1, testPointee.getColumn());
        } else {
            assertEquals(Checkerboard.BOARD_ROWS - 1, testPointee.getRow());
        }
    }

    @Test
    public void shouldJumpPointeeToTheProperPositionWhenRightDownCorner() {
        Pointee testPointee = new Pointee(Checkerboard.BOARD_ROWS, Checkerboard.BOARD_COLS);
        testCheckerboard.jump(testPointee);
        if (testPointee.getRow() == Checkerboard.BOARD_ROWS) {
            assertEquals(Checkerboard.BOARD_COLS - 1, testPointee.getColumn());
        } else {
            assertEquals(Checkerboard.BOARD_ROWS - 1, testPointee.getRow());
        }
    }

    @Test
    public void shouldGetCouponWhenProperPosition() {
        assertNotNull(testCheckerboard.getCurrentCoupon(START_ROW, START_COLUMN));
    }

    @Test
    public void shouldGetNullWhenWrongPosition() {
        assertNull(testCheckerboard.getCurrentCoupon((byte) -1, START_COLUMN));
    }
}
