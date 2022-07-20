package DAY02.P1072;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/DAY02/P1072/input.txt"));
        Scanner sc = new Scanner(System.in);

        long X = sc.nextLong();
        long Y = sc.nextLong();

        long z = 100 * Y / X;

        if (z >= 99) { // 99는 절대 안바뀜
            System.out.println(-1);
        } else {
            long start = 0;
            long end = X;
            // start <= end 하면 같아지는 경우 무한루프에 빠짐
            while (start < end) { // parametric search 는 보통 start와 end가 바뀌면 끝남
                long mid = (start + end) / 2;
                long newRate = (100 * (Y + mid) / (X + mid));
                // 승률이 그대로인 경우
                if (newRate == z) {
                    start = mid + 1;
                }
                // 승률이 변한 경우
                else {
                    end = mid; // mid도 후보가 되서 살려야한다.
                }

            }
            System.out.println(end);
            //parametric search에서는 mid를 살릴지 말지 판단하는 것이 중요

        }
    }
}
