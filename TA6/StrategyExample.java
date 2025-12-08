package TA6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Define the PaymentStrategy interface
interface PaymentStrategy {
    void pay(int amount);
}

// CreditCardPayment strategy
class CreditCardPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paying " + amount + " using credit card");
    }
}

// PayPalPayment strategy
class PayPalPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paying " + amount + " using PayPal");
    }
}

// BitcoinPayment strategy
class BitcoinPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paying " + amount + " using Bitcoin");
    }
}

// CashPayment strategy
class CashPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paying " + amount + " in cash");
    }
}

// ShoppingCart class that uses a strategy
class ShoppingCart {
    private List<Map<String, Object>> items = new ArrayList<>();
    private PaymentStrategy paymentStrategy;

    public ShoppingCart(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void addItem(Map<String, Object> item) {
        items.add(item);
    }

    public void checkout() {
        int totalAmount = 0;
        for (Map<String, Object> item : items) {
            totalAmount += (int) item.get("price");
        }
        paymentStrategy.pay(totalAmount);
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }
}

// Client code
public class StrategyExample {
    public static void main(String[] args) {
        // Create concrete strategy instances
        PaymentStrategy creditCardPayment = new CreditCardPayment();
        PaymentStrategy paypalPayment = new PayPalPayment();
        PaymentStrategy bitcoinPayment = new BitcoinPayment();

        // Use strategies in the context (ShoppingCart)

        // Cart 1: Credit Card Payment
        ShoppingCart cart1 = new ShoppingCart(creditCardPayment);
        Map<String, Object> item1 = new HashMap<>();
        item1.put("product", "Laptop");
        item1.put("price", 1000);
        cart1.addItem(item1);

        Map<String, Object> item2 = new HashMap<>();
        item2.put("product", "Mouse");
        item2.put("price", 20);
        cart1.addItem(item2);

        cart1.checkout();

        // Cart 2: PayPal Payment
        ShoppingCart cart2 = new ShoppingCart(paypalPayment);
        Map<String, Object> item3 = new HashMap<>();
        item3.put("product", "Headphones");
        item3.put("price", 50);
        cart2.addItem(item3);

        Map<String, Object> item4 = new HashMap<>();
        item4.put("product", "Keyboard");
        item4.put("price", 30);
        cart2.addItem(item4);

//        cart2.setPaymentStrategy(creditCardPayment);
        cart2.checkout();

        // Cart 3: Bitcoin Payment
        ShoppingCart cart3 = new ShoppingCart(bitcoinPayment);
        Map<String, Object> item5 = new HashMap<>();
        item5.put("product", "Smartphone");
        item5.put("price", 500);
        cart3.addItem(item5);

        Map<String, Object> item6 = new HashMap<>();
        item6.put("product", "Tablet");
        item6.put("price", 300);
        cart3.addItem(item6);

        cart3.checkout();
    }
}
