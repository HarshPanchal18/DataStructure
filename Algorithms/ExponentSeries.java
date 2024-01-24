import java.util.Scanner;

// https://www.hackerrank.com/challenges/java-loops/problem

class ExponentSeries {
    public static void main(String[] argh) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int n = in.nextInt();
            int sum = 0;

            for (int j = 0; j < n; j++) {

                double power = Math.pow(2, j);
                double series = power * b;

                if (j == 0)
                    series += a;

                sum += series;

                System.out.print(sum + " ");
            }
            System.out.println();
        }
        in.close();
    }
}