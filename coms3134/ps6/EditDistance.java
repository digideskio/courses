public class EditDistance {

    public static int editDistance(String s, String t) {
        if (s.isEmpty())
            return t.length();
        if (t.isEmpty())
            return s.length();
        int[][] distances = buildTable(s, t);
        return distances[s.length()][t.length()];
    }

    private static int[][] buildTable(String s, String t) {
        int sLength = s.length();
        int tLength = t.length();
        int[][] distances = new int[sLength + 1][tLength + 1];
        distances[0][0] = 0;
        for (int i = 1; i < sLength + 1; i++) {
            distances[i][0] = i;
        }
        for (int j = 1; j < tLength + 1; j++) {
            distances[0][j] = j;
        }
        for (int i = 1; i < sLength + 1; i++) {
            for (int j = 1; j < tLength + 1; j++) {
                int d1 = distances[i - 1][j] + 1;
                int d2 = distances[i][j - 1] + 1;
                int d3 = distances[i - 1][j - 1];
                if (s.charAt(i - 1) != t.charAt(j - 1))
                    d3++;
                if (d1 < d2 && d1 < d3)
                    distances[i][j] = d1;
                else if (d2 < d1 && d2 < d3)
                    distances[i][j] = d2;
                else
                    distances[i][j] = d3;
            }
        }
        return distances;
    }
}