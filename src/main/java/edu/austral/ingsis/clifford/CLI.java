package edu.austral.ingsis.clifford;
import java.util.*;

public class CLI {
    private Directory cD;
    private final Map<String, Command> commands;

    public CLI(Directory root) {
        this.cD = root;
        this.commands = new HashMap<>();
        this.commands.put("ls", new Ls(cD));
        this.commands.put("mkdir", new Mkdir(cD));
        this.commands.put("cd", new Cd(cD, root));
        this.commands.put("pwd", new Pwd(cD));
        this.commands.put("touch", new Touch(cD));
        this.commands.put("rm", new Rm(cD));
    }

    private String executeCommand(String command) {
        String[] comSpl = command.split("\\s+");
        String cmd = comSpl[0];
        String[] arguments = Arrays.copyOfRange(comSpl, 1, comSpl.length);

        Command exec = commands.get(cmd);
        if (exec != null) {
            String res = exec.execute(arguments);
            if (exec instanceof Cd) {
                cD = Cd.getcD();
                commands.replace("ls", new Ls(cD));
                commands.replace("mkdir", new Mkdir(cD));
                commands.replace("pwd", new Pwd(cD));
                commands.replace("touch", new Touch(cD));
                commands.replace("rm", new Rm(cD));
            }
            return res;
        } else {
            return "Unknown command";
        }
    }
    public List<String> executeCommands(List<String> commands) {
        List<String> res = new ArrayList<>();
        for (String command : commands) {
            String added = executeCommand(command);
            res.add(added);
        }
        return res;
    }
}