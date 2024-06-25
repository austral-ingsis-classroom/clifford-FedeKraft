package edu.austral.ingsis.clifford;

public class Cd implements Command {
    private static Directory cD;
    private final Directory rD;

    public Cd(Directory cd, Directory rd) {
        Cd.cD = cd;
        this.rD = rd;
    }

    @Override
    public String execute(String[] args) {
        String dirName = args[0];
        if (dirName.equals("/")) {
            cD = rD;
            return "moved to directory '/'";
        }

        if (dirName.equals("..")) {
            if (cD.getP() != null) {
                cD = cD.getP();
                return "moved to directory '" + cD.getPath() + "'";
            } else {
                return "moved to directory '/'";
            }
        }

        if (!dirName.startsWith("/")) {
            String[] path = dirName.split("/");
            Directory child = cD;
            for (String part : path) {
                Directory nextChild = child.findDirectoryByName(part);
                if (nextChild == null) {
                    return "'" + part + "' directory does not exist";
                }
                child = nextChild;
            }
            cD = child;
            dirName = child.getName();
            return "moved to directory '" + dirName + "'";
        } else {
            Directory child = cD.findDirectoryByName(dirName);
            if (child != null) {
                cD = child;
                return "moved to directory '" + dirName + "'";
            } else {
                return "'" + dirName + "' directory does not exist";
            }
        }
    }

    public static Directory getcD() {
        return cD;
    }
}