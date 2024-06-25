package edu.austral.ingsis.clifford;

import java.util.*;

public class CLI {
    private Directory currentDirectory;
    private final Map<String, Command> commands;

    public CLI(Directory rootDirectory) {
        this.currentDirectory = rootDirectory;
        this.commands = initializeCommands(rootDirectory);
    }

    private Map<String, Command> initializeCommands(Directory directory) {
        Map<String, Command> commandMap = new HashMap<>();
        commandMap.put("ls", new Ls(directory));
        commandMap.put("mkdir", new Mkdir(directory));
        commandMap.put("cd", new Cd(directory, directory));
        commandMap.put("pwd", new Pwd(directory));
        commandMap.put("touch", new Touch(directory));
        commandMap.put("rm", new Rm(directory));
        return commandMap;
    }

    public List<String> executeCommands(List<String> commands) {
        List<String> results = new ArrayList<>();
        for (String command : commands) {
            results.add(executeCommand(command));
        }
        return results;
    }

    private String executeCommand(String command) {
        String[] parts = command.split("\\s+");
        String cmd = parts[0];
        String[] args = Arrays.copyOfRange(parts, 1, parts.length);

        Command commandToExecute = commands.get(cmd);
        if (commandToExecute != null) {
            String result = commandToExecute.execute(args);
            if (commandToExecute instanceof Cd) {
                updateCurrentDirectory();
            }
            return result;
        } else {
            return "Unknown command";
        }
    }

    private void updateCurrentDirectory() {
        currentDirectory = Cd.getCurrentDirectory();
        commands.replace("ls", new Ls(currentDirectory));
        commands.replace("mkdir", new Mkdir(currentDirectory));
        commands.replace("pwd", new Pwd(currentDirectory));
        commands.replace("touch", new Touch(currentDirectory));
        commands.replace("rm", new Rm(currentDirectory));
    }
}
