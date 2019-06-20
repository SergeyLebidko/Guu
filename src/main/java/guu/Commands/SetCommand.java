package guu.Commands;

import guu.Context;
import guu.Utils;

public class SetCommand implements Command {

    @Override
    public void execute(String line, Context context) throws Exception {
        String paramLine = line.substring(4);

        //Список параметров не может быть пустым
        paramLine = paramLine.trim();
        if (paramLine.equals("")) {
            throw new Exception("Пустой список параметров");
        }

        //Удалаем лишние пробелы между словами
        while (paramLine.indexOf("  ") >= 0) {
            paramLine = paramLine.replace("  ", " ");
        }

        //Разделяем список параметров на отдельные элементы
        String[] paramList = paramLine.split(" ");

        //Элементов должно быть всегда два
        if (paramList.length != 2) {
            throw new Exception("Некорректное количество параметров");
        }

        //Проверяем имя переменно на корректность
        String varName = paramList[0];
        boolean isValidName = Utils.checkName(varName);
        if (!isValidName) {
            throw new Exception("Некорректное имя переменной");
        }

        //Проверяем значение переменной на корректность
        Integer varValue;
        try {
            varValue = Integer.parseInt(paramList[1]);
        } catch (NumberFormatException e) {
            throw new Exception("Некорректное значение переменной");
        }

        //Если все проверки прошли успешно - вносим имя и значение переменной в соответсвующий список
        context.addVarValue(varName, varValue);
    }

}
