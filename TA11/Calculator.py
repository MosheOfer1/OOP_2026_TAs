import tkinter as tk



# Function to handle button click events
# Depending on the button clicked, it either updates the display, clears it, or evaluates an expression
def on_button_click(button_text):
    current_text = display.get()  # Fetch the current text on the display
    if button_text == "=":  # If the user presses "=", evaluate the expression
        try:
            result = str(eval(current_text))  # Calculates the expression using eval
            display.set(result)  # Updates the display with the result
        except Exception as e:  # Catch any errors during evaluation
            display.set("Error")  # Display "Error" if the expression is invalid
    elif button_text == "C":  # If the user presses "C", clear the display
        display.set("")  # Reset the display to an empty string
    else:  # For all other button clicks, append the button's text to the display
        display.set(current_text + button_text)

root = tk.Tk()  # Create the main Tkinter window
root.title("Calculator")  # Set the title of the window
root.geometry("300x450")  # Set the size of the window
root.config(bg="#2d2d2d")  # Set the background color of the window

display = tk.StringVar()  # A StringVar to manage the content of the display (entry field)

display_entry = tk.Entry(root, textvariable=display, font=("Arial", 24), bd=8,
                         relief="ridge", justify="right", bg="#f0f0f0", width=100)  # The display field for the calculator
display_entry.grid(row=0, column=0,columnspan=4)  # Span the display across the top four columns

# Define the calculator buttons and their positions (text, row, column)
buttons = [
    ('7', 1, 0), ('8', 1, 1), ('9', 1, 2), ('/', 1, 3),
    ('4', 2, 0), ('5', 2, 1), ('6', 2, 2), ('*', 2, 3),
    ('1', 3, 0), ('2', 3, 1), ('3', 3, 2), ('-', 3, 3),
    ('0', 4, 0), ('.', 4, 1), ('=', 4, 2), ('+', 4, 3),
    ('C', 5, 0)  # Add a clear button at the bottom
]

# Iterate over the buttons and create them dynamically
for (text, row, col) in buttons:
    button = tk.Button(root, text=text, font=("Arial", 20), height=2, width=5,  # Create a button with the specified text and appearance
                       command=lambda t=text: on_button_click(t),  # Assign the on_button_click function to the button
                       bg="#37a8c8", fg="white")  # Set the button colors
    button.grid(row=row, column=col, padx=3, pady=3)  # Place the button in the correct grid position

# Configure row and column lengths to make the calculator responsive
for i in range(0,6):  # Iterate over all rows and columns in the grid
    if i != 0:
      root.grid_rowconfigure(i, weight=1)  # Assign a weight to each row to make them resizable
    root.grid_columnconfigure(i, weight=1)  # Assign a weight to each column to make them resizable

# Start the main event loop of the Tkinter application
root.mainloop()
