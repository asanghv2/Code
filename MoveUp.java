public class MoveUp {
    
    // Method to execute a move upwards
    /** 
     * Executes the move upwards
     * @param board
     * @param rowIndex
     * @param player
     * @param colIndex
     */
    public static void makeUpMove(Character[][] board, int rowIndex, int player, int colIndex) {
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
        
        // Initialize variables for the row index and flag for opponent's disc presence
        int nextRowIndex = rowIndex - 1; // Next row index one above the current position
        boolean hasOpponentDisc = false; // Flag to track if there is an opponent's disc in the path
        
        // Traverse upwards until an opponent's disc is found or the edge of the board is reached
        while (nextRowIndex >= 0 && board[nextRowIndex][colIndex] == opponentDisc) {
            hasOpponentDisc = true; // Set flag to true as opponent's disc is found in the path
            nextRowIndex--; // Move to the next row upwards
        }
        
        // If there is an opponent's disc followed by the player's disc, flip the discs in between
        if (hasOpponentDisc && nextRowIndex >= 0 && board[nextRowIndex][colIndex] == playerDisc) {
            // Iterate from the row below the opponent's disc to the current position
            for (int i = nextRowIndex + 1; i < rowIndex; i++) {
                // Flip the discs in between to the player's disc
                board[i][colIndex] = playerDisc;
            }
        }
    }
}
