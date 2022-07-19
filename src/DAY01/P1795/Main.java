package DAY01.P1795;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static boolean[] visited;
    static int L;
    static int C;
    static char[] data;
    static List<String> result;
    static String pwd;

    public static void main(String[] args) throws FileNotFoundException {

        System.setIn(new FileInputStream("src/DAY01/P1795/input.txt"));
        Scanner sc = new Scanner(System.in);
        L = sc.nextInt();
        C = sc.nextInt();

        data = new char[C];
        result = new LinkedList<>();
        pwd = "";
        for (int i = 0; i < C; i++) {
            data[i] = sc.next().charAt(0);
        }

        Arrays.sort(data);
        System.out.println(Arrays.toString(data));
        dfs(0, 0, 0 , -1, pwd);

        System.out.println(result);
    }

    static void dfs(int length, int ja, int mo, int current, String pwd) {
        // 1. 체크인 - 정렬하면 생략 가능
        // 2. 목적지인가? : length == L => ja 개수, mo 개수 확인 암호 가능 판별
        if (length == L) {
            if (ja >= 2 && mo >= 1) {
                // 정답처리
                result.add(pwd);
            }
        } else {
            // 3. 연결된 곳 순회 : current + 1 ~ C
            for (int i = current + 1; i < C; i++) {
                // 4. 갈 수 있는가? : 정렬했기 떄문에 다 갈 수 있다.
                // 5. 간다 -> ja, mo 2가지 케이스로 나뉨
                if (data[i] == 'a' || data[i] == 'e' || data[i] == 'i' || data[i] == 'o' || data[i] == 'u') {
                    dfs(length + 1, ja, mo + 1, i, pwd + data[i]);
                } else {
                    dfs(length + 1, ja + 1, mo, i, pwd + data[i]);
                }
            }
        }
        // 6. 체크아웃 - 생략가능
    }


}
