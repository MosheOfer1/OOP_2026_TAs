import tkinter as tk
from tkinter import messagebox


# Callback functions
def on_button_click():
    user_name = name_entry.get()
    messagebox.showinfo("Greetings", f"Hello, {user_name}!")


def on_radio_button_select():
    selection = f"You selected: {radio_var.get()}"
    radio_label.config(text=selection)


def on_checkbox_toggle():
    selected_options = []
    if music_var.get():
        selected_options.append("Music")
    if video_var.get():
        selected_options.append("Video")
    check_label.config(text=f"Selected: {', '.join(selected_options)}")


def on_scale_change(value):
    scale_label.config(text=f"Scale value: {value}")


# Main application window
root = tk.Tk()
root.title("Comprehensive Tkinter GUI")
root.geometry("600x600")

# Frame for name entry and button
frame1 = tk.Frame(root, padx=10, pady=10)
frame1.pack(fill=tk.BOTH, expand=True)

name_label = tk.Label(frame1, text="Enter your name:")
name_label.grid(row=0, column=0, sticky=tk.W)

name_entry = tk.Entry(frame1, width=30)
name_entry.grid(row=0, column=1)

greet_button = tk.Button(frame1, text="Greet Me", command=on_button_click)
greet_button.grid(row=0, column=2, padx=10)

# Canvas for drawing
frame2 = tk.Frame(root, padx=10, pady=10)
frame2.pack(fill=tk.BOTH, expand=True)

canvas = tk.Canvas(frame2, bg="lightblue", height=200, width=400)
canvas.create_oval(50, 50, 150, 150, fill="red")
canvas.create_rectangle(200, 50, 350, 150, fill="green")
canvas.pack()

# Radio buttons
frame3 = tk.Frame(root, padx=10, pady=10)
frame3.pack(fill=tk.BOTH, expand=True)

radio_var = tk.StringVar(value="None")
tk.Radiobutton(frame3, text="Option 1", variable=radio_var, value="Option 1", command=on_radio_button_select).pack(anchor=tk.W)
tk.Radiobutton(frame3, text="Option 2", variable=radio_var, value="Option 2", command=on_radio_button_select).pack(anchor=tk.W)
tk.Radiobutton(frame3, text="Option 3", variable=radio_var, value="Option 3", command=on_radio_button_select).pack(anchor=tk.W)

radio_label = tk.Label(frame3, text="You selected: None")
radio_label.pack()

# Checkboxes
frame4 = tk.Frame(root, padx=10, pady=10)
frame4.pack(fill=tk.BOTH, expand=True)

music_var = tk.BooleanVar()
video_var = tk.BooleanVar()

music_check = tk.Checkbutton(frame4, text="Music", variable=music_var, command=on_checkbox_toggle)
music_check.pack(side=tk.LEFT)

video_check = tk.Checkbutton(frame4, text="Video", variable=video_var, command=on_checkbox_toggle)
video_check.pack(side=tk.LEFT)

check_label = tk.Label(frame4, text="Selected: None")
check_label.pack(side=tk.LEFT, padx=10)

# Scale widget
frame5 = tk.Frame(root, padx=10, pady=10)
frame5.pack(fill=tk.BOTH, expand=True)

scale_label = tk.Label(frame5, text="Scale value: 0")
scale_label.pack()

scale = tk.Scale(frame5, from_=0, to=100, orient=tk.VERTICAL
                 , command=on_scale_change)
scale.pack()

# Listbox
frame6 = tk.Frame(root, padx=10, pady=10)
frame6.pack(fill=tk.BOTH, expand=True)

listbox = tk.Listbox(frame6, height=5)
listbox.insert(1, "Python")
listbox.insert(2, "Java")
listbox.insert(3, "C++")
listbox.insert(4, "JavaScript")
listbox.insert(5, "Ruby")
listbox.pack()

# Exit button
exit_button = tk.Button(root, text="Exit", command=root.quit)
exit_button.pack(pady=10)

# Start the main loop
root.mainloop()
