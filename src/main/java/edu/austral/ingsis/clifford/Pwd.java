package edu.austral.ingsis.clifford;

public class Pwd implements Command {
    private final Directory cD;

    public Pwd(Directory cd) {
        this.cD = cd;
    }

    @Override
    public String execute(String[] args) {
        return cD.getPath();
    }
}