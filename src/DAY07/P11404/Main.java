package DAY07.P11404;

public class Main {

    static int[][] dp; // dp[n][n];
    static int N;
    

    public static void main(String[] args) {

    }

    static void floyd() {
        for (int k = 1; k <= N; k++) { // 모든 경유지를 거침
            for (int i = 1; k <= N; i++) {
                for (int j = 0; i <= N; i++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }
    }
}
