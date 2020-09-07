import javax.swing.*;
import java.awt.*;

public class SeaBattleGame {

    Player player;
    Enemy enemy;
    GUI g;
    static final String[] alphabet = {"A","B","C","D","E","F","G"};
    int prevCell = 50;

    public static void main(String[] args) {
        SeaBattleGame game = new SeaBattleGame();
        game.setUpGame(game);
    }

    private void setUpGame(SeaBattleGame game) {
        enemy = new Enemy(3);
        player = new Player();
        g = new GUI(alphabet, player, game);

        g.firstWindow();
    }

    public void startPlaying() {

        int turn = (int) (Math.random() * 2);

        if (turn == 1) {
            enemyTurn();
        } else {
            myTurn();
        }
    }

    public void myTurn() {
        g.enableGrid(g.enemyButtonGrid, true);
        g.mainTextArea.append("\n\nYour turn, choose a cell");
    }

    public void myTurnHandler(JButton but) {
        but.setEnabled(false);
        g.mainTextArea.append("\n Your choise: " + but.getName());
        if (enemy.getShips().contains(new Integer(g.enemyButtonGrid.indexOf(but)))) {
            but.setBackground(Color.RED);
            enemy.getShips().remove(new Integer(g.enemyButtonGrid.indexOf(but)));
            g.mainTextArea.append("\n hit");
            if (enemy.getShips().isEmpty()) {
                finishGame();
            } else {
                myTurn();
            }
        } else {
            but.setBackground(Color.DARK_GRAY);
            g.mainTextArea.append("\n miss");
            enemyTurn();
        }
    }

    public void enemyTurn() {
        g.mainTextArea.append("\n\nEnemy turn, wait...");
        int cell = enemy.enemyChoise(prevCell);
        if (player.getShips().contains(cell)) {
            g.myButtonGrid.get(cell).setBackground(Color.RED);
            g.mainTextArea.append("\nEnemy hit at " + alphabet[cell /alphabet.length] + (cell %alphabet.length));
            player.getShips().remove(new Integer(cell));
            if (player.getShips().isEmpty()) {
                finishGame();
            } else {
                enemyTurn();
            }
        } else {
            //TODO Возможно, лучше поменять на массив использованных, компьютером, ячеек
            if (g.myButtonGrid.get(cell).getBackground() == Color.RED || g.myButtonGrid.get(cell).getBackground() == Color.DARK_GRAY) {
                prevCell = 50;
                enemyTurn();
            } else {
                g.myButtonGrid.get(cell).setBackground(Color.DARK_GRAY);
                g.mainTextArea.append("\nEnemy missed at " + alphabet[cell /alphabet.length] + (cell %alphabet.length));
                prevCell = 50;
                myTurn();
            }
        }
    }

    public void finishGame(){
        if (enemy.getShips().isEmpty()) {
            g.mainTextArea.append("\n\n\n    Game is over.");
            g.mainTextArea.append("\n\n" + g.userName + ", congratulations! You are winner!\n Let's play again!\n   `(^o^)`");
            g.enableGrid(g.enemyButtonGrid, false);
        } else {
            if (player.getShips().isEmpty()) {
                g.mainTextArea.append("\n\n\n    Game is over.");
                g.mainTextArea.append("\n\n" + g.userName + ", you lose... \n (x_x) Try again!");
                g.enableGrid(g.enemyButtonGrid, false);
            }
        }
    }
}

