public class CompareVersionNumber {

    // Method to compare two version numbers
    public static int compareVersion(String A, String B) {
        if (A == null || B == null)
            return 0;

        String[] v1 = A.split("\\.");
        String[] v2 = B.split("\\.");

        int i = 0;
        int j = 0;

        while (i < v1.length || j < v2.length) {
            double x = i < v1.length ? Double.parseDouble(v1[i]) : 0;
            double y = j < v2.length ? Double.parseDouble(v2[i]) : 0;

            if (x > y)
                return 1;
            else if (x < y)
                return -1;
            i++;
            j++;
        }

        return 0;
    }

    public static void main(String[] args) {
        System.out.println(compareVersion("4444371174137455", "5.168"));
    }
}
