package ap.exercises.ex4;

class Letter {
    private String from;
    private String to;
    private String body;

    public Letter(String from, String to) {
        this.from = from;
        this.to = to;
        this.body = "Dear " + to + "\n";
    }

    public void addLine(String line) {
        body = body.concat(line).concat("\n");
    }

    public String getText() {
        body = body.concat("\nSincerely,\n\n" + from);
        return body;
    }
}

public class LetterPrinter {
    public static void main(String[] args) {

        Letter letter = new Letter("Mary", "John");
        letter.addLine("I am sorry we must part.");
        letter.addLine("I wish you all the best.");
        System.out.println(letter.getText());
    }

}
