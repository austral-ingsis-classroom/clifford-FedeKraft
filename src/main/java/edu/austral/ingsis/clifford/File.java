package edu.austral.ingsis.clifford;

public class File implements FileSystem {
    private final String name;
    private final Directory parent;

    public File(String name, Directory parent) {
        validateName(name);
        this.name = name.trim();
        this.parent = parent;
    }

    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("File name cannot be null or empty");
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPath() {
        return (parent == null) ? "/" + name : parent.getPath() + "/" + name;
    }
}
