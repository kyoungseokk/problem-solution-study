package DAY02.P2805;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] trees;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/DAY01/P2805/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        trees = new int[N];

        int max = 0;

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            max = Math.max(trees[i], max);
        }

        long s = 0;
        long e = max;
        long mid;
        long result = 0;
        // 절단기 높이 H 지정
        // H보다 높으면 자르고 아니면 둔다.

        while (true) { // 돌기 전에 검증
            mid = (s + e) / 2;
            long sum = calc(mid);
            // sum == M
            if (sum == M) {
                result = mid;
                break;
            } else if (sum < M) {
                // sum < M -> mid -> e
                e = mid - 1;
            } else {
                // sum > M -> mid -> s mid를 살린다 버린다 기준은 유효한가 유효하지 않은가
                result = mid;
                s = mid + 1;
            }

            //========================================논리적 구분분

           if (s > e) {
                break; // while 안에 넣어도 되나 여기서는 돌고나서 검증
            }
        }

        System.out.println(result);

    }

    static long calc(long value) {
        long result = 0;
        
        for (int i=0; i<trees.length; i++) {
            int tree = trees[i];
            if (tree > value) {
                result += tree - value;
            }
        }
        
        return result;
    }
}
