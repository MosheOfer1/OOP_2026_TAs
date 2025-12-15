def my_decorator(func):
    def wrapper():
        print("Something is happening before the function is called.")
        func()
        print("Something is happening after the function is called.")
    return wrapper


@my_decorator
def say_hello():
    print("Hello!")
# Manually applies the decorator; equivalent to writing @my_decorator above the function
# say_hello = my_decorator(say_hello)


if __name__ == "__main__":
    say_hello()
