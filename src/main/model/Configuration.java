package model;

public class Configuration {

    private char[][] grid;
    private static final char maximizingPlayer = 'X';
    private static final char minimizingPlayer = 'O';

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

    // EFFECTS: updates the grid based on the best move available
    public void botMove(String str) {
        if (str.equals("FIRST")){
            int bestScore = Integer.MIN_VALUE;
            int[] bestMove = new int[2];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (grid[i][j] == ' ') {
                        grid[i][j] = maximizingPlayer;
                        int val = minimax(grid, 0, false);
                        grid[i][j] = ' ';
                        if (val > bestScore) {
                            bestScore = val;
                            bestMove[0] = i;
                            bestMove[1] = j;
                        }
                    }
                }
            }
            grid[bestMove[0]][bestMove[1]] = maximizingPlayer;
        } else if (str.equals("SECOND")) {
            int bestScore = Integer.MAX_VALUE;
            int[] bestMove = new int[2];
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (grid[i][j] == ' ') {
                        grid[i][j] = minimizingPlayer;
                        int val = minimax(grid, 0, true);
                        grid[i][j] = ' ';
                        if (val < bestScore) {
                            bestScore = val;
                            bestMove[0] = i;
                            bestMove[1] = j;
                        }
                    }
                }
            }
            grid[bestMove[0]][bestMove[1]] = minimizingPlayer;
        }
    }

    // EFFECTS: returns the best value for that move
    public int minimax(char[][] position, int depth, boolean isMaximizingPlayer) {
        if (hasXWon()) {
            return 1;
        } else if (hasOWon()) {
            return -1;
        } else if (isGridComplete()) {
            return 0;
        }
        if (isMaximizingPlayer) {
            int maxVal = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (position[i][j] == ' ') {
                        position[i][j] = maximizingPlayer;
                        int val = minimax(position, depth + 1, false);
                        position[i][j] = ' ';
                        maxVal = Math.max(maxVal, val);
                    }
                }
            }
            return maxVal;
        } else {
            int minVal = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (position[i][j] == ' ') {
                        position[i][j] = minimizingPlayer;
                        int val = minimax(position, depth + 1, true);
                        position[i][j] = ' ';
                        minVal = Math.min(minVal, val);
                    }
                }
            }
            return minVal;
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
