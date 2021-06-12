package tictactoe;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.*;
import java.awt.Dimension;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static javax.swing.BoxLayout.X_AXIS;

public class TicTacToe extends JFrame implements WindowListener, ActionListener {
    static Field field;
    static JLabel labelStatus;
    static boolean endOfGame;
    static int counter;
    static JToolBar toolBar;
    static JButton buttonReset;
    static Player player1;
    static Player player2;
    public static Player player;
    JButton buttonPlayer1;
    JButton buttonPlayer2;

    public TicTacToe(){
        super();
        buttonPlayer1 = new JButton("Human");
        buttonPlayer1.setName("ButtonPlayer1");
        buttonPlayer1.addActionListener(this);

        buttonPlayer2 = new JButton("Human");
        buttonPlayer2.setName("ButtonPlayer2");
        buttonPlayer2.addActionListener(this);

        buttonReset = new JButton();
        buttonReset.setName("ButtonStartReset");
        buttonReset.setText("Start");
        buttonReset.addActionListener(this);
        buttonReset.enableInputMethods(true);

        toolBar = new JToolBar();
        toolBar.setLayout(new GridLayout(1, 3));
        toolBar.setPreferredSize(new Dimension(450,30));
        toolBar.add(buttonPlayer1);
        toolBar.add(buttonReset);
        toolBar.add(buttonPlayer2);
        toolBar.setFloatable(false);
        toolBar.setVisible(true);
        add(toolBar, X_AXIS);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setSize(450,560);
        this.setTitle("Tic Tac Toe");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);

        addWindowListener(this);
        field = new Field();
        this.add(field);
        labelStatus = new JLabel();
        labelStatus.setBounds(25, 480, 150, 40);
        labelStatus.setText("Game is not started");
        labelStatus.setVisible(true);
        this.add(labelStatus);
        labelStatus.setVisible(true);
        labelStatus.setName("LabelStatus");
        this.setVisible(true);
        counter = 0;
        player1 = new Player(buttonPlayer1.getText(),"X");
        player2 = new Player(buttonPlayer2.getText(),"O");
        player = player1;

    }

    public static void checkForWinnerAndChangePlayer() {
        counter++;
        labelStatus.setText("Game in progress");
        checkGameStatus();
        changePlayer();
        if(player.getName().equals("Robot")){
            takeMove(player);
            checkForWinnerAndChangePlayer();
        }
    }

    private static void changePlayer() {
        if (player == player1) {
            player = player2;
        } else {
            player = player1;
        }
    }

    public static void SetFieldIsOccupied() {
        labelStatus.setText("This field is occupied");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonReset) {
            if(buttonReset.getText().equals("Reset")) {
                labelStatus.setText("Game is not started");
                buttonReset.setText("Start");
                endOfGame = false;
                buttonPlayer1.setEnabled(true);
                buttonPlayer2.setEnabled(true);
                Field.resetButtons();
                player = player1;
                counter = 0;
            } else {
                buttonReset.setText("Reset");
                labelStatus.setText("Game in progress");
                buttonPlayer1.setEnabled(false);
                buttonPlayer2.setEnabled(false);
                for (Cell x:Field.freeButtons) {x.setEnabled(true);}
                if(buttonPlayer1.getText().equals("Robot") && buttonPlayer2.getText().equals("Robot")){

                    //AKO JE POCETAK IGRE I IGRAJU 2 ROBOTA KOMPJUTER
                    while(!endOfGame){
                        takeMove(player);
                        //setVisible(false);
                        setVisible(true);
                        checkGameStatus();
                        changePlayer();
                    }
                }   else if (buttonPlayer1.getText().equals("Robot")){
                    takeMove(player);
                    checkGameStatus();
                    changePlayer();
                }
            }
        }
        else if (e.getSource() == buttonPlayer1) {
            buttonPlayer1.setText(buttonPlayer1.getText().equals("Robot") ? "Human" : "Robot");
            player1.setName(buttonPlayer1.getText());
        } else if (e.getSource() == buttonPlayer2) {
            buttonPlayer2.setText(buttonPlayer2.getText().equals("Robot") ? "Human" : "Robot");
            player2.setName(buttonPlayer2.getText());
        }
    }

    public static void takeMove(Player player) {
        if(!endOfGame) {
            counter++;
            Random random = new Random();
            Cell randomButton = Field.freeButtons.get(random.nextInt(Field.freeButtons.size()));
            randomButton.setText(player.getSign());
            labelStatus.setText("Game in progres");
            Field.freeButtons.remove(randomButton);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void checkGameStatus(){
        String message = "Game in progress";
        if(counter > 4) {
            message = Field.isThereWinner();
            if (message.equals("")) {
                if (Field.isFull()) {
                    message = "Draw";
                    endOfGame = true;
                } else {
                    message = "Game in progress";
                }
            } else {
                endOfGame = true;
            }
        }
        labelStatus.setText(message);
    }

    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }
    public void windowOpened(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}
}