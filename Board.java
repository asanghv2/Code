import java.util.HashMap;

public class Board {
    private int size = 8;
    private char[][] board;
    private char whiteDot = 'W';
    private char blackDot = 'B';
    private Character[][] othelloBoard;
    private HashMap<Character, Integer> letterMap;

    /**
     * Initializes the board
     */
    public Board() {
        board = new char[size][size];
        initializeBoard();
        initializeLetterMap();
    }

    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = '.';
            }
        }
        // Starting positions
        board[3][3] = whiteDot;
        board[3][4] = blackDot;
        board[4][3] = blackDot;
        board[4][4] = whiteDot;
        convertToOthelloBoard();
    }

    private void initializeLetterMap() {
        letterMap = new HashMap<>();
        letterMap.put('A', 0);
        letterMap.put('B', 1);
        letterMap.put('C', 2);
        letterMap.put('D', 3);
        letterMap.put('E', 4);
        letterMap.put('F', 5);
        letterMap.put('G', 6);
        letterMap.put('H', 7);
    }

    /**
     * Displays the board
     */
    public void displayBoard() {
        System.out.println("  | A | B | C | D | E | F | G | H |");
        System.out.println("  |-------------------------------|");
        for (int i = 0; i < size; i++) {
            System.out.print((i + 1) + " | ");
            for (int j = 0; j < size; j++) {
                System.out.print(othelloBoard[i][j] + " | ");
            }
            System.out.println("\n  |-------------------------------|");
        }
    }

    private void convertToOthelloBoard() {
        othelloBoard = new Character[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                othelloBoard[i][j] = board[i][j];
            }
        }
    }

    
    /**Returns the board for the user
     * @return board
     */
    public char[][] getBoard() {
        return board;
    }
}