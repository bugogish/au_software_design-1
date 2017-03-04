package shell;

import commands.*;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Factory which is used to construct Command objects relying on command token
 */
public class CommandFactory {


    /**
     * @param commandToken token, which contains command name as a String
     * @param args arguments of a command
     * @param env Environment object
     * @return generated runnable Command object
     */
    public static Command produce(Token commandToken, List<String> args, Environment env) {
        Command command;
        switch (commandToken.getType()) {
            case EQ:
                command = new Eq(args, env);
                return command;
            case WORD:
                command = getCommand(commandToken.toString(), args, env);
                return command;
            default:
                return null;
        }
    }

    private static Command getCommand(String commandName, List<String> args, Environment env) {

        Command command = null;
        switch (commandName) {
            case Echo.NAME:
                command = new Echo(args, env);
                break;
            case Cat.NAME:
                command = new Cat(args, env);
                break;
            case Wc.NAME:
                command = new Wc(args, env);
                break;
            case Pwd.NAME:
                command = new Pwd(args, env);
                break;
            case Exit.NAME:
                command = new Exit(args, env);
                break;
            default:
                command = new OutSource(commandName, args, env);
                break;

        }
        return command;
    }

    private static String concat(List<String> args) {

        return args.stream().collect(Collectors.joining());
    }
}
