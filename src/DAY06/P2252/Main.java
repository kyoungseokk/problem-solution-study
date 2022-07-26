package DAY06.P2252;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] in = new int[N + 1];
        ArrayList<Integer>[] adj = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adj[from].add(to);
            in[to]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < N + 1; i++) {
            if (in[i] == 0) {
                q.add(i);
            }
        }

        while (q.isEmpty()) {
            int now = q.poll();
            sb.append(now).append(" ");
            for (int next : adj[now]) {
                in[next]--;
                if (in[next] == 0) {
                    q.add(next);
                }
            }
        }
        System.out.println(sb);
    }
}
