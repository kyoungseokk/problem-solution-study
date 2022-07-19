package DAY01.P3055;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    //                      좌, 우, 위, 아래
    static final int[] MX = {-1, 1, 0, 0};
    static final int[] MY = {0, 0, -1, 1};

    static int R, C;
    static char[][] map;
    static int dp[][];
    static Queue<Point> queue;
    static boolean foundAnswer;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("src/DAY01/P3055/input.txt"));
        Scanner sc = new Scanner(System.in);

        R = sc.nextInt();
        C = sc.nextInt();

        map = new char[R][C];
        dp = new int[R][C];
        queue = new LinkedList<>();
        Point st = null;

        for (int r = 0; r < R; r++) {
            String line = sc.next();
            for (int c = 0; c < C; c++) {
                map[r][c] = line.charAt(c);
                if (map[r][c] == '*') {
                    queue.add(new Point(r, c, '*'));
                } else if (map[r][c] == 'S') {
                    st = new Point(r, c, 'S');
                }
            }
        }

        queue.add(st);

        // Queue [ *, S ]

        while (!queue.isEmpty()) {
            // 1. 큐에서 꺼내옴 (이동할 곳) -> *, S, ., D 4가지 케이스 나올 수 있음
            Point p = queue.poll();
            // 2. 목적지인가? D
            if (p.type == 'D') {
                // 답 출력
                foundAnswer = true;
                break;
            }
            // 3. 연결된 곳을 순환 -> 좌우위아래
            for (int i = 0; i < 4; i++) {
                int ty = p.y + MY[i];
                int tx = p.x + MX[i];
                // 4. 갈 수 있는가? (공통) : 맵 안에 들어오는가
                if (0 <= ty && ty < R && 0 <= tx && tx < C) {
                    if (p.type == 'S' || p.type == '.') {
                        // 4. 갈 수 있는가? (고슴도치) : . or D, 방문하지 않은 곳
                        if ((map[ty][tx] == '.' || map[ty][tx] == 'D') && dp[ty][tx] == 0) {
                            // 5. 체크인 (고슴도치) dp[][] = 이동거리
                            dp[ty][tx] = dp[p.y][p.x] + 1;
                            // 6. 큐에 넣음
                            queue.add(new Point(ty, tx, map[ty][tx]));
                        }
                    } else if (p.type == '*') {
                        // 4. 갈 수 있는가? (물) : .만 가능하다.
                        if (map[ty][tx] == '.' || map[ty][tx] == 'S') { // 시작점도 물로 덮일 수 있다.
                            // 5. 체크인 (물) map[][] = *
                            map[ty][tx] = '*';
                            // 6. 큐에 넣음
                            queue.add(new Point(ty, tx, '*'));
                        }
                    }
                }
            }
        }
    }
}

class Point {
    int y;
    int x;
    char type;

    public Point(int y, int x, char type) {
        this.y = y;
        this.x = x;
        this.type = type;
    }
}
