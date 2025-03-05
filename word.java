package ap;

import java.lang.String;
import java.util.Scanner;

public class word {
    public static void main(String[] args) {

        System.out.println("Enter a word: ");
        Scanner in = new Scanner(System.in);
        String word = in.nextLine();
        char[] ch = new char[word.length()];
        ch = word.toCharArray();
        int tf = 0;
        while (true) {
            System.out.println("1)find uppercase");
            System.out.println("2)second character");
            System.out.println("3)vowels replaced by an underscore");
            System.out.println("4)number of vowel sounds");
            System.out.println("5)index of vowel sound");
            System.out.println("6)exit");
            System.out.println("select(1-6)");
            int select = in.nextInt();
            switch (select) {
                case 1:
                    for (int i = 0; i < ch.length; i++) {
                        if (ch[i] >= 65 && ch[i] <= 90) {
                            System.out.println(ch[i]);
                            tf++;
                        }
                    }
                    if (tf == 0) {
                        System.out.println("not found");
                    }
                    break;
                case 2:
                    char save = 0;
                    for (int i = 0; i < ch.length; i++) {
                        save = ch[1];
                    }
                    System.out.println(save);
                    break;
                case 3:
                    for (int i = 0; i < ch.length; i++) {
                        if (ch[i] == 'a' || ch[i] == 'A' || ch[i] == 'e' || ch[i] == 'E' || ch[i] == 'i' || ch[i] == 'I' || ch[i] == 'o' || ch[i] == 'O' || ch[i] == 'u' || ch[i] == 'U') {
                            ch[i] = '_';
                            tf++;
                        }
                    }
                    for (int i = 0; i < ch.length; i++) {
                        System.out.print(ch[i]);
                    }
                    System.out.println();
                    if (tf == 0) {
                        System.out.println("not found");
                    }
                    break;
                case 4:
                    int count = 0;
                    for (int i = 0; i < ch.length; i++) {
                        if (ch[i] == 'a' || ch[i] == 'A' || ch[i] == 'e' || ch[i] == 'E' || ch[i] == 'i' || ch[i] == 'I' || ch[i] == 'o' || ch[i] == 'O' || ch[i] == 'u' || ch[i] == 'U') {
                            count++;
                        }
                    }
                    System.out.println(count);
                    break;
                case 5:
                    for (int i = 0; i < ch.length; i++) {
                        if (ch[i] == 'a' || ch[i] == 'A' || ch[i] == 'e' || ch[i] == 'E' || ch[i] == 'i' || ch[i] == 'I' || ch[i] == 'o' || ch[i] == 'O' || ch[i] == 'u' || ch[i] == 'U') {
                            System.out.println("index=" + (i + 1));
                            tf++;
                        }
                    }
                    if (tf == 0) {
                        System.out.println("not found");
                    }
                    break;
                case 6:
                    in.close();
                    return;
            }
        }

    }
}
