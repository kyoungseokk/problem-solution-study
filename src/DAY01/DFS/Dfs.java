package DAY01.DFS;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Dfs {

    static int N, K;
    static String[] words;
    static boolean[] visited;
    static int selectedCount;
    static int max;


    public static void main(String[] args) throws FileNotFoundException {

        System.setIn(new FileInputStream("src/DAY01/DFS/input.txt"));
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        words = new String[N];
        visited = new boolean[26];
        visited['a' - 'a'] = true;
        visited['n' - 'a'] = true;
        visited['t' - 'a'] = true;
        visited['i' - 'a'] = true;
        visited['c' - 'a'] = true;

        for (int i = 0; i < N; i++) {
            words[i] = sc.next().replaceAll("[antic]", "");
        }

        System.out.println(Arrays.toString(words));
        selectedCount = 5;
        max = countWord();

        for (int i = 0; i < 26; i++) {
            if (visited[i] == false) {
                dfs(i);
            }
        }

        System.out.println(max);
    }

    // 1. 체크인
    // 2. 목적지인가? : depth (selectedCount) == K => 읽을 수 있는 단어 계산
    // 3. 연결된 곳을 순회 : index번쨰 알파벳을 방문해라 index + 1 ~ 25
    // 4. 갈 수 있는가? dfs : 방문 여부
    // 5. 간다 dfs()
    // 6. 체크아웃

    static void dfs(int index) {
        // 1. 체크인
        visited[index] = true;
        selectedCount++;
        // 2. 목적지인가? : depth (selectedCount) == K => 읽을 수 있는 단어 계산
        if (selectedCount == K) {
            // 계산
            max = Math.max(max, countWord());
        } else {
            // 3. 연결된 곳을 순회 : index번쨰 알파벳을 방문해라 index + 1 ~ 25
            for (int i = index + 1; i <= 25; i++) {
                // 4. 갈 수 있는가? dfs : 방문 여부
                if (visited[i] == false) {
                    // 5. 간다 dfs()
                    dfs(i);
                }
            }
        }
        // 6. 체크아웃
        visited[index] = false;
        selectedCount--;
    }

    static int countWord() {
        int count = 0;
        for (int n = 0; n < N; n++) {
            boolean isPossible = true;
            String word = words[n];
            for (int i = 0; i < word.length(); i++) {
                if (visited[word.charAt(i) - 'a'] == false) {
                    isPossible = false;
                    break;
                }
            }
            if (isPossible) {
                count++;
            }
        }

        return count;
    }
}
