import tkinter as tk
from tkinter import ttk, font, colorchooser
from flyweight import RichTextDocument, FormatFactory


class RichTextEditorGUI:
    def __init__(self, root):
        self.root = root
        self.root.title("Rich Text Editor")
        self.doc = RichTextDocument()
        self.setup_ui()

    def setup_ui(self):
        # Toolbar
        toolbar = ttk.Frame(self.root)
        toolbar.pack(fill=tk.X, padx=5, pady=5)

        # Font selection
        self.font_var = tk.StringVar(value="Arial")
        fonts = list(font.families())
        ttk.Combobox(toolbar, textvariable=self.font_var, values=fonts, width=15).pack(side=tk.LEFT, padx=2)

        # Size selection
        self.size_var = tk.StringVar(value="12")
        sizes = [str(s) for s in range(8, 37, 2)]
        ttk.Combobox(toolbar, textvariable=self.size_var, values=sizes, width=5).pack(side=tk.LEFT, padx=2)

        # Style buttons
        self.bold_var = tk.BooleanVar()
        ttk.Checkbutton(toolbar, text="B", variable=self.bold_var).pack(side=tk.LEFT, padx=2)

        self.italic_var = tk.BooleanVar()
        ttk.Checkbutton(toolbar, text="I", variable=self.italic_var).pack(side=tk.LEFT, padx=2)

        # Color button
        self.text_color = "black"
        ttk.Button(toolbar, text="Color", command=self.choose_color).pack(side=tk.LEFT, padx=2)

        # Alignment buttons
        alignments = ["left", "center", "right", "justify"]
        self.alignment_var = tk.StringVar(value="left")
        for align in alignments:
            ttk.Radiobutton(toolbar, text=align.capitalize(), value=align,
                            variable=self.alignment_var).pack(side=tk.LEFT, padx=2)

        # New paragraph button
        ttk.Button(toolbar, text="New Paragraph",
                   command=self.new_paragraph).pack(side=tk.LEFT, padx=2)

        # Text entry
        self.text_entry = ttk.Entry(self.root)
        self.text_entry.pack(fill=tk.X, padx=5, pady=5)
        self.text_entry.bind('<Return>', self.add_text)

        # Preview area
        preview_frame = ttk.LabelFrame(self.root, text="Preview")
        preview_frame.pack(fill=tk.BOTH, expand=True, padx=5, pady=5)

        self.preview = tk.Text(preview_frame, wrap=tk.WORD, height=20)
        self.preview.pack(fill=tk.BOTH, expand=True, padx=5, pady=5)
        self.preview.config(state=tk.DISABLED)

        # Stats
        self.stats_label = ttk.Label(self.root, text="")
        self.stats_label.pack(pady=5)

    def choose_color(self):
        color = colorchooser.askcolor(title="Choose text color")[1]
        if color:
            self.text_color = color

    def new_paragraph(self):
        self.doc.new_paragraph(alignment=self.alignment_var.get())
        self.update_preview()

    def add_text(self, event=None):
        text = self.text_entry.get()
        if text:
            self.doc.add_text(
                text,
                self.font_var.get(),
                int(self.size_var.get()),
                self.bold_var.get(),
                self.italic_var.get(),
                self.text_color
            )
            self.text_entry.delete(0, tk.END)
            self.update_preview()
            self.update_stats()

    def update_preview(self):
        self.preview.config(state=tk.NORMAL)
        self.preview.delete(1.0, tk.END)

        for i, para in enumerate(self.doc.paragraphs):
            if i > 0:
                self.preview.insert(tk.END, "\n\n")

            for char in para.content:
                format_tags = []
                if char.format_.bold:
                    format_tags.append("bold")
                if char.format_.italic:
                    format_tags.append("italic")

                tag_name = f"format_{len(self.preview.tag_names())}"
                self.preview.tag_configure(tag_name,
                                           font=(char.format_.font, char.format_.size),
                                           foreground=char.format_.color)

                self.preview.insert(tk.END, char.char, tag_name)

        self.preview.config(state=tk.DISABLED)

    def update_stats(self):
        total_chars = sum(len(p.content) for p in self.doc.paragraphs)
        total_formats = FormatFactory.format_count()
        format_memory = FormatFactory.memory_usage()

        stats = f"Characters: {total_chars} | Unique formats: {total_formats} | "
        stats += f"Memory saved: ~{(total_chars - total_formats) * format_memory / total_formats:.0f} bytes"
        self.stats_label.config(text=stats)


def main():
    root = tk.Tk()
    app = RichTextEditorGUI(root)
    root.mainloop()


if __name__ == "__main__":
    main()