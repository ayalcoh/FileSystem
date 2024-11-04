
class File extends FileSystemEntity {
    private long size;
    
    public File(String name, long size) {
        super(name);
        sizeException(size);
        this.size = size;
    }
    
    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        sizeException(size);
        this.size = size; 
}

    private void sizeException(long size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Size got to be positive.");
        }
    }

    @Override
    public String getDetails(String indent) {
        return String.format("%s[File]      Name: %s, Size: %d bytes, Created: %s",
                indent, name, size, creationDate);
    }
}
