package ai0929.exception;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ThrowsTest2 {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new FileReader("myData1.txt"));

        while (true) {
            String line = br.readLine();
            if(line == null) {
                break;
            }

            System.out.println(line);
        }
    }
}
