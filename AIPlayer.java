public class AIPlayer {
    /**
     * Randomly generates moves for AI
     * @param game
     * @return Array with moves
     */
    public int[] makeMove(Othello game) {
        while (true) {
            int rowIndex = (int) (Math.random() * 8);
            int columnIndex = (int) (Math.random() * 8);
            if (game.isValidMove(rowIndex, columnIndex)) {
                return new int[]{rowIndex, columnIndex};
            }
        }
    }
}
