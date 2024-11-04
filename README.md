# FileSystem Class Complexity Summary

| Function              | Description                                   | Time Complexity                    | Space Complexity                 |
|-----------------------|-----------------------------------------------|------------------------------------|----------------------------------|
| `addFile`             | Adds a new File under the Directory branch    | \(O(1)\)                           | \(O(1)\)                         |
| `addDir`              | Adds a new directory under specified parent   | \(O(1)\)                           | \(O(1)\)                         |
| `getSizeOfFile`       | Returns the size of the specified file        | \(O(1)\)                           | \(O(1)\)                         |
| `getMaxSizeFile`      | Returns name of the file with the maximum size| \(O(1)\)                           | \(O(1)\)                         |
| `showFileSystem`      | Displays structure of the file system         | \(O(n)\), \(n =\) total entities   | \(O(h)\), \(h =\) tree height    |
| `delete` (file)       | Deletes a file                                | \(O(1)\) or \(O(m)\)*              | \(O(1)\)                         |
| `delete` (directory)  | Deletes a directory and its descendants       | \(O(n)\)** or \(O(m)\)*            | \(O(n)\) for deleting directory  |
| `getNewBiggestFile`   | Finds and updates the largest file in system  | \(O(m)\), \(m =\) total files      | \(O(1)\)                         |

### Notes

This time complexity assuming that getSizeOfFile function is being called more frequently then the delete function.
Deleting a file can also be `O(1)` in every call but it will cost with more running time for the getSizeOfFile function.

- **For `delete`**:
    - If the file or the directory that has the largest file is being deleted, the time complexity includes an `O(m)` operation for re-evaluating the largest file.
    - For directory deletion, `O(n)` denotes removing all descendants recursively.
  
