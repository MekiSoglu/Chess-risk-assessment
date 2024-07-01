public class Main {
    public static void main(String[] args) {
        ChessBoard boardO = new ChessBoard();
        boardO.loadFromFile("baslangic.txt");
        boardO.displayBoard();

        RiskCalculator calculator = new RiskCalculator();

        double totalRiskWhiteBefore = calculator.getTotalRisk(boardO, 'b');
        double totalRiskBlackBefore = calculator.getTotalRisk(boardO, 's');

        System.out.println("\nRisk hesaplanmadan önce:");
        System.out.println("Beyaz toplam risk: " + totalRiskWhiteBefore);
        System.out.println("Siyah toplam risk: " + totalRiskBlackBefore);

        ChessBoard boardS = new ChessBoard();
        boardS.loadFromFile("t.txt");
        boardS.displayBoard();

        calculator.calculateRisk(boardS);

        double totalRiskWhiteAfter = calculator.getTotalRisk(boardS, 'b');
        double totalRiskBlackAfter = calculator.getTotalRisk(boardS, 's');

        System.out.println("\nRisk hesaplandıktan sonra:");
        System.out.println("Beyaz toplam risk: " + totalRiskWhiteAfter);
        System.out.println("Siyah toplam risk: " + totalRiskBlackAfter);

    }
}