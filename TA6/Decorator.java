package TA6;

// Component interface
interface TextInterface {
    String getText();
}

// Concrete component
class SimpleTextPrinter implements TextInterface {
    private String text;

    public SimpleTextPrinter(String text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return text;
    }
}

// Decorator abstract class
abstract class TextDecorator implements TextInterface {
    protected TextInterface textPrinter;

    public TextDecorator(TextInterface textPrinter) {
        this.textPrinter = textPrinter;
    }
}

// Concrete decorators
class BoldTextDecorator extends TextDecorator {
    public BoldTextDecorator(TextInterface textPrinter) {
        super(textPrinter);
    }

    @Override
    public String getText() {
        return "\033[1m" + textPrinter.getText() + "\033[0m"; // ANSI escape for bold
    }
}

class RedTextDecorator extends TextDecorator {
    public RedTextDecorator(TextInterface textPrinter) {
        super(textPrinter);
    }

    @Override
    public String getText() {
        return "\033[91m" + textPrinter.getText() + "\033[0m"; // ANSI escape for red
    }
}

class UnderlineTextDecorator extends TextDecorator {
    public UnderlineTextDecorator(TextInterface textPrinter) {
        super(textPrinter);
    }

    @Override
    public String getText() {
        return "\033[4m" + textPrinter.getText() + "\033[0m"; // ANSI escape for underline
    }
}

class ItalicTextDecorator extends TextDecorator {
    public ItalicTextDecorator(TextInterface textPrinter) {
        super(textPrinter);
    }

    @Override
    public String getText() {
        return "\033[3m" + textPrinter.getText() + "\033[0m"; // ANSI escape for italic
    }
}

// Client code
public class Decorator {
    public static void main(String[] args) {
//        // Create a simple text printer
//        TextPrinter simpleTextPrinter = new SimpleTextPrinter("Hello, World!");
//
//        // Decorate the text printer with various decorators
//        TextPrinter boldTextPrinter = new BoldTextDecorator(simpleTextPrinter);
//        TextPrinter redTextPrinter = new RedTextDecorator(simpleTextPrinter);
//        TextPrinter underlineTextPrinter = new UnderlineTextDecorator(simpleTextPrinter);
//        TextPrinter italicTextPrinter = new ItalicTextDecorator(simpleTextPrinter);

        TextInterface simpleTextPrinter2 = new SimpleTextPrinter("Coll Text");
        TextInterface boldTextPrinter2 = new BoldTextDecorator(simpleTextPrinter2);
        TextInterface redTextPrinter2 = new RedTextDecorator(boldTextPrinter2);
        TextInterface ans = new ItalicTextDecorator(redTextPrinter2);

        System.out.println(ans.getText());
//
//        // Print the decorated text
//        System.out.println("Simple Text:");
//        System.out.println(simpleTextPrinter.printText());
//
//        System.out.println("\nDecorated Texts:");
//        System.out.println(boldTextPrinter.printText());
//        System.out.println(redTextPrinter.printText());
//        System.out.println(underlineTextPrinter.printText());
//        System.out.println(italicTextPrinter.printText());
    }
}
