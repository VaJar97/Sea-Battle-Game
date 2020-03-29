import java.io.*;

//********SimpleDotComGame*****************
public class SimpleDotComGame {
	
	public static void main(String[] args) {
	  
	  int numOfGuesses = 0;
	  GameHelper helper = new GameHelper();

	  SimpleDotCom theDotCom = new SimpleDotCom();
	  int randomNum = (int) (Math.random() * 5);

	  int[] locations = {randomNum, randomNum+1, randomNum+2};
	  theDotCom.setLocationCells(locations);
	  boolean isAlive = true;

	  while(isAlive == true) {
		String guess = helper.getUserInput("enter a number");
		String result = theDotCom.checkYourself(guess);
		numOfGuesses++;
		if(result.equals("kill")) {
		  isAlive = false;
		  System.out.println("You took " + numOfGuesses + " guesses");
		}
	  }

	}
}


//**********SimpleDotCom************************************

class SimpleDotCom {
	private int[] locationCells;
	private int numOfHits = 0;
	

	public void setLocationCells(int[] locs){
	  locationCells = locs;
	}
	
	public String checkYourself(String stringGuess) {

		int guess = Integer.parseInt(stringGuess); // CONVERT STRING TO INT
		String result = "miss"; 		 // Присваиваем по умолчанию miss, чтобы работал RETURN
		for(int cell : locationCells) {		 // COMPARING PLAYERS GUESS WITH CELLS
			  if (guess == cell) {
		  		result = "hit";
		  		numOfHits++;
			  }
	  	}
		if (numOfHits == locationCells.length) {
			  result = "kill";
	 	}
		 System.out.println(result);
	  return result;				  // Для тестового класса
	}
}


//***********GameHelperClass******************************


class GameHelper {
	public String getUserInput(String prompt) {
	  String inputLine = null;
	  System.out.print(prompt + " ");
	  try {
		BufferedReader is = new BufferedReader(
					new InputStreamReader(System.in));
		inputLine = is.readLine();
		if (inputLine.length() == 0) return null; 
	  } 	
	  catch (IOException e) {
		System.out.println("IOExeption: " + e);
	  }
	  return inputLine;	
	}
}