# FileSystem Class Complexity Summary

This document provides a summary of the time and space complexity for each method in the `FileSystem` class.

| Function              | Description                                   | Time Complexity                    | Space Complexity                 |
|-----------------------|-----------------------------------------------|------------------------------------|----------------------------------|
| `addFile`             | Adds a new file under specified parent dir    | \(O(1)\)                           | \(O(1)\)                         |
| `addDir`              | Adds a new directory under specified parent   | \(O(1)\)                           | \(O(1)\)                         |
| `getSizeOfFile`       | Returns the size of the specified file        | \(O(1)\)                           | \(O(1)\)                         |
| `getMaxSizeFile`      | Returns name of the file with the maximum size| \(O(1)\)                           | \(O(1)\)                         |
| `showFileSystem`      | Displays structure of the file system         | \(O(n)\), \(n =\) total entities   | \(O(h)\), \(h =\) tree height    |
| `delete` (file)       | Deletes a file                                | \(O(1)\) or \(O(m)\)*              | \(O(1)\)                         |
| `delete` (directory)  | Deletes a directory and its descendants       | \(O(n)\)** or \(O(m)\)*            | \(O(n)\) for deleting directory  |
| `getNewBiggestFile`   | Finds and updates the largest file in system  | \(O(m)\), \(m =\) total files      | \(O(1)\)                         |

### Notes

- **For `delete`**:
    - If the file or directory being deleted is the largest file, the time complexity includes an \(O(m)\) operation for re-evaluating the largest file.
    - For directory deletion, `O(n)` denotes removing all descendants recursively.
  
