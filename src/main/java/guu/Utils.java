package guu;

import java.util.ArrayList;
import java.util.HashMap;

public class Utils {

    //Метод преобразовывает единый текст программы в список строк кода
    public static ArrayList<String> getCodeLines(String codeString) {
        ArrayList<String> linesList = new ArrayList<>();
        String[] linesArr = codeString.split("\n");
        int linesCount = linesArr.length;
        for (int i = 0; i < linesCount; i++) {
            linesArr[i] = linesArr[i].trim();
            linesArr[i] = linesArr[i].toLowerCase();
            linesList.add(linesArr[i]);
        }
        return linesList;
    }

    //Метод ищет имена и адреса всех процедур программы
    public static HashMap<String, Integer> getSubList(ArrayList<String> code) throws Exception {
        HashMap<String, Integer> subs = new HashMap<>();

        String name;
        boolean nameValid;
        int numLine = -1;
        for (String line : code) {
            numLine++;
            if (line.startsWith("sub ")) {
                //Получаем имя процедуры и проверяем его на правильность
                name = line.substring(4);
                name = name.trim();
                nameValid = checkName(name);
                if (nameValid) {
                    if (subs.put(name, numLine) != null) {
                        throw new Exception("Имя процедуры " + name + " дублируется. Строка: " + numLine);
                    }
                } else {
                    throw new Exception("Недопустмое имя процедуры: " + name + " Строка: " + numLine);
                }
            }
        }
        return subs;
    }

    //Метод необходим для проверки корректности имен переменных и функций
    public static boolean checkName(String name) {
        if (name.equals("")) return false;

        String numbers = "0123456789";
        String letters = "_abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        //Первый символ не может быть цифрой
        if (numbers.indexOf(name.substring(0, 1)) != (-1)) return false;

        //Имя не может быть ключевым словом
        try {
            ReservedWords.valueOf(name.toLowerCase());
            return false;
        } catch (IllegalArgumentException e) {
        }

        //Имя может содержать только буквы, цифры и знак подчеркивания
        for (char c : name.toCharArray()) {
            if (numbers.indexOf(c) == (-1) & letters.indexOf(c) == (-1)) {
                return false;
            }
        }

        //Если все проверки пройдены - имя допустимо
        return true;
    }

}
