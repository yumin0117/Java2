package ai0825;

import java.util.Random;

public class LABRandomWiseSaying {
    public static void main(String[] args) {
        String[] wiseSay= {
                "삶이 있는 한 희망은 있다 -키케로",
                "산다는것 그것은 치열한 전투이다. -로망로랑",
                "하루에 3시간을 걸으면 7년 후에 지구를 한바퀴 돌 수 있다. -사무엘존슨",
                "언제나 현재에 집중할수 있다면 행복할것이다. -파울로 코엘료",
                "진정으로 웃으려면 고통을 참아야하며, 나아가 고통을 즐길 줄 알아야 해 -찰리 채플린",
                "직업에서 행복을 찾아라. 아니면 행복이 무엇인지 절대 모를 것이다 -엘버트 허버드",
                "신은 용기있는자를 결코 버리지 않는다 -켄러",
                "피할수 없으면 즐겨라 – 로버트 엘리엇",
                "단순하게 살아라. 현대인은 쓸데없는 절차와 일 때문에 얼마나 복잡한 삶을 살아가는가? -이드리스 샤흐",
                "먼저 자신을 비웃어라. 다른 사람이 당신을 비웃기 전에 – 엘사 맥스웰"
        };

        Random random = new Random();
        int randomIndex = random.nextInt(wiseSay.length);
        System.out.println("오늘의 명언: " + wiseSay[randomIndex]);
    }
}
