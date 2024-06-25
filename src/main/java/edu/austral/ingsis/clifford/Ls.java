package edu.austral.ingsis.clifford;

public class Ls implements Command {
  private final Directory cD;

  public Ls(Directory cd) {
    this.cD = cd;
  }

  @Override
  public String execute(String[] args) {
    if (args.length > 0 && args[0].equals("--ord=asc")) {
      return String.join(" ", cD.listItems("asc"));
    } else if (args.length > 0 && args[0].equals("--ord=desc")) {
      return String.join(" ", cD.listItems("desc"));
    } else {
      return String.join(" ", cD.listItems(null));
    }
  }
}
