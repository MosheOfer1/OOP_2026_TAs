package TA6;

// Component interface
interface TextPrinter {
    String printText();
}

// Concrete component
class SimpleTextPrinter implements TextPrinter {
    private String text;

    public SimpleTextPrinter(String text) {
        this.text = text;
    }

    @Override
    public String printText() {
        return text;
    }
}

// Decorator abstract class
abstract class TextDecorator implements TextPrinter {
    protected TextPrinter textPrinter;

    public TextDecorator(TextPrinter textPrinter) {
        this.textPrinter = textPrinter;
    }
}

// Concrete decorators
class BoldTextDecorator extends TextDecorator {
    public BoldTextDecorator(TextPrinter textPrinter) {
        super(textPrinter);
    }

    @Override
    public String printText() {
        return "\033[1m" + textPrinter.printText() + "\033[0m"; // ANSI escape for bold
    }
}

class RedTextDecorator extends TextDecorator {
    public RedTextDecorator(TextPrinter textPrinter) {
        super(textPrinter);
    }

    @Override
    public String printText() {
        return "\033[91m" + textPrinter.printText() + "\033[0m"; // ANSI escape for red
    }
}

class UnderlineTextDecorator extends TextDecorator {
    public UnderlineTextDecorator(TextPrinter textPrinter) {
        super(textPrinter);
    }

    @Override
    public String printText() {
        return "\033[4m" + textPrinter.printText() + "\033[0m"; // ANSI escape for underline
    }
}

class ItalicTextDecorator extends TextDecorator {
    public ItalicTextDecorator(TextPrinter textPrinter) {
        super(textPrinter);
    }

    @Override
    public String printText() {
        return "\033[3m" + textPrinter.printText() + "\033[0m"; // ANSI escape for italic
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

        TextPrinter simpleTextPrinter2 = new SimpleTextPrinter("Coll Text");
        TextPrinter boldTextPrinter2 = new BoldTextDecorator(simpleTextPrinter2);
        TextPrinter redTextPrinter2 = new RedTextDecorator(boldTextPrinter2);
        TextPrinter ans = new ItalicTextDecorator(redTextPrinter2);

        System.out.println(ans.printText());
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
