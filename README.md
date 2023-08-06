# LZ-78 (Lempel-Ziv 78) Data Compression Algorithm

This repository contains Java code implementing the LZ-78 (Lempel-Ziv 78) data compression algorithm. The LZ-78 algorithm is a lossless data compression method that replaces repeated occurrences of data patterns with references to previously encountered patterns.

## Algorithm Description

The LZ-78 algorithm uses a dictionary-based approach to compress data. It maintains a dictionary of previously encountered patterns and represents new patterns by referencing entries in the dictionary.

The provided code includes two main functionalities: compression and decompression.

### Compression

The `LZ78compress` method in the `lZ78` class performs the compression process. It takes a string `str` as input and generates a vector of tags representing the compressed data. The algorithm scans the input string character by character, building patterns and adding them to the dictionary. Whenever a new pattern is encountered, it is added to the dictionary, and a tag is created consisting of a pointer to the pattern's index in the dictionary and the next character in the input string. These tags are stored in the `tags` vector.

The compressed data size is printed to the console, including both the original size (in bits) and the compressed size (in bits).

### Decompression

The `LZ78decompress` method in the `lZ78` class performs the decompression process. It takes a vector of tags as input and returns the decompressed string. The algorithm iterates through the tags, interpreting each tag to reconstruct the original data. It uses the dictionary to retrieve patterns based on the provided pointers and appends the characters to the result string.

The decompressed string is printed to the console.

### Usage

The code includes a `main` method that provides a simple command-line user interface for compression and decompression. Upon running the program, the user is prompted to select either compression or decompression.

For compression:
1. The input file path is set to `"Compression\\input.txt"`.
2. The compressed output is written to `"Compression\\compressed.txt"`.

For decompression:
1. The decompressed output is written to `"DeCompression\\decompressed.txt"`.
2. The user is prompted to enter the number of tags to process.
3. The user is then prompted to enter the tags in the format `<index, character>`.

### Example

An example input for decompression:
```
Enter Number of tags: 10
Enter the tags in this form: <1,A> <0,B> <2,A> <1,B> <3,A> <2,B> <4,A> <4,B> <0,C>
```

The output will be:
```
ABABAABABABAC
```

## Repository Structure

The repository structure is organized as follows:
```
LZ-78/
├── Compression/
│ └── input.txt
├── DeCompression/
│ └── decompressed.txt
└── LZ78.java
```

- The `Compression` directory contains the input file for compression (`input.txt`) and will store the compressed output (`compressed.txt`).
- The `DeCompression` directory contains the decompressed output file (`decompressed.txt`).
- The `LZ78.java` file contains the Java code implementing the LZ-78 data compression algorithm.

## Requirements

To run the code, you need to have Java installed on your machine.

## How to Run

1. Clone the repository to your local machine or download the `LZ-78` directory as a ZIP file.
2. Open a terminal or command prompt and navigate to the root directory of the repository.
3. Compile the Java code by running the following command:
```
javac LZ78.java
```
4. Run the program using the following command:
```
java LZ78
```

5. Follow the on-screen instructions to choose compression or decompression and provide the necessary input.

## Contribution

Contributions to the LZ-78 data compression algorithm implementation are welcome. If you find any issues or have suggestions for improvements, please open an issue or submit a pull request on the GitHub repository.

## License
The code in this repository is licensed under the MIT License.
