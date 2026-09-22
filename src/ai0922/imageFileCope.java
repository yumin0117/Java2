package ai0922;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

// 이미지파일과 같은 이진파일은 1byte씩 읽어서 1byte씩 출력해야한다.
public class imageFileCope {
    public static void main(String[] args) {
        try{
            FileInputStream fIn = new FileInputStream("image.jpg");
            FileOutputStream fOut = new FileOutputStream("imageCope.jpg");

            int data;
            while((data = fIn.read()) != -1){
                fOut.write((byte)data);
            }

            System.out.println("이미지 복사 완료");

            fIn.close();
            fOut.close();
        } catch(FileNotFoundException e){
            throw new RuntimeException(e);
        } catch (IOException e){
            throw new RuntimeException();
        }

    }
}
