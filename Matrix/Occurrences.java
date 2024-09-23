import java.util.Scanner;

public class Occurrences {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int size = scan.nextInt();
        scan.nextLine();

        char[][] matrix = new char[size][size];

        for (int i = 0; i < size; i++) {
            String line = scan.nextLine();
            for (int j = 0; j < size; j++) {
                matrix[i][j] = line.charAt(j);
            }
        }

        System.out.println("Word:");
        String word = scan.nextLine();

        int result = count(matrix, word);
        System.out.println(result);

        scan.close();
    }

    // Directions: right, down, diagonal down-right, diagonal down-left
    private static final int[] rowDir = { 0, 0, 1, -1, 1, -1, 1, -1 };
    private static final int[] colDir = { 1, -1, 0, 0, 1, -1, -1, 1 };

    public static int count(char[][] matrix, String word) {
        int counter = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int wordLen = word.length();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int dir = 0; dir < 8; dir++) {
                    if (searchWord(matrix, word, i, j, rowDir[dir], colDir[dir], wordLen)) {
                        counter++;
                    }
                }
            }
        }

        return counter;
    }

    private static boolean searchWord(
            char[][] matrix, String word, int row, int col, int rowDir, int colDir, int wordLen) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int k = 0; k < wordLen; k++) {
            int newRow = row + k * rowDir;
            int newCol = col + k * colDir;

            if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols
                    || matrix[newRow][newCol] != word.charAt(k)) {
                return false;
            }
        }

        return true;
    }
}
