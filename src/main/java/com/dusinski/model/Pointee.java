package com.dusinski.model;

public class Pointee {
    private byte row;
    private byte column;

    public Pointee(byte row, byte column) {
        this.row = row;
        this.column = column;
    }

    public byte getRow() {
        return row;
    }

    public byte getColumn() {
        return column;
    }

    public void changePosition(Direction inputDirection) {
        switch (inputDirection) {
            case UP -> row--;
            case DOWN -> row++;
            case LEFT -> column--;
            case RIGHT -> column++;
        }
    }
}
