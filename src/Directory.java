
import java.util.*;

class Directory extends FileSystemEntity {

    private Map<String, FileSystemEntity> child;

    public Directory(String name) {
        super(name);
        this.child = new HashMap<>();
    }

    public void addChild(FileSystemEntity entity) {
        entity.setRoot(this);
        child.put(entity.getName(), entity);
    }

    public boolean removeChild(String name) {
        return child.remove(name) != null;
    }
    
    public FileSystemEntity getChild(String name) {
        return child.get(name);
    }

    public Map<String, FileSystemEntity> getChild() {
        return child;
    }

    @Override
    String getDetails(String indent) {
        return String.format("%s[Directory] Name: %s, Created: %s",
                indent, name, creationDate);
    }

}


