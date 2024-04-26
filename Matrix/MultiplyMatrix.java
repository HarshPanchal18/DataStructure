public class MultiplyMatrix {

    public static void main(String[] args) {

        int matrix1[][] = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };

        int matrix2[][] = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 }
        };

        int size = matrix1[0].length;

        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                System.out.print(matrix1[row][column] * matrix2[row][column] + " ");
            }
            System.out.println();
        }
    }
}