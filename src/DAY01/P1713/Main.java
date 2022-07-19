package DAY01.P1713;

import java.util.*;

public class Main {
    static int N, K;
    static Nominee[] nominees;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();

        nominees = new Nominee[101];

        List<Nominee> list = new ArrayList<>();

        for (int k = 0; k < K; k++) {
            int num = sc.nextInt();
            //해당 후보가 최초 호출 시
            if(nominees[num] == null) {
                nominees[num] = new Nominee(num, 0, 0,false);
            }
            if (nominees[num].isIn == true) {
                //해당 후보가 사진틀에 있을 경우
                nominees[num].count++;
            } else {
                //해당 후보가 사진 틀에 없음
                //사진틀이 가득 찬 경우
                if (list.size() == N) {
                    //정렬, 지울 후보 선정, 제거
                    Collections.sort(list);
                    Nominee nominee = list.remove(0);
                    nominee.isIn = false;
                }
                //사진틀에 여유가 있는 경우
                nominees[num].count = 1;
                nominees[num].isIn = true;
                nominees[num].timeStamp = k;
                list.add(nominees[num]);
            }
        }

        Collections.sort(list, new Comparator<Nominee>() {
            @Override
            public int compare(Nominee o1, Nominee o2) {
                return Integer.compare(o1.num, o2.num);
            }
        });
    }

}

class Nominee implements Comparable<Nominee> {
    int num;
    int count;
    int timeStamp;
    boolean isIn;

    public Nominee(int num, int count, int timeStamp, boolean isIn) {
        this.num = num;
        this.count = count;
        this.timeStamp = timeStamp;
        this.isIn = isIn;
    }

    @Override // 1. 추천수 2. 시간
    public int compareTo(Nominee o) {
        int comp = Integer.compare(count, o.count); // count기준 오름차순
        if (comp == 0) {
            return Integer.compare(timeStamp, o.timeStamp); // 타임스탬프 기준 오름차순
        } else {
            return comp;
        }
    }
}