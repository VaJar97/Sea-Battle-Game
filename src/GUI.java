import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI {

    String userName;
    String[] alphabet;
    Player player;
    SeaBattleGame game;

    ArrayList<JButton> enemyButtonGrid = new ArrayList<>();
    ArrayList<JButton> myButtonGrid = new ArrayList<>();
    ArrayList<Integer> myShips = new ArrayList<>();

    JFrame firstFrame;
    JButton firstButton;
    JTextArea firstArea;

    JFrame mainFrame;
    JButton startButton;
    JButton newGameButton;
    JButton exitButton;
    JTextArea mainTextArea;
    JLabel border;
    Box buttonBox;
    JButton enB;
    JButton myB;

    public GUI(String[] alphabet, Player p, SeaBattleGame g) {

        this.alphabet = alphabet;
        this.player = p;
        this.game = g;
    }

    public void firstWindow() {
        firstFrame = new JFrame("	Sea Battle	");
        firstFrame.setResizable(false);

        firstButton = new JButton("Go!");

        firstArea = new JTextArea("Enter your name here", 1, 25);

        JLabel firstLabel = new JLabel("    Welcome to the game \"SEA BATTLE\"");
        Font bigFont = new Font("serif", Font.BOLD, 20);
        firstLabel.setFont(bigFont);

        JPanel firstPanel = new JPanel();
        firstPanel.setBackground(Color.CYAN);
        firstPanel.add(firstArea);
        firstPanel.add(firstButton);

        firstButton.addActionListener(new myButtonListener());
        firstArea.addMouseListener(new myMouseListener());
        firstArea.addKeyListener(new myKeyListener());

        firstFrame.getContentPane().add(BorderLayout.NORTH, firstLabel);
        firstFrame.getContentPane().add(BorderLayout.CENTER, firstPanel);

        firstFrame.setBounds(600,300,500,200);
        firstFrame.setVisible(true);
        firstFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    class myButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton but = (JButton) e.getSource();
            if (but.getText().equals(firstButton.getText())) {
                getName();
                intro();
            }
            if (but.getText().equals(exitButton.getText())) {
                System.exit(0);
            }
            if (but.getText().equals(startButton.getText())) {
                if (myShips.size() == 9) {
                    mainTextArea.append("\n\n The game started...");
                    mainWindow();
                } else {
                    mainTextArea.append("\n\n   No, no... You need to choose cells" + "\nfor your ships, on the right grid");
                }
            }
            if (but.getText().equals(newGameButton.getText())) {
                mainFrame.dispose();
                SeaBattleGame.main(new String[]{});
            }
        }
    }

    class myMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (firstArea.getText().equals("Enter your name here")) {
                firstArea.setText("");
            }
        }
        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }

    class myKeyListener implements KeyListener {
        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == 10) {
                getName();
                intro();
            } else {
                if(firstArea.getText().equals("Enter your name here")) {
                    firstArea.setText("");
                }
            }

        }
        public void keyReleased(KeyEvent e) {}
        public void keyTyped(KeyEvent e) {}
    }

    public void intro() {

        firstFrame.dispose();
        mainFrame = new JFrame("	Sea Battle	");
        mainFrame.setResizable(false);

        mainTextArea = new JTextArea(500,10);
        mainTextArea.setEditable(false);
        JScrollPane textScroll = new JScrollPane(mainTextArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        mainTextArea.setLineWrap(true);
        mainTextArea.setWrapStyleWord(true);
        Font areaFont = new Font("italic", Font.BOLD, 12);
        mainTextArea.setFont(areaFont);

        mainTextArea.setText("        Hello, " + userName +
                "\n         Rulers are simple" +
                "\n  Here are grids 7x7" +
                "\n  Right half for you and left for enemy" +
                "\n  First you need to choose your ships" +
                "\n  3 ships by 3 cells for each" +
                "\n  And then press \"Start\"" +
                "\n\n                Good luck!" );

        startButton = new JButton("Start");
        startButton.addActionListener(new myButtonListener());

        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(new myButtonListener());

        exitButton = new JButton("Exit");
        exitButton.addActionListener(new myButtonListener());

        BorderLayout layout = new BorderLayout();
        BorderLayout layout2 = new BorderLayout();
        BorderLayout layout3 = new BorderLayout();

        JPanel mainLeftPanel = new JPanel(layout);
        JPanel mainRightPanel = new JPanel(layout2);
        JPanel background = new JPanel(layout3);

        buttonBox = new Box(BoxLayout.X_AXIS);
        border = new JLabel("              ");
        buttonBox.add(startButton);
        buttonBox.add(border);
        buttonBox.add(exitButton);
        buttonBox.setBorder(BorderFactory.createEmptyBorder(20,300,10,300));

        GridLayout grid = new GridLayout(7,7);
        GridLayout grid2 = new GridLayout(7,1);
        GridLayout grid3 = new GridLayout(1,8);

        JPanel gridLeftPanel = new JPanel(grid);
        JPanel gridRightPanel = new JPanel(grid);

        JPanel leftWords = new JPanel(grid2);
        JPanel leftNums = new JPanel(grid3);
        JPanel rightWords = new JPanel(grid2);
        JPanel rightNums = new JPanel(grid3);

        leftWords.setFont(areaFont);
        leftNums.setFont(areaFont);
        rightWords.setFont(areaFont);
        rightNums.setFont(areaFont);

        for (String s : alphabet) {
            JLabel lw = new JLabel(s);
            leftWords.add(lw);
        }

        for (String s : alphabet) {
            JLabel rw = new JLabel(s);
            rightWords.add(rw);
        }

        JLabel ln = new JLabel("  ");
        leftNums.add(ln);
        for (int i = 0; i < alphabet.length; i++) {
            ln = new JLabel(Integer.toString(i));
            leftNums.add(ln);
        }

        JLabel rn = new JLabel("  ");
        rightNums.add(rn);
        for (int i = 0; i < alphabet.length; i++) {
            rn = new JLabel(Integer.toString(i));
            rightNums.add(rn);
        }

        for (int i = 0; i < 49; i++) {

            enB = new JButton();
            gridLeftPanel.add(enB);
            enemyButtonGrid.add(enB);

            enB.addActionListener(new enemyGridListener());
            enB.setName(alphabet[i / alphabet.length] + (i % alphabet.length));

            enB.setBackground(Color.CYAN);
            enB.setContentAreaFilled(false);
            enB.setOpaque(true);
        }

        for (int i = 0; i < 49; i++) {

            myB = new JButton();
            gridRightPanel.add(myB);
            myButtonGrid.add(myB);

            myB.addActionListener(new myGridListener());
            myB.setName(alphabet[i / alphabet.length] + (i % alphabet.length));

            myB.setBackground(Color.CYAN);
            myB.setContentAreaFilled(false);
            myB.setOpaque(true);
        }

        enableGrid(enemyButtonGrid, false);

        mainRightPanel.add(BorderLayout.CENTER, gridRightPanel);
        mainRightPanel.add(BorderLayout.EAST, rightWords);
        mainRightPanel.add(BorderLayout.SOUTH, rightNums);

        mainLeftPanel.add(BorderLayout.CENTER, gridLeftPanel);
        mainLeftPanel.add(BorderLayout.WEST, leftWords);
        mainLeftPanel.add(BorderLayout.SOUTH, leftNums);

        mainFrame.setBounds(300,150,900,400);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainFrame.getContentPane().add(background);

        background.add(BorderLayout.SOUTH, buttonBox);
        background.add(BorderLayout.CENTER, textScroll);
        background.add(BorderLayout.WEST, mainLeftPanel);
        background.add(BorderLayout.EAST, mainRightPanel);

        gridLeftPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        gridRightPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        background.setBorder(BorderFactory.createEmptyBorder(10,10,1,10));
    }

    class myGridListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton but = (JButton) e.getSource();
            but.setEnabled(false);
            but.setBackground(Color.GRAY);

            mainTextArea.append("\n Your choise: " + but.getName());

            myShips.add(myButtonGrid.indexOf(but));

            if (myShips.size() == 9) {
                enableGrid(myButtonGrid, false);
                player.setShips(myShips);
                mainTextArea.append("\n\nAll right, you have 3 ships.\nLet's play, press \"Start\"...");
            }
        }
    }

    class enemyGridListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton but = (JButton) e.getSource();
            game.myTurnHandler(but);
        }
    }

    public void mainWindow() {

        buttonBox.removeAll();
        buttonBox.add(newGameButton);
        buttonBox.add(border);
        buttonBox.add(exitButton);
        mainFrame.validate();
        game.startPlaying();
    }

    public void getName(){
        if (firstArea.getText().equals("Enter your name here")
                || firstArea.getText().equals("")) {
            userName = "Player";
        }  else {
            userName = firstArea.getText();
        }
    }

    public void enableGrid(ArrayList<JButton> grid, boolean enable) {
        for (JButton but : grid) {
            if (but.isEnabled()) {
                but.setEnabled(enable);
            }
            if (!but.isEnabled() && (but.getBackground() == Color.CYAN)){
                but.setEnabled(enable);
            }
        }
    }
}