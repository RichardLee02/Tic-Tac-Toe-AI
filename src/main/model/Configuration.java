package model;

import java.util.Random;

public class Configuration {

    private char[][] grid;

    public Configuration() {
        grid = new char[3][3];
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                grid[x][y] = ' ';
            }
        }
    }

    // EFFECTS: updates the current grid using the coordinate system
    public void updateGridX(int num) {
        if (num == 1) {
            grid[0][0] = 'X';
        } else if (num == 2) {
            grid[0][1] = 'X';
        } else if (num == 3) {
            grid[0][2] = 'X';
        } else if (num == 4) {
            grid[1][0] = 'X';
        } else if (num == 5) {
            grid[1][1] = 'X';
        } else if (num == 6) {
            grid[1][2] = 'X';
        } else if (num == 7) {
            grid[2][0] = 'X';
        } else if (num == 8) {
            grid[2][1] = 'X';
        } else if (num == 9) {
            grid[2][2] = 'X';
        }
    }

    // EFFECTS: updates the current grid using the coordinate system
    public void updateGridO(int num) {
        if (num == 1) {
            grid[0][0] = 'O';
        } else if (num == 2) {
            grid[0][1] = 'O';
        } else if (num == 3) {
            grid[0][2] = 'O';
        } else if (num == 4) {
            grid[1][0] = 'O';
        } else if (num == 5) {
            grid[1][1] = 'O';
        } else if (num == 6) {
            grid[1][2] = 'O';
        } else if (num == 7) {
            grid[2][0] = 'O';
        } else if (num == 8) {
            grid[2][1] = 'O';
        } else if (num == 9) {
            grid[2][2] = 'O';
        }
    }

    // EFFECTS: displays the current grid
    public void getGrid() {
        System.out.println(grid[0][0] + "|" + grid[0][1] + "|" + grid[0][2] + "    " + 1 + "|" + 2 + "|" + 3);
        System.out.println("-" + "+" + "-" + "+" + "-" + "    " + "-" + "+" + "-" + "+" + "-");
        System.out.println(grid[1][0] + "|" + grid[1][1] + "|" + grid[1][2] + "    " + 4 + "|" + 5 + "|" + 6);
        System.out.println("-" + "+" + "-" + "+" + "-" + "    " + "-" + "+" + "-" + "+" + "-");
        System.out.println(grid[2][0] + "|" + grid[2][1] + "|" + grid[2][2] + "    " + 7 + "|" + 8 + "|" + 9);
    }

    // EFFECTS: returns true if there is no marking (X/O) at the specified coordinate
    public boolean isCoordinateAvailable(int num) {
        if (num == 1) {
            return grid[0][0] == ' ';
        } else if (num == 2) {
            return grid[0][1] == ' ';
        } else if (num == 3) {
            return grid[0][2] == ' ';
        } else if (num == 4) {
            return grid[1][0] == ' ';
        } else if (num == 5) {
            return grid[1][1] == ' ';
        } else if (num == 6) {
            return grid[1][2] == ' ';
        } else if (num == 7) {
            return grid[2][0] == ' ';
        } else if (num == 8) {
            return grid[2][1] == ' ';
        } else {
            return grid[2][2] == ' ';
        }
    }

    // EFFECTS: generates a random number from 1-9 and updates the grid
    public void botMove(String str) {
        int min = 1;
        int max = 9;
        Random rand = new Random();
        int randomNum = rand.nextInt(max - min + 1) + min;
        while (!isCoordinateAvailable(randomNum)) {
            randomNum = rand.nextInt(max - min + 1) + min;
        }
        if (str.equals("FIRST")) {
            updateGridX(randomNum);
        } else {
            updateGridO(randomNum);
        }
    }

    // EFFECTS: returns true if the X markings form a line, otherwise false
    public boolean hasXWon() {
                // vertical line
         return grid[0][0] == 'X' && grid[0][1] == 'X' && grid[0][2] == 'X' ||
                grid[1][0] == 'X' && grid[1][1] == 'X' && grid[1][2] == 'X' ||
                grid[2][0] == 'X' && grid[2][1] == 'X' && grid[2][2] == 'X' ||
                // horizontal line
                grid[0][0] == 'X' && grid[1][0] == 'X' && grid[2][0] == 'X' ||
                grid[0][1] == 'X' && grid[1][1] == 'X' && grid[2][1] == 'X' ||
                grid[0][2] == 'X' && grid[1][2] == 'X' && grid[2][2] == 'X' ||
                // diagonal line
                grid[0][0] == 'X' && grid[1][1] == 'X' && grid[2][2] == 'X' ||
                grid[0][2] == 'X' && grid[1][1] == 'X' && grid[2][0] == 'X';
    }

    // EFFECTS: returns true if the O markings form a line, otherwise false
    public boolean hasOWon() {
                // vertical line
         return grid[0][0] == 'O' && grid[0][1] == 'O' && grid[0][2] == 'O' ||
                grid[1][0] == 'O' && grid[1][1] == 'O' && grid[1][2] == 'O' ||
                grid[2][0] == 'O' && grid[2][1] == 'O' && grid[2][2] == 'O' ||
                // horizontal line
                grid[0][0] == 'O' && grid[1][0] == 'O' && grid[2][0] == 'O' ||
                grid[0][1] == 'O' && grid[1][1] == 'O' && grid[2][1] == 'O' ||
                grid[0][2] == 'O' && grid[1][2] == 'O' && grid[2][2] == 'O' ||
                // diagonal line
                grid[0][0] == 'O' && grid[1][1] == 'O' && grid[2][2] == 'O' ||
                grid[0][2] == 'O' && grid[1][1] == 'O' && grid[2][0] == 'O';
    }

    // EFFECTS: returns true if the grid is completely filled, otherwise false
    public boolean isGridComplete() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (grid[x][y] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}
