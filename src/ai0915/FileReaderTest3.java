package ai0915;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderTest3 {
//    파일의 내용 전체를 거꾸로 출력
    public static void main(String[] args) {
        try{
            StringBuilder sb = new StringBuilder();
//            File를 읽어오기 위한 입텩스트림(InputStream) 생성
            BufferedReader br = new BufferedReader(new FileReader("myData1.txt"));
            String line = "";


//            File의 끝까지 File에서 한 줄씩 읽어오기
            while (true){
                line = br.readLine();
                if (line == null)
                    break;
                sb.append(line).append("\n");
            }

//            FileInputStream 닫기
            br.close();

            sb.reverse();
            System.out.println(sb);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
