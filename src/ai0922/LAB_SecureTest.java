package ai0922;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LAB_SecureTest {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        try {
            FileWriter fw = new FileWriter("secretMessage.txt");
            System.out.println("스파이에게 보낼 메시지를 입력하세요. (종료하려면 'exit' 입력)");

            while (true) {
                System.out.print("> ");
                String inStr = s.nextLine();

                if (inStr.equals("exit")) {
                    break;
                }

                String secure = "";
                for (int i = 0; i < inStr.length(); i++) {
                    int num = (int) inStr.charAt(i);
                    num += 100;
                    secure += (char) num;
                }

                fw.write(secure + "\n");
            }

            fw.close();
            s.close();
            System.out.println("암호화된 메시지가 secretMessage.txt 파일에 저장되었습니다.");

        } catch (IOException e) {
            System.out.println("파일 저장 중 오류가 발생했습니다.");
        }
    }
}