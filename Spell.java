import java.util.Arrays;
import java.util.Scanner;

public class Spell {
    public static void main(String[] args) {
        System.out.println("enter your word");
        Scanner in = new Scanner(System.in);
        String word = in.nextLine();
        char[] spell = new char[word.length()];
        spell = word.toCharArray();
        for (int i = 0; i < word.length(); i++) {
            System.out.println(spell[i]);
        }
        in.close();
    }
}
