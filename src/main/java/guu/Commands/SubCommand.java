package guu.Commands;

import guu.Context;

public class SubCommand implements Command {

    @Override
    public void execute(String line, Context context) {
        context.removeLastStackElement();
    }

}
