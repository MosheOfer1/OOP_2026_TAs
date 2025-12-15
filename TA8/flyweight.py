from typing import Dict, List, Optional
from dataclasses import dataclass
import sys

"""
Flyweight Pattern Implementation for Rich Text Editor

Purpose:
This code demonstrates the Flyweight pattern to optimize memory usage in a rich text editor 
by sharing character format objects instead of creating new ones for each character.

Benefits:
1. Memory Efficiency: Instead of storing format data for each character, identical formats 
  are shared across multiple characters, significantly reducing memory usage.

2. Performance: Fewer object creations and less memory allocation leads to better 
  performance, especially for large documents.


Implementation Details:
- CharacterFormat: Flyweight object storing intrinsic formatting state (font, size, etc.)
- FormatFactory: Manages format object pool and ensures reuse
- FormattedCharacter: Context class combining character (extrinsic) with format (intrinsic)
- RichTextDocument: Main document class demonstrating practical usage

Memory savings are demonstrated in the output statistics showing:
- Total characters vs unique format objects created
- Estimated memory saved by reusing format objects
"""


@dataclass
class CharacterFormat:
    """Flyweight object containing intrinsic state"""
    font: str
    size: int
    bold: bool
    italic: bool
    color: str
    background: str

    def __str__(self) -> str:
        style = []
        if self.bold:
            style.append("bold")
        if self.italic:
            style.append("italic")
        style_str = ", ".join(style) if style else "normal"
        return f"Format[font={self.font}, size={self.size}, style={style_str}, color={self.color}, bg={self.background}]"


class FormatFactory:
    """Flyweight factory that manages format objects"""
    _formats: Dict[str, CharacterFormat] = {}

    @classmethod
    def get_format(
            cls,
            font: str,
            size: int,
            bold: bool = False,
            italic: bool = False,
            color: str = "black",
            background: str = "white"
    ) -> CharacterFormat:
        key = f"{font}-{size}-{bold}-{italic}-{color}-{background}"

        if key not in cls._formats:
            cls._formats[key] = CharacterFormat(font, size, bold, italic, color, background)

        return cls._formats[key]

    @classmethod
    def format_count(cls) -> int:
        return len(cls._formats)

    @classmethod
    def memory_usage(cls) -> int:
        """Estimate memory usage of format objects in bytes"""
        # Rough estimation of memory used by each format object
        return len(cls._formats) * sys.getsizeof(CharacterFormat("", 0, False, False, "", ""))


class FormattedCharacter:
    """Context class containing extrinsic state"""

    def __init__(self, char: str, format_: CharacterFormat):
        self.char = char
        self.format_ = format_

    def render(self) -> str:
        return f"'{self.char}' with {self.format_}"


@dataclass
class Paragraph:
    """Container for formatted text with paragraph-level formatting"""
    alignment: str = "left"  # left, center, right, justify
    line_spacing: float = 1.0
    content: List[FormattedCharacter] = None

    def __post_init__(self):
        self.content = self.content or []

    def add_text(self, text: str, format_: CharacterFormat):
        for char in text:
            self.content.append(FormattedCharacter(char, format_))


class RichTextDocument:
    """Rich text document containing multiple paragraphs"""

    def __init__(self):
        self.paragraphs: List[Paragraph] = []
        self.current_paragraph: Optional[Paragraph] = None

    def new_paragraph(self, alignment: str = "left", line_spacing: float = 1.0):
        """Start a new paragraph with specified formatting"""
        self.current_paragraph = Paragraph(alignment=alignment, line_spacing=line_spacing)
        self.paragraphs.append(self.current_paragraph)

    def add_text(self, text: str, font: str, size: int, bold: bool = False,
                 italic: bool = False, color: str = "black", background: str = "white"):
        """Add text to the current paragraph with specified formatting"""
        if not self.current_paragraph:
            self.new_paragraph()

        format_ = FormatFactory.get_format(font, size, bold, italic, color, background)
        self.current_paragraph.add_text(text, format_)

    def display(self):
        """Display the formatted document"""
        for i, para in enumerate(self.paragraphs, 1):
            print(f"\nParagraph {i} ({para.alignment}, {para.line_spacing}x spacing):")
            print("-" * 50)
            for char in para.content:
                print(char.render())


def main():
    # Create a sample document with multiple paragraphs and formats
    doc = RichTextDocument()

    # First paragraph - Title
    doc.new_paragraph(alignment="center", line_spacing=1.5)
    doc.add_text("My Document Title", "Arial", 16, bold=True, color="blue")

    # Second paragraph - Normal text
    doc.new_paragraph(alignment="left", line_spacing=1.0)
    doc.add_text("This is the first paragraph with ", "Times New Roman", 12)
    doc.add_text("some important ", "Times New Roman", 12, bold=True)
    doc.add_text("and ", "Times New Roman", 12)
    doc.add_text("emphasized ", "Times New Roman", 12, italic=True)
    doc.add_text("text.", "Times New Roman", 12)

    # Third paragraph - Quote
    doc.new_paragraph(alignment="justify", line_spacing=1.2)
    doc.add_text("Here's a quoted text: ", "Georgia", 12)
    doc.add_text("Life is like a box of chocolates", "Georgia", 12, italic=True, color="darkgray")

    # Display the document
    print("\nDisplaying formatted document:")
    doc.display()

    # Show memory savings
    total_chars = sum(len(p.content) for p in doc.paragraphs)
    total_formats = FormatFactory.format_count()
    format_memory = FormatFactory.memory_usage()

    print(f"\nMemory usage statistics: ")
    print(f"Total characters: {total_chars}")
    print(f"Unique format objects: {total_formats}")
    print(f"Format objects memory usage: ~{format_memory} bytes")
    print(f"Memory saved: {total_chars - total_formats} format objects "
          f"(~{(total_chars - total_formats) * format_memory / total_formats} bytes)")


if __name__ == "__main__":
    main()
