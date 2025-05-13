public class MoveDown {
    
    // Method to execute a move downwards
    
    /**
     * Method to execute a move downwards
     * @param board
     * @param rowIndex
     * @param player
     * @param colIndex
     */
    public static void makeDownMove(Character[][] board, int rowIndex, int player, int colIndex) {
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
        int nextRowIndex = rowIndex + 1; // Next row index one below the current position
        boolean hasOpponentDisc = false; // Flag to track if there is an opponent's disc in the path
        
        // Traverse downwards until an opponent's disc is found or the edge of the board is reached
        while (nextRowIndex < 8 && board[nextRowIndex][colIndex] == opponentDisc) {
            hasOpponentDisc = true; // Set flag to true as opponent's disc is found in the path
            nextRowIndex++; // Move to the next row below
        }
        
        // If there is an opponent's disc followed by the player's disc, flip the discs in between
        if (hasOpponentDisc && nextRowIndex < 8 && board[nextRowIndex][colIndex] == playerDisc) {
            // Iterate from the position next to the opponent's disc to the current position
            for (int i = rowIndex + 1; i < nextRowIndex; i++) {
                // Flip the discs in between to the player's disc
                board[i][colIndex] = playerDisc;
            }
        }
    }
}
