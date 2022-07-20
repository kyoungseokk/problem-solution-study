package DAY02.P2003;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] num;


    public static void main(String[] args) throws IOException {
        //low = 0, hi = 0, sum = 1부터
        //H == size()
        System.setIn(new FileInputStream("src/DAY01/P2003/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        num = new int[N + 1];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }


        int low = 0, high = 0, sum = num[0], count = 0;

        while (true) {
            // sum == M -> 답, low ++
            if (sum == M) {
                count++;
                sum -= num[low++];
            } else if (sum > M) {
                // sum > M -> low++
                sum -= num[low++];
            } else {
                // sum < M -> high++
                sum += num[++high];
            }

            if (high == N) {
                break;
            }
        }

        System.out.println(count);
    }

}
