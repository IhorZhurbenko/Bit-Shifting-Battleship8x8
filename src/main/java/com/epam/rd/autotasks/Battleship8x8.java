package com.epam.rd.autotasks;

public class Battleship8x8 {
    private final long ships;
    private long shots = 0L;

    public Battleship8x8(final long ships) {
        this.ships = ships;
    }

    public boolean shoot(String shot) {
        int point = point(shot);
        long shoot = 1L << (64 - point);
        boolean hit = Long.bitCount(ships & shoot) > 0;
        shots = shots | shoot;
        return hit;
    }

    private int point(String string) {
        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H"};
        int num = string.charAt(1);
        int place = 0;
        for (int i = 0; i < letters.length; i++) {
            if (string.startsWith(letters[i])) {
                place = i;
            }
        }
        return 8 * (num - 1) + (place + 1);
    }

    public String state() {
        long check = 1L << 63;
        StringBuilder result = new StringBuilder();
        String empty = ".";
        String emptyMiss = "×";
        String ship = "☐";
        String hit = "☒";
        for (int i = 0; i < 64; i++) {
            if (i % 8 == 0) {
                result.append("\n");
            }
            if (Long.bitCount(ships & check) > 0) {
                if (Long.bitCount(shots & check) > 0) {
                    result.append(hit);
                } else {
                    result.append(ship);
                }
            } else {
                if (Long.bitCount(shots & check) > 0) {
                    result.append(emptyMiss);
                } else {
                    result.append(empty);
                }
            }
            check = check >>> 1;
        }
        return result.toString();
    }
}