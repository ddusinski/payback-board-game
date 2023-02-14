package com.dusinski.service;

import com.dusinski.model.Coupon;
import com.dusinski.model.Direction;
import com.dusinski.model.Pointee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

public class Checkerboard {
    public static final byte BOARD_ROWS = 15;
    public static final byte BOARD_COLS = 15;
    private static final Logger logger = LogManager.getLogger(Checkerboard.class);
    private final Coupon[][] couponsArray = new Coupon[BOARD_ROWS][BOARD_COLS];
    private List<Pointee> pointeeList;

    public Checkerboard() {
        initBoard();
    }

    public String printCheckerboard() {
        StringBuilder result = new StringBuilder("Points per Coupon:\n");
        for (byte i = 0; i < BOARD_ROWS; i++) {
            for (byte j = 0; j < BOARD_COLS; j++) {
                result.append(couponsArray[i][j].getSittingPointeesNumber()).append("-");
            }
            result.append("\n");
        }
        result.append("max Coupons: \n" + getMaxCouponsList() + "\n");
        return result.toString();
    }

    public void flyTheBird() {
        for (Pointee p : pointeeList) {
            Coupon currentCoupon = getCurrentCoupon(p.getRow(), p.getColumn());
            currentCoupon.removePointee();
            jump(p);
            Coupon destinationCoupon = getCurrentCoupon(p.getRow(), p.getColumn());
            destinationCoupon.addPointee();
        }
    }

    private List<Coupon> getMaxCouponsList() {
        return Arrays.stream(couponsArray).flatMap(Arrays::stream).collect(groupingBy(Coupon::getSittingPointeesNumber)).entrySet().stream().max(Comparator.comparing(Map.Entry::getKey)).get().getValue();
    }

    public Coupon getCurrentCoupon(byte currentRow, byte currentColumn) {
        try {
            return couponsArray[currentRow][currentColumn];
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.error("Coupon cannot be found for: row - " + currentRow + " column - " + currentColumn);
        }
        return null;
    }

    private void initBoard() {
        pointeeList = new ArrayList<>(BOARD_ROWS * BOARD_COLS);
        for (byte i = 0; i < BOARD_ROWS; i++) {
            for (byte j = 0; j < BOARD_COLS; j++) {
                Pointee newPointee = new Pointee(i, j);
                Coupon newCoupon = new Coupon(i, j);
                couponsArray[i][j] = newCoupon;
                pointeeList.add(newPointee);
            }
        }
    }

    public List<Direction> genPossibleDirectionList(Pointee p) {
        byte row = p.getRow();
        byte column = p.getColumn();
        List<Direction> directionList = new ArrayList<>();
        if (row < Checkerboard.BOARD_ROWS - 1) directionList.add(Direction.DOWN);
        if (row > 0) directionList.add(Direction.UP);
        if (column > 0) directionList.add(Direction.LEFT);
        if (column < Checkerboard.BOARD_COLS - 1) directionList.add(Direction.RIGHT);
        return directionList;
    }

    public void jump(Pointee p) {
        List<Direction> possibleDirections = genPossibleDirectionList(p);
        int random = (int) (Math.random() * possibleDirections.size());
        Direction randomDirection = possibleDirections.get(random);
        p.changePosition(randomDirection);
    }
}
