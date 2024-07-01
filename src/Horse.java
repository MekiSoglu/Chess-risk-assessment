import java.util.ArrayList;
import java.util.List;

public class Horse implements Piece {
    private int x;
    private int y;
    private char color;

    public Horse(int x, int y, char color) {
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
        return 'n';
    }

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public List<int[]> getPossibleMoves(ChessBoard board) {
        List<int[]> moves = new ArrayList<>();
        int[][] knightMoves = {
                {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
                {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
        };

        for (int[] move : knightMoves) {
            int newX = x + move[0];
            int newY = y + move[1];
            if (isValidMove(newX, newY)) {
                moves.add(new int[]{newX, newY});
            }
        }
        return moves;
    }

    private boolean isValidMove(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }
}
