import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class FileSystemTest {
    private FileSystem fs;
    
    @Before
    public void setUp() {
        fs = new FileSystem();
        System.out.println("\n----- Starting New Test -----");
    }
    
    @Test
    public void testBasicFileOperations() {
        System.out.println("Running: Basic File Operations Test");
        
        // Test adding directory
        fs.addDir("root", "documents");
        System.out.println("Created directory 'documents'");
        
        // Test adding file
        fs.addFile("documents", "test.txt", 100);
        System.out.println("Created file 'test.txt'");
        
        // Test file size
        long size = fs.getSizeOfFile("test.txt");
        assertEquals(100, size);
        System.out.println("Verified file size is 100");
        
        System.out.println("Test completed!");
    }
    
    @Test
    public void testFileValidation() {
        System.out.println("Running: File Validation Test");
        
        fs.addDir("root", "documents");
        System.out.println("Created directory 'documents'");
        
        // Test invalid file size
        try {
            fs.addFile("documents", "test.txt", -1);
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            System.out.println("Caught invalid file size exception");
        }
        
        System.out.println("Test completed!");
    }
    
    @Test
    public void testBiggestFile() {
        System.out.println("Running: Biggest File Test");
        
        fs.addDir("root", "documents");
        System.out.println("Created directory 'documents'");
        
        fs.addFile("documents", "small.txt", 100);
        System.out.println("Created file 'small.txt' with size 100");
        
        fs.addFile("documents", "big.txt", 200);
        System.out.println("Created file 'big.txt' with size 200");
        
        String biggest = fs.getMaxSizeFile();
        assertEquals("big.txt", biggest);
        System.out.println("Verified 'big.txt' is the biggest file");
        
        System.out.println("Test completed!");
    }
    
    @Test
    public void testDeleteOperation() {
        System.out.println("Running: Delete Operations Test");
        
        fs.addDir("root", "documents");
        System.out.println("Created directory 'documents'");
        
        fs.addFile("documents", "test.txt", 100);
        System.out.println("Created file 'test.txt'");
        
        fs.delete("test.txt");
        System.out.println("Deleted 'test.txt'");
        
        try {
            fs.getSizeOfFile("test.txt");
            fail("Should have thrown IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            System.out.println("Verified file was deleted");
        }
        
        System.out.println("Test completed!");
    }

    @Test
    public void testDeepDirectoryStructure() {
        System.out.println("Running: Deep Directory Structure Test");
        
        fs.addDir("root", "level1");
        fs.addDir("level1", "level2");
        fs.addDir("level2", "level3");
        System.out.println("Created deep directory structure");
        
        fs.addFile("level3", "deep.txt", 100);
        System.out.println("Added file in deep directory");
        
        assertEquals(100, fs.getSizeOfFile("deep.txt"));
        System.out.println("Verified file in deep directory");
        
        System.out.println("Test completed!");
    }

    @Test
    public void testNameLengthValidation() {
        System.out.println("Running: Name Length Validation Test");
        
        String longName = "ThisFileNameIsWayTooLongAndShouldCauseAnError.txt";
        
        try {
            fs.addFile("root", longName, 100);
            fail("Should have thrown IllegalArgumentException for long filename");
        } catch (IllegalArgumentException e) {
            System.out.println("Caught long filename exception");
        }
        
        System.out.println("Test completed!");
    }
}