package ai0922;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LAB_DecodeSecureTest {
    public static void main(String[] args) {
        File file = new File("secretMessage.txt");

        try {
            Scanner fileScanner = new Scanner(file);
            System.out.println("=== 암호 해독 결과 ===");

            while (fileScanner.hasNextLine()) {
                String secureStr = fileScanner.nextLine();
                String decoded = "";

                for (int i = 0; i < secureStr.length(); i++) {
                    int num = (int) secureStr.charAt(i);
                    num -= 100;
                    decoded += (char) num;
                }

                System.out.println(decoded);
            }

            fileScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("해독할 암호 파일(secretMessage.txt)을 찾을 수 없습니다.");
        }
    }
}