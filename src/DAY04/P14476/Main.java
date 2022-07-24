package DAY04.P14476;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] nums;
    static int[] gcdLtoR;
    static int[] gcdRtoL;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        gcdLtoR = new int[N];
        gcdRtoL = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine()); //br.readline

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        //LtoR 만들기
        gcdLtoR[0] = nums[0];
        for (int i = 1; i < N; i++) {
            gcdLtoR[i] = gcd(gcdLtoR[i - 1], nums[i]);
        }

        //RtoL 만들기
        gcdRtoL[N - 1] = nums[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            gcdRtoL[i] = gcd(gcdRtoL[i + 1], nums[i]);
        }

        // k 선정하기
        int max = 0;
        int maxIndex = 0;

        for (int i = 0; i < N; i++) {
            int gcd = 0;
            // i = 0일 경우
            if (i == 0) {
                gcd = gcdRtoL[1];
            }
            // N - 1의 경우
            else if (i == N - 1) {
                gcd = gcdLtoR[N - 2];
            }
            // 중간
            else {
                gcd = gcd(gcdLtoR[i - 1], gcdRtoL[i + 1]);
            }

            if (nums[i] % gcd != 0 && gcd > max) {
                max = gcd;
                maxIndex = i;
            }
        }
    }

    //gcd(a,b) == gcd(b,r) == gcd(b, a % b), stop, when a % b == 0
    static int gcd(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    } // while문으로 하는 이유 : 확장 유클리드 호제법 때문
}
