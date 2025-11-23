package DesignPatterns;

import java.util.ArrayList;
import java.util.List;

// Subject interface
abstract class Sender {
    private final List<Member> members = new ArrayList<>();

    public void register(Member member) {
        members.add(member);
    }

    public void unregister(Member member) {
        members.remove(member);
    }

    public void notifyMembers(String newsletter) {
        for (Member member : members) {
            member.update(newsletter);
        }
    }
}

// Observer interface
interface Member {
    void update(String newsletter);
}

// Concrete implementation of Observer (Member)
class Person implements Member {
    private final String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public void update(String newsletter) {
        System.out.println(name + " received newsletter: " + newsletter);
    }
}

// Concrete implementation of Subject (Sender)
class NewsletterPublisher extends Sender {

    public void sendNewsletter(String content) {
        String timestamp = java.time.LocalDateTime.now().toString();
        String newsletter = timestamp + " - Newsletter: " + content;
        System.out.println("Sending newsletter: " + newsletter);
        notifyMembers(newsletter);
    }
}

// Client code using the Observer pattern
public class Observer {

    public static void main(String[] args) {
        // Create a newsletter publisher (subject)
        NewsletterPublisher newsletterPublisher = new NewsletterPublisher();

        // Create subscribers (observers)
        Person subscriber1 = new Person("Alice");
        Person subscriber2 = new Person("Bob");

        // Add subscribers to the newsletter publisher
        newsletterPublisher.register(subscriber1);
        newsletterPublisher.register(subscriber2);

        // Send a newsletter, and subscribers will be notified
        newsletterPublisher.sendNewsletter("Latest Updates");
    }
}
