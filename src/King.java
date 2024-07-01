import java.util.ArrayList;
import java.util.List;

public class King implements Piece {
    private int x;
    private int y;
    private char color;

    public King(int x, int y, char color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public char getColor() {
        return color;
    }

    @Override
    public char getType() {
        return 'k';
    }

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public List<int[]> getPossibleMoves(ChessBoard board) {
        List<int[]> moves = new ArrayList<>();
        int[][] directions = {
                {1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
        };

        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];

            if (isValidMove(board, newX, newY)) {
                moves.add(new int[]{newX, newY});
            }
        }
        return moves;
    }

    private boolean isValidMove(ChessBoard board, int newX, int newY) {
        return newX >= 0 && newX < 8 && newY >= 0 && newY < 8;
    }
}
