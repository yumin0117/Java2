package ai0908;

public class VoidTest {
    public static void printLine(String c, int count){
        for (int i = 0; i < count; i++) {
            System.out.print(c);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String[] imojis = {"❤️", "🍕", "😍", "🌼", "🧸", "🎄", "🐻"};

        for (int i = 0; i < imojis.length; i++) {
            printLine(imojis[i], (i+1)*10);
        }
    }
}
