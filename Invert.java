import java.util.Scanner;

public class Invert {
    public static void main(String[] args) {
        System.out.println("enter your word");
        Scanner in = new Scanner(System.in);
        String word = in.nextLine();
        char[] spell = new char[word.length()];
        spell = word.toCharArray();
        for (int i = word.length() - 1; i >= 0; i--) {
            System.out.print(spell[i]);
        }
        in.close();
    }
}
