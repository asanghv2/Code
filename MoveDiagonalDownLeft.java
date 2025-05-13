public class MoveDiagonalDownLeft {
    
    // Method to execute a move diagonally down and to the left
    /**Method to execute a move diagonally down and to the left
     * @param board
     * @param rowIndex
     * @param colIndex
     * @param player
     */
    public static void makeDiagonalDownLeftMove(Character[][] board, int rowIndex, int colIndex, int player) {
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
        
        // Initialize variables for the row and column indices, and flag for opponent's disc presence
        int nextRowIndex = rowIndex + 1; // Next row index one below the current position
        int nextColIndex = colIndex - 1; // Next column index one to the left of the current position
        boolean hasOpponentDisc = false; // Flag to track if there is an opponent's disc in the path
        
        // Traverse diagonally down and to the left until an opponent's disc is found or the edge of the board is reached
        while (nextRowIndex < 8 && nextColIndex >= 0 && board[nextRowIndex][nextColIndex] == opponentDisc) {
            hasOpponentDisc = true; // Set flag to true as opponent's disc is found in the path
            nextRowIndex++; // Move to the next row below
            nextColIndex--; // Move to the next column to the left
        }
        
        // If there is an opponent's disc followed by the player's disc, flip the discs in between
        if (hasOpponentDisc && nextRowIndex < 8 && nextColIndex >= 0 && board[nextRowIndex][nextColIndex] == playerDisc) {
            // Iterate from the position next to the opponent's disc to the current position
            for (int i = 1; rowIndex + i <= nextRowIndex; i++) {
                // Flip the discs in between to the player's disc
                board[rowIndex + i][colIndex - i] = playerDisc;
            }
        }
    }
}
