import java.util.List;

public interface Piece {
    int getX();
    int getY();
    char getColor();
    char getType();
    void setPosition(int x, int y);
    List<int[]> getPossibleMoves(ChessBoard board);
}
