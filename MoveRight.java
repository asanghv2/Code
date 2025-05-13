public class MoveRight {
    
    // Method to execute a move to the right
    /**Method to execute a move to the right
     * @param board
     * @param rowIndex
     * @param player
     * @param colIndex
     */
    public static void makeRightMove(Character[][] board, int rowIndex, int player, int colIndex) {
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
        
        // Initialize variables for the column index and flag for opponent's disc presence
        int nextColIndex = colIndex + 1; // Next column index one to the right of the current position
        boolean hasOpponentDisc = false; // Flag to track if there is an opponent's disc in the path
        
        // Traverse to the right until an opponent's disc is found or the edge of the board is reached
        while (nextColIndex < 8 && board[rowIndex][nextColIndex] == opponentDisc) {
            hasOpponentDisc = true; // Set flag to true as opponent's disc is found in the path
            nextColIndex++; // Move to the next column to the right
        }
        
        // If there is an opponent's disc followed by the player's disc, flip the discs in between
        if (hasOpponentDisc && nextColIndex < 8 && board[rowIndex][nextColIndex] == playerDisc) {
            // Iterate from the column next to the opponent's disc to the current position
            for (int i = colIndex + 1; i < nextColIndex; i++) {
                // Flip the discs in between to the player's disc
                board[rowIndex][i] = playerDisc;
            }
        }
    }
}
