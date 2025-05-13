public class MoveDiagonalUpRight {
    
    // Method to make a diagonal up-right move in Othello
    /**
     * Method to make a diagonal up-right move in Othello
     * @param board
     * @param rowIndex
     * @param colIndex
     * @param player
     */
    public static void makeDiagonalUpRightMove(Character[][] board, int rowIndex, int colIndex, int player) {
        // Determine the player's disc and the opponent's disc
        char playerDisc;
        if (player == 1) {
            playerDisc = 'B';
        } else {
            playerDisc = 'W';
        }

        char opponentDisc;
        if (player == 1) {
            opponentDisc = 'W';
        } else {
            opponentDisc = 'B';
        }
        
        // Initialize variables for row and column indices
        int nextRowIndex = rowIndex - 1; // Next row index one above the current position
        int nextColumnIndex = colIndex + 1; // Next column index one to the right of the current position

        boolean hasOpponentDisc = false; // Flag to track if there is an opponent's disc in the path
        
        // Check for opponent's discs diagonally up-right until reaching an empty cell or the board boundary
        while (nextRowIndex >= 0 && nextColumnIndex < 8 && board[nextRowIndex][nextColumnIndex] == opponentDisc) {
            hasOpponentDisc = true; // Set the flag to true if an opponent's disc is found
            nextRowIndex--; // Move one row up
            nextColumnIndex++; // Move one column right
        }

        // If there are opponent's discs and the next cell is the player's disc, flip the discs in between
        if (hasOpponentDisc && nextRowIndex >= 0 && nextColumnIndex < 8 && board[nextRowIndex][nextColumnIndex] == playerDisc) {
            for (int i = 1; rowIndex - i >= nextRowIndex; i++) {
                // Flip the discs by updating the cells between the player's disc and the opponent's disc
                board[rowIndex - i][colIndex + i] = playerDisc;
            }
        }
    }
}
