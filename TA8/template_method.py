from abc import ABC, abstractmethod


class DataMiner(ABC):
    """Abstract base class that defines the template method pattern"""

    def mine_data(self, path: str) -> None:
        """Template method that defines the skeleton algorithm"""
        # Step 1: Open the file
        file = self.open_file(path)

        # Step 2: Extract the data
        raw_data = self.extract_data(file)

        # Step 3: Parse the data
        parsed_data = self.parse_data(raw_data)

        # Step 4: Analyze the data
        self.analyze_data(parsed_data)

        # Step 5: Close the file
        self.close_file(file)

        # Step 6: Send report
        self.send_report()

    @abstractmethod
    def open_file(self, path: str):
        """Open the data file"""
        pass

    @abstractmethod
    def extract_data(self, file):
        """Extract raw data from the file"""
        pass

    @abstractmethod
    def parse_data(self, raw_data):
        """Parse the raw data into structured format"""
        pass

    def analyze_data(self, parsed_data):
        """Analyze the parsed data - this is a concrete method"""
        print("Analyzing data...")
        # Default analysis implementation

    @abstractmethod
    def close_file(self, file):
        """Close the data file"""
        pass

    def send_report(self):
        """Send analysis report - this is a concrete method"""
        print("Sending report...")
        # Default reporting implementation


class PDFDataMiner(DataMiner):
    """Concrete class for mining PDF files"""

    def open_file(self, path: str):
        print(f"Opening PDF file at {path}")
        return f"PDF_FILE_{path}"

    def extract_data(self, file):
        print(f"Extracting data from PDF {file}")
        return "PDF_RAW_DATA"

    def parse_data(self, raw_data):
        print(f"Parsing PDF data: {raw_data}")
        return ["parsed_pdf_data"]

    def close_file(self, file):
        print(f"Closing PDF file: {file}")


class CSVDataMiner(DataMiner):
    """Concrete class for mining CSV files"""

    def open_file(self, path: str):
        print(f"Opening CSV file at {path}")
        return f"CSV_FILE_{path}"

    def extract_data(self, file):
        print(f"Extracting data from CSV {file}")
        return "CSV_RAW_DATA"

    def parse_data(self, raw_data):
        print(f"Parsing CSV data: {raw_data}")
        return ["parsed_csv_data"]

    def close_file(self, file):
        print(f"Closing CSV file: {file}")

    # Override the analyze_data method for CSV-specific analysis
    def analyze_data(self, parsed_data):
        print("Performing specialized CSV analysis...")


# Example usage
def main():
    # Process a PDF file
    pdf_miner = PDFDataMiner()
    pdf_miner.mine_data("document.pdf")

    print("\n" + "=" * 50 + "\n")

    # Process a CSV file
    csv_miner = CSVDataMiner()
    csv_miner.mine_data("data.csv")


if __name__ == "__main__":
    main()