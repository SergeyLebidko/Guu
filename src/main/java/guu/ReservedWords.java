package guu;

import guu.Commands.*;

public enum ReservedWords {

    sub(new SubCommand()),
    set(new SetCommand()),
    print(new PrintCommand()),
    call(new CallCommand());

    private Command command;

    ReservedWords(Command command) {
        this.command = command;
    }

    public void execute(String line, Context context) throws Exception{
        command.execute(line, context);
    }

}
