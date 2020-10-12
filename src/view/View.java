package view;

import adapter.Adapter;
import controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import static model.Model.BOARD_SIZE;

public class View {
    private Adapter adapter;
    private JFrame gui;
    private JButton[][] blocks;
    private JButton reset;
    private JTextArea playerTurn;

    // default constructor to initialize the gui as JFrame
    public View() {
        this.gui = new JFrame("Tic Tac Toe");
        this.blocks = new JButton[BOARD_SIZE][BOARD_SIZE];
        this.reset = new JButton("Reset");
        this.playerTurn = new JTextArea();
        // call the initialize method to set up the layout and initialize buttons
        initialize();
    }

    // function to add action listeners to buttons
    public void setActionListener(Controller c) {
        // adapter needs reference of controller and view class
        this.adapter = new Adapter(c,this);
        for(int row = 0; row<BOARD_SIZE ;row++) {
            for(int column = 0; column<BOARD_SIZE ;column++) {
                blocks[row][column].addActionListener(adapter);
            }
        }
        reset.addActionListener(adapter);
    }

    // function to initialize layout and buttons
    public void initialize () {
        int basicBoardDimens = 200;
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gui.setResizable(true);

        JPanel gamePanel = new JPanel(new FlowLayout());
        JPanel game = new JPanel(new GridLayout(BOARD_SIZE,BOARD_SIZE));
        gamePanel.add(game, BorderLayout.CENTER);
        JPanel options = new JPanel(new FlowLayout());
        options.add(reset);
        JPanel messages = new JPanel(new FlowLayout());
        messages.setBackground(Color.white);
        gui.add(gamePanel, BorderLayout.NORTH);
        gui.add(options, BorderLayout.CENTER);
        gui.add(messages, BorderLayout.SOUTH);
        messages.add(playerTurn);
        playerTurn.setText("Player 1 to play 'X'");

        for(int row = 0; row<BOARD_SIZE ;row++) {
            for(int column = 0; column<BOARD_SIZE ;column++) {
                blocks[row][column] = new JButton();
                blocks[row][column].setPreferredSize(new Dimension(75,75));
                blocks[row][column].setText("");
                game.add(blocks[row][column]);

            }
        }

        // make the gui visible as the final step
        gui.pack();
        gui.setVisible(true);

    }

    // function to check if the action event was generated because of reset key
    public boolean isReset(ActionEvent e) {
        if(e.getSource() == reset)
            return true;
        return false;
    }

    // function to find the x,y-coordinates of the pressed button
    public ArrayList<Integer> getPosition(ActionEvent e) {
        ArrayList<Integer> position = new ArrayList<Integer>();
        for(int row = 0; row<BOARD_SIZE ;row++) {
            for(int column = 0; column<BOARD_SIZE ;column++) {
                if(e.getSource() == blocks[row][column]) {
                    position.add(row);
                    position.add(column);
                }
            }
        }
        return position;
    }

    // function to update the view with the correct mark and message
    public void update(int row, int column, char symbol, String message) {
        blocks[row][column].setText(Character.toString(symbol));
        blocks[row][column].setEnabled(false);
        playerTurn.setText(message);

    }

    // function to freeze the view if there is a winner or game is tied
    public void isWinner(int row, int column, char symbol, String message) {
        blocks[row][column].setText(Character.toString(symbol));
        blocks[row][column].setEnabled(false);
        for(int i = 0; i<BOARD_SIZE ;i++) {
            for(int j = 0; j<BOARD_SIZE ;j++) {
                blocks[i][j].setEnabled(false);
            }
        }
        playerTurn.setText(message);

    }

    // function to clear the view and reset it for a new game
    public void resetGame() {
        for(int row = 0;row<BOARD_SIZE;row++) {
            for(int column = 0;column<BOARD_SIZE;column++) {
                blocks[row][column].setText("");
                blocks[row][column].setEnabled(true);
            }
        }
        playerTurn.setText("Player 1 to play 'X'");
    }

    // mock getter function for checking the value of a button on the grid
    public String getButtonText(int i, int j) {
        return blocks[i][j].getText();
    }

}