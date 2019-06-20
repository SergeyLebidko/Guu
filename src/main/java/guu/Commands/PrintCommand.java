package guu.Commands;

import guu.Context;
import guu.Utils;

public class PrintCommand implements Command {

    @Override
    public void execute(String line, Context context) throws Exception {
        String varName = line.substring(6);

        varName = varName.trim();
        boolean isValidName = Utils.checkName(varName);
        if (!isValidName) {
            throw new Exception("Недопустимое имя переменной в команде print");
        }

        Integer varValue = context.getVarValue(varName);
        if (varValue == null) {
            throw new Exception("Переменной не присвоено значение");
        }

        context.addTextToOutputArea(varName + " = " + varValue);
    }

}
