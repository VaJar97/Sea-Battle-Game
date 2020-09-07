package com.dotcomgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DotComBust {

    private GameHelper helper = new GameHelper();
    private int numOfGuesses = 0;
    private ArrayList<DotCom> dotComList = new ArrayList<>();
    public static String userName;

    private void setUpGame() {

        System.out.println("            " + userName + ", welcome to the game \"Sunk the .com\"" +
                           "\n\n           Rulers are simple : There'are a grid 7x7 and 3 sites. " +
                           "\n\n         You need to enter cell coordinates, A-G + 0-6 (exmpl. A3,E0,B6 etc.)." +
                           "\n\n     But you have only 15 attempts, so good luck." +
                           "\n                              (*^_^*)");

        DotCom dotFirst = new DotCom("Google.com");
        dotComList.add(dotFirst);
        DotCom dotSecond = new DotCom("YouTube.com");
        dotComList.add(dotSecond );
        DotCom dotThird = new DotCom("Amazon.com");
        dotComList.add(dotThird);

        for (DotCom dotToSet : dotComList) {
            dotToSet.setLocationCells(helper.placeDotCom(3));
        }
    }

    private void startPlaying() {

        while (!dotComList.isEmpty() ) {
            String guess = helper.getUserInput("Please, enter a cell(letter + number)...");
            checkUserGuess(guess);
        }
        finishGame();
    }

    public void checkUserGuess(String userGuess) {

        numOfGuesses++;

        String result = "miss";

        for (DotCom dotCheck : dotComList) {
            result = dotCheck.checkYourself(userGuess);

            if (result.equals("hit")) {
                break;
            }
            if (result.equals("kill")) {
                dotComList.remove(dotCheck);
                break;
            }
        }
        System.out.println(result);
    }

    public void finishGame() {
        System.out.println("Game is over.");
        if (numOfGuesses <= 15) {
            System.out.println("Congratulations! Fish are dancing with your options. You took" + numOfGuesses + " guesses.  `(^o^)`");
        } else {
            System.out.println(" You lose... (x_x) You took " + numOfGuesses + " guesses.");
        }
    }

    public static void main(String[] args) {
        System.out.println("Please, enter your name...");
        try {
            BufferedReader inputName = new BufferedReader(new InputStreamReader(System.in));
            if(inputName.equals("")) {
                userName = "Player";
            } else {
                userName = inputName.readLine();
            }
        } catch (IOException e) {
            e.getMessage();
        }

        DotComBust game = new DotComBust();
        game.setUpGame();
        game.startPlaying();
    }
}

