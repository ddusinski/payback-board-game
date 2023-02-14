package com.dusinski.model;

public class Coupon {
    private final String name;
    private int sittingPointeesNumber;

    public Coupon(byte row, byte column) {
        name = "R" + row + "_C" + column;
        sittingPointeesNumber = 1;
    }

    public void addPointee() {
        sittingPointeesNumber++;
    }

    public void removePointee() {
        sittingPointeesNumber--;
    }

    public int getSittingPointeesNumber() {
        return this.sittingPointeesNumber;
    }

    @Override
    public String toString() {
        return "Coupon{" + "name='" + name + '\'' + ", sittingPointeesNumber=" + sittingPointeesNumber + '}';
    }
}
