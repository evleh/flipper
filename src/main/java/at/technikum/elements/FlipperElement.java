package at.technikum.elements;

import at.technikum.commands.Command;

public abstract class FlipperElement {
    private Command command;
    private String name;

    public FlipperElement(Command command) {
        this(command, "unnamed FlipperElement");
    }

    public FlipperElement(Command command, String name){
        this.command = command;
        this.name = name;
    }

    public void hit(){
        if(command != null){
            command.execute(); // dynamic Method resolution (?)
        }
    }

    public void setCommand(Command c){
        this.command = c;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
