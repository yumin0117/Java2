package ai0922;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LAB_FileCope {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("myData1.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("newFile.txt"));

            String line = "";

            while (true) {
                line = br.readLine();
                if (line == null)
                    break;

                bw.write(line);
                bw.newLine();
            }

            bw.close();
            br.close();

            System.out.println("파일 복사가 성공적으로 완료되었습니다!");

        } catch (FileNotFoundException e) {
            System.out.println("원본 파일을 찾을 수 없습니다.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}