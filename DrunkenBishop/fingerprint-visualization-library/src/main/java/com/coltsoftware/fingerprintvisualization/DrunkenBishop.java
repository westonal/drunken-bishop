package com.coltsoftware.fingerprintvisualization;

import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public final class DrunkenBishop {

    private enum Move {
        UP_LEFT(-1, -1), UP_RIGHT(-1, +1), DOWN_LEFT(+1, -1), DOWN_RIGHT(+1, +1);

        private final int deltaX;
        private final int deltaY;

        Move(int deltaY, int deltaX) {
            this.deltaX = deltaX;
            this.deltaY = deltaY;
        }

        public int moveX(int x) {
            return x + deltaX;
        }

        public int moveY(int y) {
            return y + deltaY;
        }
    }

    private final String characters = " .o+=*BOX@%&#/^SE";

    private final Queue<Move> moves = new ArrayDeque<Move>();

    private final int[][] result = new int[17][9];

    private int startXPos = 8;
    private int startYPos = 4;

    private int xPos;
    private int yPos;

    public DrunkenBishop(String hexString) {
        hexString = hexString.replaceAll(":", "");
        String allBits = new BigInteger(hexString, 16).toString(2);
        System.out.println(allBits);
        ArrayList<Move> temp = new ArrayList<Move>();
        int offset = 0;
        while (offset < allBits.length()) {
            String moveBits = allBits.substring(offset, offset + 2);
            Move move = moveFromMoveBits(moveBits);
            temp.add(0, move);
            System.out.println(String.format("%s => %s", moveBits, move));
            offset += 2;
            if (temp.size() == 4) {
                moves.addAll(temp);
                temp.clear();
            }
        }

        moves.addAll(temp);
        temp.clear();

        System.out.println(String.format("%d moves", moves.size()));

        xPos = startXPos;
        yPos = startYPos;

        while (!moves.isEmpty()) {
            Move move = moves.poll();

            xPos = move.moveX(xPos);
            yPos = move.moveY(yPos);

            xPos = clamp(xPos, 0, result.length - 1);
            yPos = clamp(yPos, 0, result[0].length - 1);

            result[xPos][yPos]++;
        }

        result[startXPos][startYPos] = characters.length() - 2;
        result[xPos][yPos] = characters.length() - 1;
    }

    private static int clamp(int val, int min, int max) {
        return Math.max(min, Math.min(max, val));
    }

    public String render() {
        StringBuilder sb = new StringBuilder();

        sb.append("+-----------------+");
        sb.append(System.lineSeparator());
        for (int y = 0; y < 9; y++) {
            sb.append('|');
            for (int x = 0; x < 17; x++) {
                sb.append(resultToSymbol(result[x][y]));
            }
            sb.append('|');
            sb.append(System.lineSeparator());
        }
        sb.append("+-----------------+");
        sb.append(System.lineSeparator());

        return sb.toString();
    }

    private char resultToSymbol(int result) {
        return characters.charAt(result);
    }

    private static Move moveFromMoveBits(String moveBits) {
        if (moveBits.equals("00"))
            return Move.UP_LEFT;
        if (moveBits.equals("01"))
            return Move.UP_RIGHT;
        if (moveBits.equals("10"))
            return Move.DOWN_LEFT;
        if (moveBits.equals("11"))
            return Move.DOWN_RIGHT;
        throw new RuntimeException();
    }
}