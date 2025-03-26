package application;

public class UserNameRecognizer {
	/**
	 * <p>
	 * Title: FSM-translated UserNameRecognizer.
	 * </p>
	 * 
	 * <p>
	 * Description: A demonstration of the mechanical translation of Finite State
	 * Machine diagram into an executable Java program using the UserName
	 * Recognizer. The code detailed design is based on a while loop with a select
	 * list
	 * </p>
	 * 
	 * <p>
	 * Copyright: Lynn Robert Carter © 2024
	 * </p>
	 * 
	 * @author Lynn Robert Carter
	 * 
	 * @version 1.00 2024-09-13 Initial baseline derived from the Even Recognizer
	 * @version 1.01 2024-09-17 Correction to address UNChar coding error, improper
	 *          error message, and improve internal documentation
	 * 
	 */

	/**********************************************************************************************
	 * 
	 * Result attributes to be used for GUI applications where a detailed error
	 * message and a pointer to the character of the error will enhance the user
	 * experience.
	 * 
	 */

	public static String userNameRecognizerErrorMessage = ""; // The error message text
	public static String userNameRecognizerInput = ""; // The input being processed
	public static int userNameRecognizerIndexofError = -1; // The index of error location
	private static int state = 0; // The current state value
	private static int nextState = 0; // The next state value
	private static boolean finalState = false; // Is this state a final state?
	private static boolean foundSpace = false;
	private static String inputLine = ""; // The input line
	private static char currentChar; // The current character in the line
	private static int currentCharNdx; // The index of the current character
	private static boolean running; // The flag that specifies if the FSM is
									// running
	private static int userNameSize = 0; // A numeric value may not exceed 16 characters

	// Private method to display debugging data
	private static void displayDebuggingInfo() {
		// Display the current state of the FSM as part of an execution trace
		if (currentCharNdx >= inputLine.length())
			// display the line with the current state numbers aligned
			System.out.println(((state > 99) ? " " : (state > 9) ? "  " : "   ") + state
					+ ((finalState) ? "       F   " : "           ") + "None");
		else
			System.out.println(((state > 99) ? " " : (state > 9) ? "  " : "   ") + state
					+ ((finalState) ? "       F   " : "           ") + "  " + currentChar + " "
					+ ((nextState > 99) ? "" : (nextState > 9) || (nextState == -1) ? "   " : "    ") + nextState
					+ "     " + userNameSize);
	}

	// Private method to move to the next character within the limits of the input
	// line
	private static void moveToNextCharacter() {
		currentCharNdx++;
		if (currentCharNdx < inputLine.length())
			currentChar = inputLine.charAt(currentCharNdx);
		else {
			currentChar = ' ';
			running = false;
		}
	}

	/**********
	 * This method is a mechanical transformation of a Finite State Machine diagram
	 * into a Java method.
	 * 
	 * @param input The input string for the Finite State Machine
	 * @return An output string that is empty if every things is okay or it is a
	 *         String with a helpful description of the error
	 */
	public static String checkForValidUserName(String input) {
		foundSpace = false; // reset found space
		// Check to ensure that there is input to process
		if (input.length() <= 0) {
			userNameRecognizerIndexofError = 0; // Error at first character;
			return "\n*** ERROR *** The input is empty";
		}

		// The local variables used to perform the Finite State Machine simulation
		state = 0; // This is the FSM state number
		inputLine = input; // Save the reference to the input line as a global
		currentCharNdx = 0; // The index of the current character
		currentChar = input.charAt(0); // The current character from above indexed position

		// The Finite State Machines continues until the end of the input is reached or
		// at some
		// state the current character does not match any valid transition to a next
		// state

		userNameRecognizerInput = input; // Save a copy of the input
		running = true; // Start the loop
		
		// This is the place where semantic actions for a transition to the initial
		// state occur

		userNameSize = 0; // Initialize the UserName size

		// The Finite State Machines continues until the end of the input is reached or
		// at some
		// state the current character does not match any valid transition to a next
		// state
		while (running) {
			//Checks if the character is a whitespace
			if (Character.isWhitespace(currentChar)) {
				foundSpace = true;
			}

			// Go to the next character if there is one
			currentCharNdx++;
			if (currentCharNdx >= inputLine.length())
				running = false;
			else
				currentChar = input.charAt(currentCharNdx);

			if (running) {
				displayDebuggingInfo();
				// When the processing of a state has finished, the FSM proceeds to the next
				// character in the input and if there is one, it fetches that character and
				// updates the currentChar. If there is no next character the currentChar is
				// set to a blank.
				moveToNextCharacter();
			}
			// Should the FSM get here, the loop starts again

		}
		displayDebuggingInfo();

		System.out.println("The loop has ended.");
		
		String errMessage = "";
		if (foundSpace)
			errMessage += "No whitespace character allowed; ";
		
		return errMessage;
	}
}
