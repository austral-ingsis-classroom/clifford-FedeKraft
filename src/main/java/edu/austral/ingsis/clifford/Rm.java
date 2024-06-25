package edu.austral.ingsis.clifford;

public class Rm implements Command {
  private final Directory cD;

  public Rm(Directory cd) {
    this.cD = cd;
  }

  @Override
  public String execute(String[] args) {
    if (args.length == 0) {
      return "Invalid command";
    }

    String name = args[args.length - 1];
    boolean recursive = args.length > 1 && args[0].equals("--recursive");

    FileSystem child = cD.findChildByName(name);
    if (child == null) {
      return "cannot remove '" + name + "', does not exist";
    } else if (child instanceof Directory && !recursive) {
      return "cannot remove '" + name + "', is a directory";
    } else {
      cD.removeChild(child);
      return "'" + name + "' removed";
    }
  }
}
