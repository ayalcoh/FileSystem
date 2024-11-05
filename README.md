## Time and Space Complexity Analysis

| Operation | Time Complexity | Space Complexity | Description |
|-----------|----------------|------------------|-------------|
| addFile | O(1) | O(1) | Uses HashMap for instant parent lookup and insertion |
| addDir | O(1) | O(1) | Similar to addFile, constant time directory creation |
| getFileSize | O(1) | O(1) | Direct HashMap lookup for file information |
| getBiggestFile | O(1) | O(1) | Maintains tracked reference to largest file |
| showFileSystem | O(n) | O(h) | Traverses all n entities, recursion depth h |
| delete (file) | O(1) or O(n)* | O(1) | O(1) for regular files, O(n) only if deleting biggest file |
| delete (directory) | O(n) | O(n) | Must process all n descendants in directory |

where:
- n = total number of entities in the system
- h = height of the directory tree
- *O(n) only when deleting the biggest file as we need to scan all files to find the new biggest file.An alternative approach could make delete of biggest file aswell O(1) but would make getBiggestFile O(n) - current design assumes getBiggestFile is called more frequently.

- Including JUnit for testing functionalities.


  
