import java.util.List;

public class RiskCalculator {

    public void calculateRisk(ChessBoard board) {
        List<Piece> pieces = board.getPieces();

        for (Piece piece : pieces) {
            if (piece instanceof Pawn) {
                calculateDirectionalRisk(board, piece, new int[][]{{1, 1}, {1, -1}});
            } else if (piece instanceof Rook) {
                calculateDirectionalRisk(board, piece, new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}});
            } else if (piece instanceof Bishop) {
                calculateDirectionalRisk(board, piece, new int[][]{{1, 1}, {1, -1}, {-1, 1}, {-1, -1}});
            } else if (piece instanceof Queen) {
                calculateDirectionalRisk(board, piece, new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}});
            } else if (piece instanceof King) {
                calculateDirectionalRisk(board, piece, new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}});
            } else if (piece instanceof Horse) {
                calculateHorseRisk(board, (Horse) piece);
            }
        }
    }

    private void calculateDirectionalRisk(ChessBoard board, Piece piece, int[][] directions) {
        for (int[] direction : directions) {
            evaluateDirection(board, piece, direction[0], direction[1]);
        }
    }
// at dışındaki bütün taşlar için risk hesabı
    private void evaluateDirection(ChessBoard board, Piece piece, int dx, int dy) {
        int newX = piece.getX() + dx;
        int newY = piece.getY() + dy;
        boolean isPawn = piece instanceof Pawn;
        boolean isKing = piece instanceof King;

        while (isValidMove(newX, newY)) {
            double[] target = board.getPiece(newX, newY);
            if (target != null) {
                if (target[0] != (piece.getColor() == 'b' ? 0 : 1)) {
                    if (!isPawn || (Math.abs(piece.getX() - newX) == 1 && Math.abs(piece.getY() - newY) == 1)) {
                        reduceRisk(target, board, newX, newY);
                    }
                    break;
                } else {
                    break;
                }
            }
            if (isPawn || isKing) {
                break;
            }
            newX += dx;
            newY += dy;
        }
    }

    private boolean isValidMove(int x, int y) {
        return x >= 0 && x < 8 && y >= 0 && y < 8;
    }


    private void reduceRisk(double[] target, ChessBoard board, int x, int y) {
        char type = (char) PieceUtils.getTypeString((int) target[1]).charAt(0);
        double initialRisk = PieceUtils.getInitialRisk(type);
        target[2] = initialRisk / 2;
        board.updatePieceRisk(x, y, target[2]);
    }
// at hareketleri diğer taşlardan farklı olduğu için özel bir metot oluşturulur
    private void calculateHorseRisk(ChessBoard board, Horse horse) {
        List<int[]> moves = horse.getPossibleMoves(board);
        for (int[] move : moves) {
            int x = move[0];
            int y = move[1];
            double[] target = board.getPiece(x, y);
            if (target != null) {
                if (target[0] != (horse.getColor() == 'b' ? 0 : 1)) {
                    reduceRisk(target, board, x, y);
                }
            }
        }
    }

    public double getTotalRisk(ChessBoard board, char color) {
        double totalRisk = 0.0;
        int colorCode = (color == 'b') ? 0 : 1;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                double[] piece = board.getPiece(i, j);
                if (piece != null && piece[0] == colorCode) {
                    totalRisk += piece[2];
                }
            }
        }
        return totalRisk;
    }
}
