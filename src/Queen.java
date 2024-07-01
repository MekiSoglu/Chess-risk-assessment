import java.util.ArrayList;
import java.util.List;

public class Queen implements Piece {
    private int x;
    private int y;
    private char color;

    public Queen(int x, int y, char color) {
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
        return 'q';
    }

    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public List<int[]> getPossibleMoves(ChessBoard board) {
        List<int[]> allMoves = new ArrayList<>();
        allMoves.addAll(getMovesInDirection(board, 0, 1));  // +x
        allMoves.addAll(getMovesInDirection(board, 0, -1)); // -x
        allMoves.addAll(getMovesInDirection(board, 1, 0));  // +y
        allMoves.addAll(getMovesInDirection(board, -1, 0)); // -y
        allMoves.addAll(getMovesInDirection(board, 1, 1));  // +x, +y
        allMoves.addAll(getMovesInDirection(board, 1, -1)); // +x, -y
        allMoves.addAll(getMovesInDirection(board, -1, 1)); // -x, +y
        allMoves.addAll(getMovesInDirection(board, -1, -1));// -x, -y
        return allMoves;
    }

    private List<int[]> getMovesInDirection(ChessBoard board, int dx, int dy) {
        List<int[]> moves = new ArrayList<>();
        int newX = x + dx;
        int newY = y + dy;

        while (isValidMove(board, newX, newY)) {
            moves.add(new int[]{newX, newY});
            newX += dx;
            newY += dy;
        }
        return moves;
    }

    private boolean isValidMove(ChessBoard board, int newX, int newY) {
        return newX >= 0 && newX < 8 && newY >= 0 && newY < 8;
    }
}
