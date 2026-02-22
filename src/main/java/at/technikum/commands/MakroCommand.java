package at.technikum.commands;

import java.util.ArrayList;
import java.util.List;

//Kompositum
public class MakroCommand implements Command{ // component im Kompositum

    private List<Command> commands = new ArrayList<>();
    private String commandName;

    public MakroCommand(String commandName){
        this.commandName = commandName;
    }

    public MakroCommand() {
        this("unnamed");
    }

    @Override
    public void execute() {
        System.out.println("# Macro-Command: " + this.commandName);
        commands.forEach(Command::execute); // for-loop im Stream Syntax
    }

    public void addCommand(Command command){
        commands.add(command);
    }
}
