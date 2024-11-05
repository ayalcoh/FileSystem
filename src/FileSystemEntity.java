
import java.time.LocalDateTime;

abstract class FileSystemEntity { 
                                
    protected String name;
    protected LocalDateTime creationDate; 
    protected FileSystemEntity root; 
   

    public FileSystemEntity(String name){
        throwException(name);
        this.name = name;
        this.creationDate = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public FileSystemEntity getParent() {
        return root;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setRoot(FileSystemEntity data) {
        if (data == null) {
            throw new IllegalArgumentException("Parent must not be null.");
        }
        this.root = data;
    }

    abstract String getDetails(String indent);

    private void throwException(String name) {
        if (name == null || name.length() > 32){
            throw new IllegalArgumentException("Name must not be null and no longer than 32 characters.");
        }
    }

    
}
