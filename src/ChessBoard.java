import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class ChessBoard {
    private double[][][] board;
    private List<Piece> pieces;
    public ChessBoard() {
        board = new double[8][8][3];
        pieces = new ArrayList<>();
        initializeBoard();
    }
// boş bir satranç tahtası oluşturma
    private void initializeBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j][0] = -1;
                board[i][j][1] = -1;
                board[i][j][2] = 0;
            }
        }
    }
// taşları satranç tahtasına yerleştirme
    public void loadFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("[, ]+");
                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);
                char color = parts[2].charAt(0);
                char type = parts[3].charAt(0);
                double risk = PieceUtils.getInitialRisk(type);

                board[row][col][0] = (color == 'b') ? 0 : 1;
                board[row][col][1] = PieceUtils.getTypeValue(type);
                board[row][col][2] = risk;

                switch (type) {
                    case 'p':
                        pieces.add(new Pawn(row, col, color));
                        break;
                    case 'k':
                        pieces.add(new Rook(row, col, color));
                        break;
                    case 'f':
                        pieces.add(new Bishop(row, col, color));
                        break;
                    case 'v':
                        pieces.add(new Queen(row, col, color));
                        break;
                    case 's':
                        pieces.add(new King(row, col, color));
                        break;
                    case 'a':
                        pieces.add(new Horse(row, col, color));
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//belirtilen konumda bir taşın olup olmadığı
    public double[] getPiece(int row, int col) {
        if (board[row][col][0] != -1) {
            return new double[] { board[row][col][0], board[row][col][1], board[row][col][2] };
        }
        return null;
    }

    public void updatePieceRisk(int row, int col, double newRisk) {
        board[row][col][2] = newRisk;
    }

    public void displayBoard() {
        char[] columns = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
        System.out.print("   ");
        for (char col : columns) {
            System.out.print("  " + col + "  ");
        }
        System.out.println();

        for (int i = 0; i < 8; i++) {
            System.out.print(8 - i + " ");
            for (int j = 0; j < 8; j++) {
                if (board[i][j][0] == -1) {
                    System.out.print("----- ");
                } else {
                    String color = board[i][j][0] == 0 ? "b" : "s";
                    String type = PieceUtils.getTypeString((int) board[i][j][1]);
                    double risk = board[i][j][2];
                    System.out.printf("%s%s%.1f ", color, type, risk);
                }
            }
            System.out.printf("%2d", 8 - i);
            System.out.println();
        }

        System.out.print("   ");
        for (char col : columns) {
            System.out.print("  " + col + "  ");
        }
        System.out.println();
    }

    public List<Piece> getPieces() {
        return pieces;
    }
}
