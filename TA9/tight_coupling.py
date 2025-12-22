# BAD EXAMPLE: Tight coupling
# The OrderService creates its own dependency internally
class PayPalProcessor:
    def __init__(self, email):
        self.email = email

    def pay(self, amount):
        print(f"Paying {amount} using PayPal account {self.email}")


class OrderService:
    def __init__(self):
        # OrderService is tightly coupled to PayPalProcessor
        self.payment_processor = PayPalProcessor("alice@example.com")

    def checkout(self, amount):
        print("Processing order...")
        self.payment_processor.pay(amount)
        print("Order completed!\n")


# Usage
order = OrderService()
order.checkout(100)