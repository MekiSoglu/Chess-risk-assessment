public class PieceUtils {

    public static int getTypeValue(char type) {
        switch (type) {
            case 'p': return 0; // Piyon
            case 'a': return 1; // At
            case 'f': return 2; // Fil
            case 'k': return 3; // Kale
            case 'v': return 4; // Vezir
            case 's': return 5; // Şah
            default: return -1;
        }
    }

    public static double getInitialRisk(char type) {
        switch (type) {
            case 'p': return 1.0; // Piyon
            case 'a': return 3.0; // At
            case 'f': return 3.0; // Fil
            case 'k': return 5.0; // Kale
            case 'v': return 9.0; // Vezir
            case 's': return 100.0; // Şah
            default: return 0.0;
        }
    }

    public static String getTypeString(int type) {
        switch (type) {
            case 0: return "p"; // Piyon
            case 1: return "a"; // At
            case 2: return "f"; // Fil
            case 3: return "k"; // Kale
            case 4: return "v"; // Vezir
            case 5: return "s"; // Şah
            default: return "-";
        }
    }
}
