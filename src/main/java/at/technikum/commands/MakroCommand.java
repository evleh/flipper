package at.technikum.commands;

import java.util.ArrayList;
import java.util.List;

//Kompositum
public class MakroCommand implements Command{ // component im Kompositum

    private List<Command> commands = new ArrayList<>();


    @Override
    public void execute() {
        commands.forEach(Command::execute); // for-loop im Stream Syntax
    }

    public void addCommand(Command command){
        commands.add(command);
    }
}
