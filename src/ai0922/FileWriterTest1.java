package ai0922;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileWriterTest1 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        try {
            FileWriter fw = new FileWriter("myData2.txt");

            System.out.println("파일에 저장할 내용을 입력하세요. (종료하려면 'exit' 입력)");

            while (true) {
                System.out.print("> ");
                String line = s.nextLine();

                if (line.equals("exit")) {
                    break;
                }

                fw.write(line + "\n");
            }

            fw.close();
            s.close();
            System.out.println("\n myData2.txt에 내용 저장 완료.");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}