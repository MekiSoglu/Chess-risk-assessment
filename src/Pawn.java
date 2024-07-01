import java.util.ArrayList;
import java.util.List;

public class Pawn implements Piece {
    private int x;
    private int y;
    private char color;

    public Pawn(int x, int y, char color) {
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
        return 'p';
    }

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public List<int[]> getPossibleMoves(ChessBoard board) {
        List<int[]> moves = new ArrayList<>();
        int direction = (color == 'b') ? 1 : -1;

        if (isValidMove(board, x + direction, y)) {
            moves.add(new int[]{x + direction, y});
        }
        if (isValidMove(board, x + direction, y - 1)) {
            moves.add(new int[]{x + direction, y - 1});
        }
        if (isValidMove(board, x + direction, y + 1)) {
            moves.add(new int[]{x + direction, y + 1});
        }

        return moves;
    }

    private boolean isValidMove(ChessBoard board, int newX, int newY) {
        return newX >= 0 && newX < 8 && newY >= 0 && newY < 8;
    }
}
