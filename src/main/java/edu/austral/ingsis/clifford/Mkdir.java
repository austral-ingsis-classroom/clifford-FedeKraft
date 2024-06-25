package edu.austral.ingsis.clifford;

public class Mkdir implements Command {
    private final Directory cD;

    public Mkdir(Directory cd) {
        this.cD = cd;
    }

    @Override
    public String execute(String[] args) {
        if (args.length > 0) {
            String name = args[0];
            Directory newDir = new Directory(name, cD);
            return cD.addChild(newDir);
        }
        return "Invalid command";
    }
}