package edu.austral.ingsis.clifford;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Directory implements FileSystem {
    private final String name;
    private final Directory p;
    private final List<FileSystem> c;

    public Directory(String name, Directory p) {
        this.name = trimName(name);
        this.p = p;
        this.c = new ArrayList<>();
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
        if (p == null) {
            return "/";
        } else {
            String parentPath = p.getPath();
            if (parentPath.equals("/")) {
                return parentPath + name;
            } else {
                return parentPath + name;
            }
        }
    }
    public Directory getP() {
        return p;
    }

    public void removeChild(FileSystem fileSystem) {
        c.remove(fileSystem);
    }

    public Directory getChildByName(String name) {
        FileSystem child = findChildByName(name);
        return (child instanceof Directory) ? (Directory) child : null;
    }
    public String addChild(FileSystem fileSystem) {
        if (findChildByName(fileSystem.getName()) != null) {
            return "Error: Directory or file already exists";
        }

        c.add(fileSystem);
        return "'" + fileSystem.getName() + "' " + getFileType(fileSystem) + " created";
    }


    public FileSystem findChildByName(String name) {
        for (FileSystem child : c) {
            if (child.getName().equals(name)) {
                return child;
            }
        }
        return null;
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

    private String getFileType(FileSystem fileSystem) {
        return (fileSystem instanceof Directory) ? "directory" : "file";
    }

    public List<String> listItems(String order) {
        List<String> items = new ArrayList<>();
        for (FileSystem child : c) {
            items.add(child.getName());
        }
        sortItems(items, order);
        return items;
    }

    public Directory findDirectoryByName(String name) {
        for (FileSystem child : c) {
            if (child instanceof Directory && child.getName().equals(name)) {
                return (Directory) child;
            }
        }
        return null;
    }
}
