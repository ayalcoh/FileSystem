
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;



public class FileSystem {
    private Directory root;
    private Map<String, FileSystemEntity> dataMap;
    private File biggestFileSize;

    
    public FileSystem() {
        this.root = new Directory("root");
        this.dataMap = new HashMap<>();
        dataMap.put("root", root);
        this.biggestFileSize = null;
    }
    
    /*
    -Adds a new file under the specified parent directory
    -Time Complexity: O(1)                                                               
    -Space Complexity: O(1)
    */
    public void addFile(String dirParentName, String fileName, long fileSize) {
        if (!dataMap.containsKey(dirParentName)) {
            throw new IllegalArgumentException("Parent directory not found: " + dirParentName);
        }

        if (dataMap.containsKey(fileName)) {
            throw new IllegalStateException("A file with this name already exists");
        }

        FileSystemEntity parent = dataMap.get(dirParentName);
        if (!(parent instanceof Directory)) {                                        
            throw new IllegalArgumentException("Parent must be a directory");
        }

        File fileToAdd = new File(fileName, fileSize);
        ((Directory) parent).addChild(fileToAdd);
        dataMap.put(fileName, fileToAdd);
        System.out.println("Added file: " + fileName);

        if (biggestFileSize == null || fileToAdd.getSize() > biggestFileSize.getSize()) {
            biggestFileSize = fileToAdd;
        }
    }

    /*
    -Adds a new directory under the specified parent directory
    -Time Complexity: O(1)
    -Space Complexity: O(1)
    */

    public void addDir(String dirParentName, String dirName) {
        if (!dataMap.containsKey(dirParentName)) {
            throw new IllegalArgumentException("Parent directory not found: " + dirParentName);
        }
        
        if (dataMap.containsKey(dirName)) {
            throw new IllegalStateException("A directory with this name already exists");
        }

        FileSystemEntity parent = dataMap.get(dirParentName);
        if (!(parent instanceof Directory)) {
            throw new IllegalArgumentException("Parent must be a directory");
        }

        Directory dirToAdd = new Directory(dirName);
        ((Directory) parent).addChild(dirToAdd);
        dataMap.put(dirName, dirToAdd);
        System.out.println("Added directory: " + dirName);
    }
    
    /*
    -Returns the size of the file
    -Time Complexity: O(1)
    -Space Complexity: O(1)
    */

    public long getSizeOfFile(String fileName) {
        FileSystemEntity data = dataMap.get(fileName);
        if (data == null || !(data instanceof File)) {
            throw new IllegalArgumentException("File not found" + fileName);  
        }
        return ((File) data).getSize();
    }

    /*
    -Returns the name of the file with the maximum size
    -Time Complexity: O(1)
    -Space Complexity: O(1)
    */

    public String getMaxSizeFile() {
        if (biggestFileSize == null) {
            throw new IllegalStateException("No files currently in the system");
        }
        return biggestFileSize.getName();
    }
    /*
     -Displays structure of the file system
     -Time Complexity: O(n), n = total number of data
     -Space Complexity: O(h), h = height of the tree
     */
    public void showFileSystem() {
        showFileSystemRecursive(root, "");
    }

    private void showFileSystemRecursive(FileSystemEntity entity, String indent) {
        System.out.println(indent + "- " + entity.getName() + 
            (entity instanceof File ? " (size: " + ((File) entity).getSize() + " bytes)" : ""));
        
        if (entity instanceof Directory) {
            Directory dir = (Directory) entity;
            for (FileSystemEntity child : dir.getChild().values()) {
                showFileSystemRecursive(child, indent + "  ");
            }
        }
    }

    /*
     -Deletes the file or directory
     -Time Complexity for file: O(1) for regular files, O(n) only if deleting biggest file
     -Time Complexity for directory: O(n)
     -Space Complexity: O(n) for directory deletion
    */

    public void delete(String name) {
        if (!dataMap.containsKey(name)) {
            throw new IllegalArgumentException("Entity not found: " + name);
        }

        FileSystemEntity entity = dataMap.get(name);
        if (entity == root) {
            throw new IllegalArgumentException("Cannot delete root directory");
        }


        Directory dirParent = (Directory) entity.getParent();
        dirParent.removeChild(name);

        // Directory case always O(n)
        if (entity instanceof Directory) {
            Queue<Directory> que = new LinkedList<>();
            que.offer((Directory) entity);

            while (!que.isEmpty()) {
                Directory dir = que.poll();
                for (FileSystemEntity child : dir.getChild().values()) {
                    dataMap.remove(child.getName());
                    if (child instanceof Directory) {
                        que.offer((Directory) child);
                    }
                    if (child == biggestFileSize) {
                        biggestFileSize = null;
                    }
                }
            }
        } else if (entity instanceof File && entity == biggestFileSize) {
            biggestFileSize = null;
        }
        //Not biggest file then O(1)
        dataMap.remove(name);
        // Biggest file case O(n) 
        if (biggestFileSize == null) {
            getNewBiggestFile();
        }
        System.out.println("Removed " + name);
        
    }

    private void getNewBiggestFile() {
        long maxSize = -1;
        for (FileSystemEntity entity : dataMap.values()) {
            if (entity instanceof File) {
                File file = (File) entity;
                if (file.getSize() > maxSize) {
                    maxSize = file.getSize();
                    biggestFileSize = file;
                }
            }
        }
    }


    public static void main(String[] args) {
        FileSystem fs = new FileSystem();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== File System Manager ===");
            System.out.println("1. Add Directory");
            System.out.println("2. Add File");
            System.out.println("3. Show File System Structure");
            System.out.println("4. Get File Size");
            System.out.println("5. Find Biggest File");
            System.out.println("6. Delete Directory or File");
            System.out.println("7. Exit");
            System.out.print("\nEnter your choice (1-7): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter parent directory name: ");
                    String parentDir = scanner.nextLine();
                    System.out.print("Enter new directory name: ");
                    String newDir = scanner.nextLine();
                    fs.addDir(parentDir, newDir);
                    break;

                case 2:
                    System.out.print("Enter directory name: ");
                    String dir = scanner.nextLine();
                    System.out.print("Enter file name: ");
                    String fileName = scanner.nextLine();
                    System.out.print("Enter file size: ");
                    long fileSize = scanner.nextLong();
                    fs.addFile(dir, fileName, fileSize);
                    break;

                case 3:
                    System.out.println("\nCurrent File System Structure:");
                    fs.showFileSystem();
                    break;

                case 4:
                    System.out.print("Enter file name: ");
                    String file = scanner.nextLine();
                    long size = fs.getSizeOfFile(file);
                    if (size != -1) {
                        System.out.println("\nSize of " + file + ": " + size + " bytes");
                    } else {
                        System.out.println("\nFile not found!");
                    }
                    break;

                case 5:
                    String biggest = fs.getMaxSizeFile();
                    if (biggest != null) {
                        System.out.println("Biggest file: " + biggest);
                    } else {
                        System.out.println("No files in the system!");
                    }
                    break;

                case 6:
                    System.out.print("Enter name to delete: ");
                    String toDelete = scanner.nextLine();
                    fs.delete(toDelete);
                    break;

                case 7:
                    running = false;
                    System.out.println("Backend programming is what I'm most passionate about! thank you hope to hear from you soon ");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        scanner.close();
    }
}