package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI {

    private static final int FRAME_WIDTH = 900;
    private static final int FRAME_HEIGHT = 600;
    private int playerOneScore;
    private int playerTwoScore;

    private String currentTurn;

    private JFrame openingFrame;
//    private JFrame singlePlayerFrame;
    private JFrame multiPlayerFrame;

    private JLabel openingLabel;
    private JLabel scoreBoardLabel;
    private JLabel playerOneLabel;
    private JLabel playerTwoLabel;
    private JLabel xLabel;
    private JLabel oLabel;
    private JLabel playerOneScoreLabel;
    private JLabel playerTwoScoreLabel;
    private JLabel currentTurnLabel;
    private JLabel menuLabel;
    private JLabel byLabel;

//    private JButton singlePlayerButton;
    private JButton multiPlayerButton;
    private JButton quitButton;
    private JButton newGameButton;
    private JButton resetScoreButton;
    private JButton exitButton;
    private JButton[][] gameButton;

//    private JPanel singlePlayerPanel;
    private JPanel multiPlayerPanel;
    private JPanel menuPanel;
    private JPanel scoreBoardPanel;

    public GameGUI() {
        openingFrame = new JFrame("Tic-Tac-Toe");
//        singlePlayerFrame = new JFrame("Tic-Tac-Toe");
        multiPlayerFrame = new JFrame("Tic-Tac-Toe");
        gameButton = new JButton[3][3];
        playerOneScore = 0;
        playerTwoScore = 0;
        currentTurn = "X";
        runGame();
    }

    // EFFECTS: runs the Tic-Tac-Toe game
    public void runGame() {
        initializeOpeningLabel();
//        initializeSinglePlayerButton();
        initializeMultiPlayerButton();
        initializeQuitButton();
        initializeOpeningFrame();
    }

    // EFFECTS: initializes the opening frame
    public void initializeOpeningFrame() {
        openingFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        openingFrame.setLayout(null);
        openingFrame.setLocationRelativeTo(null);
        openingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        openingFrame.setVisible(true);
    }

    // EFFECTS: initializes the opening label
    public void initializeOpeningLabel() {
        openingLabel = new JLabel("Tic-Tac-Toe");
        openingLabel.setBounds(240, 80, 500, 200);
        openingLabel.setFont(new Font("Serif", Font.BOLD, 65));
        openingFrame.add(openingLabel);

        byLabel = new JLabel("By: Richard Lee");
        byLabel.setBounds(330, 170, 500, 200);
        byLabel.setFont(new Font("Serif", Font.BOLD, 30));
        openingFrame.add(byLabel);
    }

//    // EFFECTS: initializes the single-player button
//    public void initializeSinglePlayerButton() {
//        singlePlayerButton = new JButton("Player Vs. Bot");
//        singlePlayerButton.setBounds(350, 300, 200, 40);
//        openingFrame.add(singlePlayerButton);
//        singlePlayerButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                openingFrame.dispose();
//                singlePlayerFrame = new JFrame("Tic-Tac-Toe");
//                initializeSinglePlayerFrame();
//            }
//        });
//    }

//    // EFFECTS: initializes the single-player frame
//    public void initializeSinglePlayerFrame() {
//        singlePlayerFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
//        singlePlayerFrame.setLayout(null);
//        singlePlayerFrame.setLocationRelativeTo(null);
//        singlePlayerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        singlePlayerFrame.setVisible(true);
//    }

    // EFFECTS: initializes the multi-player button
    public void initializeMultiPlayerButton() {
        multiPlayerButton = new JButton("Play");
        multiPlayerButton.setBounds(350, 350, 200, 40);
        openingFrame.add(multiPlayerButton);
        multiPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openingFrame.dispose();
                initializeCurrentTurnLabel();
                initializeMultiPlayerPanel();
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        int coord_X = x;
                        int coord_Y = y;
                        gameButton[x][y] = new JButton(" ");
                        multiPlayerPanel.add(gameButton[x][y]);
                        gameButton[x][y].addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                updateBoard(coord_X, coord_Y);
                                checkWinner();
                            }
                        });
                    }
                }

                initializeMenuPanel();
                initializeScoreBoardPanel();
                initializeScoreBoardLabel();
                initializePlayerOneLabel();
                initializePlayerTwoLabel();
                initializeXLabel();
                initializeOLabel();
                initializePlayerOneScoreLabel();
                initializePlayerTwoScoreLabel();
                initializeMenuLabel();
                initializeByLabel();
                initializeNewGameButton();
                initializeResetScoreButton();
                initializeExitButton();
                initializeMultiPlayerFrame();
            }
        });
    }

    // EFFECTS: initializes the current turn label
    public void initializeCurrentTurnLabel() {
        currentTurnLabel = new JLabel("Current Turn: " + currentTurn);
        currentTurnLabel.setBounds(275, 50, 300, 100);
        currentTurnLabel.setFont(new Font("Serif", Font.BOLD, 18));
        multiPlayerFrame.add(currentTurnLabel);
    }

    // EFFECTS: initializes the multi-player panel
    public void initializeMultiPlayerPanel() {
        multiPlayerPanel = new JPanel();
        multiPlayerPanel.setBounds(275, 125, 350, 350);
        multiPlayerPanel.setLayout(new GridLayout(3, 3));
        multiPlayerPanel.setBackground(Color.LIGHT_GRAY);
        multiPlayerFrame.add(multiPlayerPanel);
    }

    // MODIFIES: this
    // EFFECTS: mark the clicked button with X/O, and updates the current turn label
    public void updateBoard(int x, int y) {
        if (gameButton[x][y].getText().equals(" ")) {
            if (currentTurn.equals("X")) {
                gameButton[x][y].setText("X");
                currentTurn = "O";
            } else {
                gameButton[x][y].setText("O");
                currentTurn = "X";
            }
            currentTurnLabel.setText("Current Turn: " + currentTurn);
        }
    }

    // MODIFIES: this
    // EFFECTS: check if there's a winner, or a draw, and updates the game
    public void checkWinner() {
        if (gameButton[0][0].getText().equals("X") &&
                gameButton[0][1].getText().equals("X") &&
                gameButton[0][2].getText().equals("X")) {
            gameButton[0][0].setBackground(Color.RED);
            gameButton[0][1].setBackground(Color.RED);
            gameButton[0][2].setBackground(Color.RED);
            gameButton[0][0].setOpaque(true);
            gameButton[0][1].setOpaque(true);
            gameButton[0][2].setOpaque(true);
            updateGame("X");
        } else if (gameButton[1][0].getText().equals("X") &&
                gameButton[1][1].getText().equals("X") &&
                gameButton[1][2].getText().equals("X")) {
            gameButton[1][0].setBackground(Color.RED);
            gameButton[1][1].setBackground(Color.RED);
            gameButton[1][2].setBackground(Color.RED);
            gameButton[1][0].setOpaque(true);
            gameButton[1][1].setOpaque(true);
            gameButton[1][2].setOpaque(true);
            updateGame("X");
        } else if (gameButton[2][0].getText().equals("X") &&
                gameButton[2][1].getText().equals("X") &&
                gameButton[2][2].getText().equals("X")) {
            gameButton[2][0].setBackground(Color.RED);
            gameButton[2][1].setBackground(Color.RED);
            gameButton[2][2].setBackground(Color.RED);
            gameButton[2][0].setOpaque(true);
            gameButton[2][1].setOpaque(true);
            gameButton[2][2].setOpaque(true);
            updateGame("X");
        } else if (gameButton[0][0].getText().equals("X") &&
                gameButton[1][0].getText().equals("X") &&
                gameButton[2][0].getText().equals("X")) {
            gameButton[0][0].setBackground(Color.RED);
            gameButton[1][0].setBackground(Color.RED);
            gameButton[2][0].setBackground(Color.RED);
            gameButton[0][0].setOpaque(true);
            gameButton[1][0].setOpaque(true);
            gameButton[2][0].setOpaque(true);
            updateGame("X");
        } else if (gameButton[0][1].getText().equals("X") &&
                gameButton[1][1].getText().equals("X") &&
                gameButton[2][1].getText().equals("X")) {
            gameButton[0][1].setBackground(Color.RED);
            gameButton[1][1].setBackground(Color.RED);
            gameButton[2][1].setBackground(Color.RED);
            gameButton[0][1].setOpaque(true);
            gameButton[1][1].setOpaque(true);
            gameButton[2][1].setOpaque(true);
            updateGame("X");
        } else if (gameButton[0][2].getText().equals("X") &&
                gameButton[1][2].getText().equals("X") &&
                gameButton[2][2].getText().equals("X")) {
            gameButton[0][2].setBackground(Color.RED);
            gameButton[1][2].setBackground(Color.RED);
            gameButton[2][2].setBackground(Color.RED);
            gameButton[0][2].setOpaque(true);
            gameButton[1][2].setOpaque(true);
            gameButton[2][2].setOpaque(true);
            updateGame("X");
        } else if (gameButton[0][0].getText().equals("X") &&
                gameButton[1][1].getText().equals("X") &&
                gameButton[2][2].getText().equals("X")) {
            gameButton[0][0].setBackground(Color.RED);
            gameButton[1][1].setBackground(Color.RED);
            gameButton[2][2].setBackground(Color.RED);
            gameButton[0][0].setOpaque(true);
            gameButton[1][1].setOpaque(true);
            gameButton[2][2].setOpaque(true);
            updateGame("X");
        } else if (gameButton[0][2].getText().equals("X") &&
                gameButton[1][1].getText().equals("X") &&
                gameButton[2][0].getText().equals("X")) {
            gameButton[0][2].setBackground(Color.RED);
            gameButton[1][1].setBackground(Color.RED);
            gameButton[2][0].setBackground(Color.RED);
            gameButton[0][2].setOpaque(true);
            gameButton[1][1].setOpaque(true);
            gameButton[2][0].setOpaque(true);
            updateGame("X");
        } else if (gameButton[0][0].getText().equals("O") &&
                gameButton[0][1].getText().equals("O") &&
                gameButton[0][2].getText().equals("O")) {
            gameButton[0][0].setBackground(Color.RED);
            gameButton[0][1].setBackground(Color.RED);
            gameButton[0][2].setBackground(Color.RED);
            gameButton[0][0].setOpaque(true);
            gameButton[0][1].setOpaque(true);
            gameButton[0][2].setOpaque(true);
            updateGame("O");
        } else if (gameButton[1][0].getText().equals("O") &&
                gameButton[1][1].getText().equals("O") &&
                gameButton[1][2].getText().equals("O")) {
            gameButton[1][0].setBackground(Color.RED);
            gameButton[1][1].setBackground(Color.RED);
            gameButton[1][2].setBackground(Color.RED);
            gameButton[1][0].setOpaque(true);
            gameButton[1][1].setOpaque(true);
            gameButton[1][2].setOpaque(true);
            updateGame("O");
        } else if (gameButton[2][0].getText().equals("O") &&
                gameButton[2][1].getText().equals("O") &&
                gameButton[2][2].getText().equals("O")) {
            gameButton[2][0].setBackground(Color.RED);
            gameButton[2][1].setBackground(Color.RED);
            gameButton[2][2].setBackground(Color.RED);
            gameButton[2][0].setOpaque(true);
            gameButton[2][1].setOpaque(true);
            gameButton[2][2].setOpaque(true);
            updateGame("O");
        } else if (gameButton[0][0].getText().equals("O") &&
                gameButton[1][0].getText().equals("O") &&
                gameButton[2][0].getText().equals("O")) {
            gameButton[0][0].setBackground(Color.RED);
            gameButton[1][0].setBackground(Color.RED);
            gameButton[2][0].setBackground(Color.RED);
            gameButton[0][0].setOpaque(true);
            gameButton[1][0].setOpaque(true);
            gameButton[2][0].setOpaque(true);
            updateGame("O");
        } else if (gameButton[0][1].getText().equals("O") &&
                gameButton[1][1].getText().equals("O") &&
                gameButton[2][1].getText().equals("O")) {
            gameButton[0][1].setBackground(Color.RED);
            gameButton[1][1].setBackground(Color.RED);
            gameButton[2][1].setBackground(Color.RED);
            gameButton[0][1].setOpaque(true);
            gameButton[1][1].setOpaque(true);
            gameButton[2][1].setOpaque(true);
            updateGame("O");
        } else if (gameButton[0][2].getText().equals("O") &&
                gameButton[1][2].getText().equals("O") &&
                gameButton[2][2].getText().equals("O")) {
            gameButton[0][2].setBackground(Color.RED);
            gameButton[1][2].setBackground(Color.RED);
            gameButton[2][2].setBackground(Color.RED);
            gameButton[0][2].setOpaque(true);
            gameButton[1][2].setOpaque(true);
            gameButton[2][2].setOpaque(true);
            updateGame("O");
        } else if (gameButton[0][0].getText().equals("O") &&
                gameButton[1][1].getText().equals("O") &&
                gameButton[2][2].getText().equals("O")) {
            gameButton[0][0].setBackground(Color.RED);
            gameButton[1][1].setBackground(Color.RED);
            gameButton[2][2].setBackground(Color.RED);
            gameButton[0][0].setOpaque(true);
            gameButton[1][1].setOpaque(true);
            gameButton[2][2].setOpaque(true);
            updateGame("O");
        } else if (gameButton[0][2].getText().equals("O") &&
                gameButton[1][1].getText().equals("O") &&
                gameButton[2][0].getText().equals("O")) {
            gameButton[0][2].setBackground(Color.RED);
            gameButton[1][1].setBackground(Color.RED);
            gameButton[2][0].setBackground(Color.RED);
            gameButton[0][2].setOpaque(true);
            gameButton[1][1].setOpaque(true);
            gameButton[2][0].setOpaque(true);
            updateGame("O");
        } else if (isGameOver()) {
            currentTurnLabel.setText("Current Turn: A Draw");
        }
    }

    // EFFECTS: initializes the menu panel
    public void initializeMenuPanel() {
        menuPanel = new JPanel();
        menuPanel.setBounds(25, 125, 225, 350);
        menuPanel.setLayout(null);
        menuPanel.setBackground(Color.LIGHT_GRAY);
        multiPlayerFrame.add(menuPanel);
    }

    // EFFECTS: initializes the scoreboard panel
    public void initializeScoreBoardPanel() {
        scoreBoardPanel = new JPanel();
        scoreBoardPanel.setBounds(650, 125, 225, 350);
        scoreBoardPanel.setLayout(null);
        scoreBoardPanel.setBackground(Color.LIGHT_GRAY);
        multiPlayerFrame.add(scoreBoardPanel);
    }

    // EFFECTS: initializes the scoreboard label
    public void initializeScoreBoardLabel() {
        scoreBoardLabel = new JLabel("ScoreBoard");
        scoreBoardLabel.setBounds(42, 25, 150, 50);
        scoreBoardLabel.setFont(new Font("Serif", Font.BOLD, 24));
        scoreBoardPanel.add(scoreBoardLabel);
    }

    // EFFECTS: initializes the player one label
    public void initializePlayerOneLabel() {
        playerOneLabel = new JLabel("Player 1: X");
        playerOneLabel.setBounds(65, 95, 150, 50);
        playerOneLabel.setFont(new Font("Serif", Font.BOLD, 16));
        scoreBoardPanel.add(playerOneLabel);
    }

    // EFFECTS: initializes the player two label
    public void initializePlayerTwoLabel() {
        playerTwoLabel = new JLabel("Player 2: O");
        playerTwoLabel.setBounds(65, 135, 150, 50);
        playerTwoLabel.setFont(new Font("Serif", Font.BOLD, 16));
        scoreBoardPanel.add(playerTwoLabel);
    }

    // EFFECTS: initializes the x label
    public void initializeXLabel() {
        xLabel = new JLabel("X: ");
        xLabel.setBounds(42, 210, 150, 50);
        xLabel.setFont(new Font("Serif", Font.BOLD, 16));
        scoreBoardPanel.add(xLabel);
    }

    // EFFECTS: initializes the y label
    public void initializeOLabel() {
        oLabel = new JLabel("O: ");
        oLabel.setBounds(42, 265, 150, 50);
        oLabel.setFont(new Font("Serif", Font.BOLD, 16));
        scoreBoardPanel.add(oLabel);
    }

    // EFFECTS: initializes the player one score label
    public void initializePlayerOneScoreLabel() {
        playerOneScoreLabel = new JLabel(String.valueOf(playerOneScore), SwingConstants.CENTER);
        playerOneScoreLabel.setBounds(72, 220, 105, 30);
        playerOneScoreLabel.setFont(new Font("Serif", Font.BOLD, 16));
        playerOneScoreLabel.setBackground(Color.WHITE);
        playerOneScoreLabel.setOpaque(true);
        scoreBoardPanel.add(playerOneScoreLabel);
    }

    // EFFECTS: initializes the player two score label
    public void initializePlayerTwoScoreLabel() {
        playerTwoScoreLabel = new JLabel(String.valueOf(playerTwoScore), SwingConstants.CENTER);
        playerTwoScoreLabel.setBounds(72, 275, 105, 30);
        playerTwoScoreLabel.setFont(new Font("Serif", Font.BOLD, 16));
        playerTwoScoreLabel.setBackground(Color.WHITE);
        playerTwoScoreLabel.setOpaque(true);
        scoreBoardPanel.add(playerTwoScoreLabel);
    }

    // EFFECTS: initializes the menu label
    public void initializeMenuLabel() {
        menuLabel = new JLabel("Tic-Tac-Toe");
        menuLabel.setBounds(32, 25, 200, 50);
        menuLabel.setFont(new Font("Serif", Font.BOLD, 24));
        menuPanel.add(menuLabel);
    }

    // EFFECTS: initializes the by whom label
    public void initializeByLabel() {
        byLabel = new JLabel("By: Richard Lee");
        byLabel.setBounds(65, 60, 200, 50);
        byLabel.setFont(new Font("Serif", Font.BOLD, 12));
        menuPanel.add(byLabel);
    }

    // MODIFIES: this
    // EFFECTS: given a string, updates counter and labels, and disables game button
    public void updateGame(String str) {
        if (str.equals("X")) {
            currentTurnLabel.setText("Current Turn: X Wins");
            playerOneScore++;
            playerOneScoreLabel.setText(String.valueOf(playerOneScore));
        } else {
            currentTurnLabel.setText("Current Turn: O Wins");
            playerTwoScore++;
            playerTwoScoreLabel.setText(String.valueOf(playerTwoScore));
        }
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                gameButton[x][y].setEnabled(false);
            }
        }
    }

    // EFFECTS: returns true if the board is filled, otherwise false
    public boolean isGameOver() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (gameButton[x][y].getText().equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    // MODIFIES: this
    // EFFECTS: initializes new game button
    public void initializeNewGameButton() {
        newGameButton = new JButton("New Game");
        newGameButton.setBounds(42, 145, 141, 40);
        menuPanel.add(newGameButton);
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        gameButton[x][y].setText(" ");
                        gameButton[x][y].setBackground(Color.LIGHT_GRAY);
                        gameButton[x][y].setEnabled(true);
                    }
                }
                currentTurn = "X";
                currentTurnLabel.setText("Current Turn: " + currentTurn);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: initializes reset score button
    public void initializeResetScoreButton() {
        resetScoreButton = new JButton("Reset Score");
        resetScoreButton.setBounds(42, 205, 141, 40);
        menuPanel.add(resetScoreButton);
        resetScoreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerOneScore = 0;
                playerTwoScore = 0;
                playerOneScoreLabel.setText(String.valueOf(playerOneScore));
                playerTwoScoreLabel.setText(String.valueOf(playerTwoScore));
            }
        });
    }

    // EFFECTS: initializes exit button
    public void initializeExitButton() {
        exitButton = new JButton("Exit");
        exitButton.setBounds(42, 265, 141, 40);
        menuPanel.add(exitButton);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                multiPlayerFrame.dispose();
            }
        });
    }

    // EFFECTS: initializes multi-player frame
    public void initializeMultiPlayerFrame() {
        multiPlayerFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        multiPlayerFrame.setLayout(null);
        multiPlayerFrame.setLocationRelativeTo(null);
        multiPlayerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        multiPlayerFrame.setVisible(true);
    }

    // EFFECTS: initializes quit button
    public void initializeQuitButton() {
        quitButton = new JButton("Quit");
        quitButton.setBounds(350, 400, 200, 40);
        openingFrame.add(quitButton);
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openingFrame.dispose();
            }
        });
    }
}
