package guu;

import guu.Commands.Command;

public enum ReservedWords {

    sub(null),
    set(null),
    print(null),
    call(null);

    private Command command;

    ReservedWords(Command command) {
        this.command = command;
    }

    public void execute(String line, Context context) {
        command.execute(line, context);
    }

}
