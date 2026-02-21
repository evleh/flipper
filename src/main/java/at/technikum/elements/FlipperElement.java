package at.technikum.elements;

import at.technikum.commands.Command;

public abstract class FlipperElement {
    private Command command;

    public FlipperElement(Command command) {
        this.command = command;
    }

    public void hit(){
        if(command != null){
            command.execute(); // dynamic Method resolution (?)
        }
    }

    public void setCommand(Command c){
        this.command = c;
    }

}
