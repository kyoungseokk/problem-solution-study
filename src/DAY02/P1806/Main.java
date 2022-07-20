package DAY02.P1806;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, S;
    static int[] num;


    public static void main(String[] args) throws IOException {
        //low = 0, hi = 0, sum = 1부터
        //H == size()
        System.setIn(new FileInputStream("src/DAY01/P1806/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        num = new int[N + 1];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(Arrays.toString(num));

        int low = 0, high = 0, sum = num[0], len = Integer.MAX_VALUE;

        while (true) {
            if (sum >= S) {
                len = Math.min(high - low + 1, len);
                sum -= num[low++];
            } else {
                sum += num[++high];
            }

            if (high == N) {
                break;
            }
        }

        if (len == Integer.MAX_VALUE) {

        }


    }
}
