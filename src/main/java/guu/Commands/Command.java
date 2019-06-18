package guu.Commands;

import guu.Context;

public interface Command {

    void execute(String line, Context context);

}
