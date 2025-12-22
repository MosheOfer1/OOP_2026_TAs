from abc import ABC, abstractmethod

# Interface / Abstraction
class PaymentProcessor(ABC):
    @abstractmethod
    def pay(self, amount):
        pass

# Concrete implementations
class PayPalProcessor(PaymentProcessor):
    def __init__(self, email):
        self.email = email

    def pay(self, amount):
        print(f"Paying {amount} using PayPal account {self.email}")

class CreditCardProcessor(PaymentProcessor):
    def __init__(self, card_number):
        self.card_number = card_number

    def pay(self, amount):
        print(f"Paying {amount} using Credit Card {self.card_number}")

# OrderService depends on abstraction, not on concrete implementation
class OrderService:
    def __init__(self, payment_processor: PaymentProcessor):
        # Dependency is injected from the outside
        self.payment_processor = payment_processor

    def checkout(self, amount):
        print("Processing order...")
        self.payment_processor.pay(amount)
        print("Order completed!\n")


# Usage
paypal_order = OrderService(PayPalProcessor("alice@example.com"))
credit_order = OrderService(CreditCardProcessor("1234-5678-9876-5432"))

paypal_order.checkout(100)
credit_order.checkout(250)

# Easy testing with a mock
class MockProcessor(PaymentProcessor):
    def pay(self, amount):
        print(f"Mock payment of {amount}")

test_order = OrderService(MockProcessor())
test_order.checkout(50)