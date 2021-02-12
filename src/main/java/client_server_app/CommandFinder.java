package client_server_app;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Command finder looks for commands in the file.
 */
public class CommandFinder {
    private Map<String, String> commands;

    /**
     * Instantiates a new Command finder.
     *
     * @param sourceFile the source file
     */
    public CommandFinder(String sourceFile) {
        parseCommands(sourceFile);
    }

    private void parseCommands(String sourceFile) {
        try (BufferedReader in = new BufferedReader(new FileReader(sourceFile))) {
            commands = in.lines().map(x -> x.split(":"))
                    .collect(Collectors.toMap(x -> x[0].trim(), x -> x[1].trim()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Check input boolean.
     *
     * @param input the input
     * @return the boolean
     */
    public boolean checkInput (String input){
        return commands.containsKey(input);
    }

    /**
     * Gets command answer.
     *
     * @param input the input
     * @return the command answer
     */
    public String getCommandAnswer(String input) {
        return "Server answer: " + commands.get(input) ;
    }
}
