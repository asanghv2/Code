public class MoveDiagonalDownRight {
    
    // Method to execute a diagonal move towards down-right direction
    /**Method to execute a diagonal move towards down-right direction
     * @param board
     * @param rowIndex
     * @param colIndex
     * @param player
     */
    public static void makeDiagonalDownRightMove(Character[][] board, int rowIndex, int colIndex, int player) {
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
        
        // Initialize variables for next row and column indices
        int nextRowIndex = rowIndex + 1; // Next row index one below the current position
        int nextColumnIndex = colIndex + 1; // Next column index one to the right of the current position
        boolean hasOpponentDisc = false; // Flag to track if there is an opponent's disc in the path
        
        // Traverse diagonally down-right until an opponent's disc is found or the edge of the board is reached
        while (nextRowIndex < 8 && nextColumnIndex < 8 && board[nextRowIndex][nextColumnIndex] == opponentDisc) {
            hasOpponentDisc = true; // Set flag to true as opponent's disc is found in the path
            nextRowIndex++; // Move to the next row towards down direction
            nextColumnIndex++; // Move to the next column towards right direction
        }
        
        // If there is an opponent's disc followed by the player's disc, flip the discs in between
        if (hasOpponentDisc && nextRowIndex < 8 && nextColumnIndex < 8 && board[nextRowIndex][nextColumnIndex] == playerDisc) {
            // Iterate from the current position to the position where the opponent's disc was found
            for (int i = 1; rowIndex + i <= nextRowIndex; i++) {
                // Flip the discs in between to the player's disc
                board[rowIndex + i][colIndex + i] = playerDisc;
            }
        }
    }
}
