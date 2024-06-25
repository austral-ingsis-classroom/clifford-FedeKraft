package edu.austral.ingsis.clifford;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Directory implements FileSystem {
    private final String name;
    private final Directory parent;
    private final List<FileSystem> children;

    public Directory(String name, Directory parent) {
        this.name = trimName(name);
        this.parent = parent;
        this.children = new ArrayList<>();
    }

    public Directory(String name) {
        this(name, null);
    }

    private String trimName(String name) {
        return (name != null) ? name.trim() : "";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPath() {
        return (parent == null) ? "/" + name : parent.getPath() + "/" + name;
    }

    public Directory getParent() {
        return parent;
    }

    public String addChild(FileSystem fileSystem) {
        if (findChildByName(fileSystem.getName()) != null) {
            return "Error: Directory or file already exists";
        }

        children.add(fileSystem);
        return "'" + fileSystem.getName() + "' " + getFileType(fileSystem) + " created";
    }

    private String getFileType(FileSystem fileSystem) {
        return (fileSystem instanceof Directory) ? "directory" : "file";
    }

    public void removeChild(FileSystem fileSystem) {
        children.remove(fileSystem);
    }

    public Directory getChildByName(String name) {
        FileSystem child = findChildByName(name);
        return (child instanceof Directory) ? (Directory) child : null;
    }

    public FileSystem findChildByName(String name) {
        for (FileSystem child : children) {
            if (child.getName().equals(name)) {
                return child;
            }
        }
        return null;
    }

    public List<String> listItems(String order) {
        List<String> items = new ArrayList<>();
        for (FileSystem child : children) {
            items.add(child.getName());
        }
        sortItems(items, order);
        return items;
    }

    private void sortItems(List<String> items, String order) {
        if (order != null) {
            switch (order) {
                case "asc":
                    Collections.sort(items);
                    break;
                case "desc":
                    Collections.sort(items, Collections.reverseOrder());
                    break;
                default:
                    break;
            }
        }
    }

    public Directory findDirectoryByName(String name) {
        for (FileSystem child : children) {
            if (child instanceof Directory && child.getName().equals(name)) {
                return (Directory) child;
            }
        }
        return null;
    }
}
