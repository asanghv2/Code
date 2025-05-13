import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;


public class Othello {
    private int userTurn = 1; // Indicates the current player's turn (1 for black, 2 for white)
    private Character[][] othelloBoard; // Represents the game board
    private HashMap<Character, Integer> coordinate; // Maps column letters to indices
    private Scanner scanner; // Scanner for user input
    private boolean playerVsAI; // Flag to determine if the game is Player vs AI
    private AIPlayer aiPlayer; // AI player instance

    /**
     * @return
     */
    public Character[][] getOthelloBoard() {
        return othelloBoard;
    }

    /**
     * @param othelloBoard
     */
    public void setOthelloBoard(Character[][] othelloBoard) {
        this.othelloBoard = othelloBoard;
    }

    // Constructor to initialize the game
    public Othello() {
        othelloBoard = new Character[8][8]; // Initialize the game board
        coordinate = new HashMap<>(); // Initialize the coordinate map
        scanner = new Scanner(System.in); // Initialize the scanner for user input
        aiPlayer = new AIPlayer(); // Initialize the AI player

        // Initialize the game board with empty spaces
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                othelloBoard[i][j] = ' ';
            }
        }

        // Set the initial positions of black and white discs
        othelloBoard[3][3] = 'W';
        othelloBoard[3][4] = 'B';
        othelloBoard[4][3] = 'B';
        othelloBoard[4][4] = 'W';
        
        // Initialize the map to map column letters to indices
        initializeLetterMap();
    }

    // Method to initialize the letter-to-index map
    private void initializeLetterMap() {
        coordinate.put('A', 0);
        coordinate.put('B', 1);
        coordinate.put('C', 2);
        coordinate.put('D', 3);
        coordinate.put('E', 4);
        coordinate.put('F', 5);
        coordinate.put('G', 6);
        coordinate.put('H', 7);
    }

    // Method to display the game board
    public void displayBoard() {
        // Print the column headers
        System.out.println("  | A | B | C | D | E | F | G | H |");
        System.out.println("  |-------------------------------|");
        // Iterate over each row and column to print the board
        for (int i = 0; i < 8; i++) {
            System.out.print((i + 1) + " | "); // Print the row number
            for (int j = 0; j < 8; j++) {
                System.out.print(othelloBoard[i][j] + " | "); // Print the content of each cell
            }
            System.out.println("\n  |-------------------------------|"); // Print separator
        }
    }

    /**
     * @param userPlaceTokenIndex
     * @param columnIndex
     */
    public void upMove(int userPlaceTokenIndex, int columnIndex) {
        MoveUp.makeUpMove(this.getOthelloBoard(), userPlaceTokenIndex, this.userTurn, columnIndex);
    }

    /**
     * @param userPlaceTokenIndex
     * @param columnIndex
     */
    public void downMove(int userPlaceTokenIndex, int columnIndex) {
        MoveDown.makeDownMove(this.getOthelloBoard(), userPlaceTokenIndex, this.userTurn, columnIndex);
    }

    /**
     * @param userPlaceTokenIndex
     * @param rowIndex
     */
    public void leftMove(int userPlaceTokenIndex, int rowIndex) {
        MoveLeft.makeLeftMove(this.othelloBoard, userPlaceTokenIndex, this.userTurn, rowIndex);
    }

    /**
     * @param userPlaceTokenIndex
     * @param rowIndex
     */
    public void rightMove(int userPlaceTokenIndex, int rowIndex) {
        MoveRight.makeRightMove(this.othelloBoard, userPlaceTokenIndex, this.userTurn, rowIndex);
    }

    /**
     * @param diagonalNum
     * @param userPlaceTokenIndex
     */
    public void upLeftDiagonalMove(int diagonalNum, int userPlaceTokenIndex) {
        MoveDiagonalUpLeft.makeDiagonalUpLeftMove(this.getOthelloBoard(), diagonalNum, userPlaceTokenIndex, this.userTurn);
    }

    /**
     * @param diagonalNum
     * @param userPlaceTokenIndex
     */
    public void downRightDiagonalMove(int diagonalNum, int userPlaceTokenIndex) {
        MoveDiagonalDownRight.makeDiagonalDownRightMove(this.getOthelloBoard(), diagonalNum, userPlaceTokenIndex, this.userTurn);
    }

    /**
     * @param diagonalNum
     * @param userPlaceTokenIndex
     */
    public void upRightDiagonalMove(int diagonalNum, int userPlaceTokenIndex) {
        MoveDiagonalUpRight.makeDiagonalUpRightMove(this.getOthelloBoard(), diagonalNum, userPlaceTokenIndex, this.userTurn);
    }

    /**
     * @param diagonalNum
     * @param userPlaceTokenIndex
     */
    public void downLeftDiagonalMove(int diagonalNum, int userPlaceTokenIndex) {
        MoveDiagonalDownLeft.makeDiagonalDownLeftMove(this.getOthelloBoard(), diagonalNum, userPlaceTokenIndex, this.userTurn);
    }

    // Method to update the game board with a disc at the specified position
    private void updateBoard(int rowIndex, int columnIndex) {
        char disc;
        if (userTurn == 1) {
            disc = 'B';
        } else {
            disc = 'W';
        }
        othelloBoard[rowIndex][columnIndex] = disc;
        // Apply moves in all directions
        upMove(rowIndex, columnIndex);
        downMove(rowIndex, columnIndex);
        leftMove(rowIndex, columnIndex);
        rightMove(rowIndex, columnIndex);
        upLeftDiagonalMove(rowIndex, columnIndex);
        downRightDiagonalMove(rowIndex, columnIndex);
        upRightDiagonalMove(rowIndex, columnIndex);
        downLeftDiagonalMove(rowIndex, columnIndex);
    }

    
    /**
     * Checks if the moves are valid or not
     * @param rowIndex
     * @param columnIndex
     * @return if direction is valid
     */
    public boolean isValidMove(int rowIndex, int columnIndex) {
        // Check if the move is within the board bounds
        if (rowIndex < 0 || rowIndex > 7 || columnIndex < 0 || columnIndex > 7) {
            return false;
        }

        // Check if the place is not already occupied by another piece
        if (othelloBoard[rowIndex][columnIndex] != ' ') {
            return false;
        }

        // Check all eight possible directions for a valid move
        return isValidInDirection(rowIndex, columnIndex, -1, 0) || // up
               isValidInDirection(rowIndex, columnIndex, 1, 0) ||  // down
               isValidInDirection(rowIndex, columnIndex, 0, -1) || // left
               isValidInDirection(rowIndex, columnIndex, 0, 1) ||  // right
               isValidInDirection(rowIndex, columnIndex, -1, -1) || // up-left diagonal
               isValidInDirection(rowIndex, columnIndex, -1, 1) ||  // up-right diagonal
               isValidInDirection(rowIndex, columnIndex, 1, -1) ||  // down-left diagonal
               isValidInDirection(rowIndex, columnIndex, 1, 1);     // down-right diagonal
    }

    private boolean isValidInDirection(int rowIndex, int columnIndex, int rowDir, int colDir) {
        char opponentDisc;
        char playerDisc;

        if (userTurn == 1) {
            playerDisc = 'B';
            opponentDisc = 'W';
        } else {
            playerDisc = 'W';
            opponentDisc = 'B';
        }

        int r = rowIndex + rowDir;
        int c = columnIndex + colDir;
        boolean hasOpponentDisc = false;

        while (r >= 0 && r < 8 && c >= 0 && c < 8) {
            if (othelloBoard[r][c] == opponentDisc) {
                hasOpponentDisc = true;
            } else if (othelloBoard[r][c] == playerDisc) {
                return hasOpponentDisc;
            } else {
                break;
            }
            r += rowDir;
            c += colDir;
        }

        return false;
    }

    private int[] extractIndexes(String userInput) {
        char columnChar = extractLetter(userInput); // Extract the column letter
        int columnIndex = getNumberFromLetter(columnChar, coordinate); // Get the column
        int rowIndex = extractNumber(userInput); // Get the row number
        return new int[]{rowIndex, columnIndex}; // Return the row and column indices
    }

    private char extractLetter(String userInput) {
        // Extract the column letter from the user's input
        return userInput.toUpperCase().charAt(0);
    }

    private int extractNumber(String userInput) {
        // Extract the row number from the user's input
        return Character.getNumericValue(userInput.charAt(1)) - 1;
    }

    private int getNumberFromLetter(char letter, HashMap<Character, Integer> coordinate) {
        // Convert the column letter to the corresponding index
        return coordinate.get(letter);
    }

    private void switchTurn() {
        // Switch the player's turn
        if (userTurn == 1) {
            userTurn = 2;
        } else {
            userTurn = 1;
        }        
    }

    
    private  boolean isFull(){
        int count = 0;
        for(Character[] list: this.getOthelloBoard()){
            for(Character ch: list){
                if(ch == 'W'||ch == 'B'){
                    count++;
                }
            }
        }
        return count == 64;
    }

    private boolean isGameOver() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (othelloBoard[i][j] == ' ' && isValidMove(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Determining the winner
    private void determineFinalOutcome(){
        int black = 0;
        int white = 0;
        for(Character[] list: this.getOthelloBoard()){
            for(Character ch: list){
                if(ch == 'W'){
                    white++;
                }
                else{
                    if(ch == 'B'){
                        black++;
                    }
                }
            }
        }
        if(black > white){
            System.out.println("Black Wins! Score:" + black);
        }
        else if(white > black){
            System.out.println("White wins! Score:" + white);
        }
        else{
            System.out.println("Tied");
        }
    }

    // Main game loop
    /**
     * Runs the whole game, PvP and P vs AI
     */
    public void play() {
        // Prompt the user to select the game mode
        System.out.println("Welcome to Othello!");
        System.out.println("Select Game Mode:");
        System.out.println("1. Player vs Player");
        System.out.println("2. Player vs AI");
        int choice = scanner.nextInt();
        playerVsAI = (choice == 2);

        // Game loop
        while (true) {
            displayBoard();
            String disc = "";
            if (userTurn == 1) {
                disc = "Black";
            } else {
                disc = "White";
            }

            System.out.println("Player " + disc + "'s turn.");
            int[] move;
            if (playerVsAI && userTurn == 2) {
                // AI makes a move
                move = aiPlayer.makeMove(this);
                System.out.println("AI moves to " + (char) ('A' + move[1]) + (move[0] + 1));
            } else {
                // Human player makes a move
                System.out.print("Enter your move (e.g., A3): ");
                String userInput = scanner.next();
                move = extractIndexes(userInput);
            }
            if (isValidMove(move[0], move[1])) {
                updateBoard(move[0], move[1]);
                switchTurn();
            } else {
                System.out.println("Invalid move. Try again.");
            }
            if(isFull()||isGameOver()){
                break;
            }
        }
        displayBoard();
        determineFinalOutcome();
    }


    /**
     * Reads a file name, makes the moves and shows the game
     * Also mentions who is the winner if a full complete game 
     * is completed. 
     * Also shows message "Invalid Move" if user makes an invalid move.
     * @param fileName
     */
    private void test(String fileName){
        try{
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                int[] move;
                String line = scanner.nextLine().strip();
                move = extractIndexes(line);
        
            if (isValidMove(move[0], move[1])) {
                updateBoard(move[0], move[1]);
                switchTurn();
            } else {
                System.out.println("Invalid move. Try again.");
            }
            if(isFull()||isGameOver()){
                determineFinalOutcome();
                break;
            }
                //System.out.println(line);
            }
            scanner.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File is not found.");
        }
        displayBoard();
    }
    public static void main(String[] args) {
        Othello game = new Othello();
        game.play();
        System.out.println();
        //game.test("testFullGame.txt"); //White will win
       // game.test("test(2).txt"); // Will just show some completed moves
       
    }
}
