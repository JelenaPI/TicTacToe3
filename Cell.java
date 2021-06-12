package tictactoe;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Cell extends JButton implements ActionListener {
    Cell(String text){
        super(text);
        this.setText(" ");
        this.setName("Button" + text);
        this.setEnabled(false);
        addActionListener(this);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(TicTacToe.buttonReset.getText().equals("Reset")){
            setFocusPainted(false);
            if(!TicTacToe.endOfGame) {
                if(isCellFree()){
                    setText(TicTacToe.player.getSign());
                    Field.freeButtons.remove(this);
                    TicTacToe.checkForWinnerAndChangePlayer();
                } else {
                    TicTacToe.SetFieldIsOccupied();
                }
            }
        }
    }

    private boolean isCellFree() {
        return this.getText().equals(" ");
    }
}

