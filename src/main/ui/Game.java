package ui;

import model.Configuration;

import java.util.Scanner;

public class Game {

    private Configuration config;
    private Scanner input;
    private int command;

    public Game() {
        config = new Configuration();
        input = new Scanner(System.in);
        command = 0;
        runGame();
    }

    // EFFECTS: processes user input
    public void runGame() {
        config = new Configuration();
        input = new Scanner(System.in);
        command = 0;

        displayOpening();
        displayMenu();
        command = input.nextInt();
        processCommands(command);
    }

    // EFFECTS: displays introduction screen
    public void displayOpening() {
        System.out.println("\n" + "=================================================");
        System.out.println("=                                               =");
        System.out.println("=                  TIC-TAC-TOE                  =");
        System.out.println("=                BY: RICHARD LEE                =");
        System.out.println("=                                               =");
        System.out.println("=================================================" + "\n");
    }

    // EFFECTS: displays the options
    public void displayMenu() {
        System.out.println("===================== Menu ======================");
        System.out.println("=                                               =");
        System.out.println("=        -------------------------------        =");
        System.out.println("=        | Press 1: Player Vs. Bot     |        =");
        System.out.println("=        -------------------------------        =");
        System.out.println("=        -------------------------------        =");
        System.out.println("=        | Press 2: Player Vs. Player  |        =");
        System.out.println("=        -------------------------------        =");
        System.out.println("=                                               =");
        System.out.println("=================================================");
    }

    // EFFECTS: processes user commands
    public void processCommands(int option) {
        if (option == 1) {
            runSinglePlayer();
        } else if (option == 2) {
            runMultiPlayer();
        }
    }

    // EFFECTS: runs player vs. bot game
    public void runSinglePlayer() {
        System.out.println("Select From:" + "\n");
        System.out.println("\t1 - Go First");
        System.out.println("\t2 - Go Second" + "\n");
        System.out.print("Enter A Number (1 or 2):" + "\n");
        command = input.nextInt();
        if (command == 1) {
            while (true) {
                gameTemplate("SINGLE");
                if (config.hasXWon()) {
                    displayEnding("WIN");
                    break;
                } else if (config.isGridComplete()) {
                    displayEnding("TIE");
                    break;
                }

                config.botMove("SECOND");
                System.out.println("\n" + "+----------------- Tic-Tac-Toe -----------------+");
                System.out.println("\n" + "Bot Move: ");
                config.getGrid();
                if (config.hasOWon()) {
                    displayEnding("BOT");
                    break;
                }
            }
        } else if (command == 2) {
            while (true) {
                System.out.println("\n" + "+----------------- Tic-Tac-Toe -----------------+");
                config.botMove("FIRST");
                System.out.println("\n" + "Bot Move: ");
                config.getGrid();
                if (config.hasXWon()) {
                    displayEnding("BOT");
                    break;
                } else if (config.isGridComplete()) {
                    displayEnding("TIE");
                    break;
                }

                gameTemplate("SINGLE: SECOND");
                if (config.hasOWon()) {
                    displayEnding("WIN");
                    break;
                }
            }
        }
    }

    // EFFECTS: runs player vs. player game
    public void runMultiPlayer() {
        while (true) {
            gameTemplate("X");
            if (config.hasXWon()) {
                displayEnding("X");
                break;
            } else if (config.isGridComplete()) {
                displayEnding("TIE");
                break;
            }

            gameTemplate("O");
            if (config.hasOWon()) {
                displayEnding("O");
                break;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user inputs, and updates the grid
    public void gameTemplate(String str) {
        System.out.println("\n" + "+----------------- Tic-Tac-Toe -----------------+");
        System.out.println("\n" + "Current Board:");
        config.getGrid();

        if (str.equals("SINGLE") || str.equals("SINGLE: SECOND")) {
            System.out.println("\n" + "Enter A Number (1-9):");
        } else {
            System.out.println("\n" + "[PLAYER " + str + "] Enter A Number (1-9):");
        }

        command = input.nextInt();

        while (!(command >= 1 && command <= 9) || !config.isCoordinateAvailable(command)) {
            if (!(command >= 1 && command <= 9)) {
                System.out.println("\n" + "Coordinate [" + command + "] Is Invalid! Try Again!");
            } else {
                System.out.println("\n" + "Coordinate [" + command + "] Has Already Been Marked! Try Again!");
            }
            System.out.println("\n" + "Current Board:");
            config.getGrid();
            if (str.equals("SINGLE") || str.equals("SINGLE: SECOND")) {
                System.out.println("\n" + "Enter A Number (1-9):");
            } else {
                System.out.println("\n" + "[PLAYER " + str + "] Enter A Number (1-9):");
            }
            command = input.nextInt();
        }

        if (str.equals("SINGLE") || str.equals("X")) {
            config.updateGridX(command);
        } else {
            config.updateGridO(command);
        }

        if (str.equals("SINGLE") || str.equals("SINGLE: SECOND")) {
            System.out.println("\n" + "Your Move:");
        } else {
            System.out.println("\n" + "Player [" + str + "] Move:");
        }

        config.getGrid();
    }

    // EFFECTS: displays ending screen
    public void displayEnding(String str) {
        System.out.println("\n" + "=================================================");
        switch (str) {
            case "WIN":
                System.out.println("=                 YOU HAVE WON!                 =");
                break;
            case "TIE":
                System.out.println("|                 IT IS A DRAW!                 |");
                break;
            case "BOT":
                System.out.println("=                 BOT HAS WON!                  =");
                break;
            default:
                System.out.println("|              PLAYER [" + str + "] HAS WON!              |");
                break;
        }
        System.out.println("=================================================");
        System.out.println("\n" + "=================================================");
        System.out.println("=                                               =");
        System.out.println("=                  GAME OVER!                   =");
        System.out.println("=                                               =");
        System.out.println("=================================================");
    }
}
