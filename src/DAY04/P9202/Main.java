package DAY04.P9202;

        import java.io.BufferedReader;
        import java.io.InputStreamReader;

public class Main {
    static int[] mx = {-1, 1, 0, 0, -1, 1, -1, 1};
    static int[] my = {0, 0, -1, 1, -1, -1, 1, 1};
    static int[] score = {0, 0, 0, 1, 1, 2, 3, 5, 11};

    static int W, N;
    static char[][] map;
    static boolean[][] visited;
    static String answer;
    static int sum;
    static int count;
    static StringBuilder sb = new StringBuilder();
    static TrieNode root = new TrieNode();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        W = Integer.parseInt(br.readLine());

        for (int i = 0; i < W; i++) {
            insertTireNode(br.readLine());
        }

        br.readLine();
        N = Integer.parseInt(br.readLine());
        StringBuilder resultSb = new StringBuilder();
        for (int n = 0; n < N; n++) {
            map = new char[4][4];
            visited = new boolean[4][4];
            answer = "";
            sum = 0;
            count = 0;
            sb = new StringBuilder();

            for (int i = 0; i < 4; i++) {
                String in = br.readLine();
                for (int k = 0; k < 4; k++) {
                    map[i][k] = in.charAt(k);
                }
            }
            br.readLine();
            for (int y = 0; y < 4; y++) {
                for (int x = 0; x < 4; x++) {
                    //출발 가능 조건 -> root가 해당 child를 가지면
                    if (root.hasChild(map[y][x])) {
                        search(y, x, root.getChild(map[y][x]));
                    }
                }
            }
            //결과 출력
            root.clearHit();
        }
        System.out.println(resultSb.toString());

    }

    static void search(int y, int x, TrieNode node) {
        // 1. 체크인 -> visited
        visited[y][x] = true;
        sb.append(map[y][x]);
        // 2. 목적지에 도달하였는가? -> isWord == true, isHit == false
        if (node.isWord == true && node.isHit == false) {
            node.isHit = true;
            //답 작업 -> 길이, 단어
            String foundWord = sb.toString();
            int length = foundWord.length();
        }
        // 3. 연결된 곳을 순회 -> 8방
        for (int i = 0; i < 8; i++) {
            int ty = y + my[i];
            int tx = x + mx[i];
            // 4. 가능한가? - map경계, 방문하지 않았는지, node가 해당 자식을 가지고 있는지
            if (0 <= ty && ty < 4 && 0 <= tx && tx < 4) {
                if (visited[ty][tx] == false && node.hasChild(map[ty][tx])) {
                    // 5. 간다
                    search(ty, tx, node.getChild(map[ty][tx]));
                }
            }
        }
        // 6. 체크아웃
        visited[y][x] = false;
        sb.deleteCharAt(sb.length() - 1);
    }

    static void insertTireNode(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char a = word.charAt(i);
            int index = a - 'A';
            if (current.chlid[index] == null) {
                current.chlid[index] = new TrieNode();
            }
            current = current.chlid[index];
        }
        current.isWord = true;
    }
}

class TrieNode {
    boolean isWord = false;
    boolean isHit = false;
    TrieNode[] chlid = new TrieNode[26];

    void clearHit() {
        isHit = false;
        for (int i = 0; i < chlid.length; i++) {
            if (chlid[i] != null) {
                chlid[i].clearHit();
            }
        }
    }

    boolean hasChild(char c) {
        return chlid[c - 'A'] != null;
    }

    TrieNode getChild(char c) {
        return chlid[c - 'A'];
    }
}

// package DAY04.P9202;
//
//public class Main {
//    static TrieNode root;
//    static StringBuilder sb = new StringBuilder(); // string 쓸 때 좀 더 빠르게 처리 가능 , multiple thread에서 안전하지 않음
//    static StringBuffer stringBuffer = new StringBuffer(); // thread-safe
//
//    public static void main(String[] args) {
//
//    }
//
//    static void insertTrieNode(String word) {
//        TrieNode current = root;
//        for (int i = 0; i < word.length(); i++) {
//            char a = word.charAt(i);
//            int index = a - 'A';
//            if (current.child[index] == null) {
//                current.child[index] = new TrieNode();
//            }
//            current = current.child[index];
//        }
//        current.isWord = true;
//    }
//
//    //y를 먼저 써주는게 좋다
//    static void search(int y, int x, TrieNode node) {
//        // 1. 체크인 -> visited
//        visited[y][x] = true;
//        sb.append(map[y][x]);
//        // 2. 목적지에 도달하였는가? -> isWord == true, isHit == false;
//        if (node.isWord == true && node.isHit == false) {
//            node.isHit = true;
//            //답 작업 -> 길이, 단어
//            String foundWord = sb.toString();
//            int length = foundWord.length();
//        }
//        // 3. 연결된 곳을 순회 - 8방 int[] MX, MY;
//        for (int i = 0; i < 8; i++) {
//            int ty = y + my[i];
//            int tx = x + mx[i];
//            if (0 <= ty && ty < 4 && 0 <= tx && tx < 4) {
//                if (visited[ty][tx] == false && node.hasChild(map[ty][tx])) {
//                    // 5. 간다
//                    search(ty, tx, node.getChild(map[ty][tx]));
//                }
//            }
//        }
//
//        // 6. 체크아웃
//        visited[y][x] = false;
//        sb.deleteCharAt(sb.length() - 1);
//    }
//}
//
//class TrieNode {
//    boolean isWord = false;
//    boolean isHit = false;
//    TrieNode[] child = new TrieNode[26];
//
//    void clearHit() {
//        isHit = false;
//
//        for (int i = 0; i < child.length; i++) {
//            if (child[i] != null) {
//                child[i].clearHit();
//            }
//        }
//    }
//
//    boolean hasChild(char c) {
//        return child[c - 'A'] != null;
//    }
//
//    TrieNode
//
//}