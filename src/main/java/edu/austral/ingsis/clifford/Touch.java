package edu.austral.ingsis.clifford;

public class Touch implements Command {
    private final Directory cD;

    public Touch(Directory cd) {
        this.cD = cd;
    }

    @Override
    public String execute(String[] args) {
        if (args.length > 0) {
            String fileName = args[0];
            File newFile = new File(fileName, cD);
            return cD.addChild(newFile);
        }
        return "Invalid command";
    }
}