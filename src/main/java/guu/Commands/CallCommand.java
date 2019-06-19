package guu.Commands;

import guu.Context;
import guu.StackElement;
import guu.Utils;

public class CallCommand implements Command {

    @Override
    public void execute(String line, Context context) throws Exception {
        String subName = line.substring(5);

        boolean isValidName = Utils.checkName(subName);
        if (!isValidName) {
            throw new Exception("Некорректное имя вызываемой процедуры");
        }

        Integer subAddr = context.getSubs().get(subName);
        if (subAddr == null) {
            throw new Exception("Вызываемая процедура не найдена");
        }
        context.getStack().add(new StackElement(subName, subAddr));
    }

}
