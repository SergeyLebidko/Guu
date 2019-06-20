package guu;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void checkGetCodeLines() {
        String[] lines = {
                "",
                "",
                "   ",
                "sub main",
                "",
                "   ",
                "set x 10",
                "print x",
                " ",
                " "
        };
        String codeString = "";
        for (String line : lines) {
            codeString += line + "\n";
        }

        int expectedLines = lines.length;
        int actualLines = Utils.getCodeLines(codeString).size();
        assertEquals("Проверка разделения кода на строки не успешна!", expectedLines, actualLines);
    }

    @Test
    public void checkGetSubList() {
        ArrayList<String> code = new ArrayList<>();

        //Проверяем корректный код
        String[] codeLines1 = {
                "sub main",
                "set x 10",
                "print x",
                "call func",
                "",
                "sub func",
                "sub start"
        };
        for (String line : codeLines1) {
            code.add(line);
        }
        int expected = 3;
        int actual = 0;
        HashMap<String, Integer> subList = new HashMap<>();
        try {
            subList = Utils.getSubList(code);
            actual = subList.size();
        } catch (Exception e) {
            fail("Возникает исключение при работе тестируемого метода с корректными данными. Описание ошибки: " + e.getMessage());
        }
        assertEquals("Проверка поиска процедур не успешна для корректных данных!", expected, actual);

        assertEquals("Метод getSubList не корректно определяет адрес процедуры", new Integer(0), subList.get("main"));
        assertEquals("Метод getSubList не корректно определяет адрес процедуры", new Integer(5), subList.get("func"));
        assertEquals("Метод getSubList не корректно определяет адрес процедуры", new Integer(6), subList.get("start"));

        //Проверяем код, содержащий ошибки (повторяющиеся имена процедур)
        code.clear();
        String[] codeLines2 = {
                "sub main",
                "set x 10",
                "print x",
                "call func",
                "",
                "sub func",
                "sub func"
        };
        for (String line : codeLines2) {
            code.add(line);
        }
        try {
            Utils.getSubList(code);
            fail("Тестируемый метод не находит повторяющиеся имена процедур");
        } catch (Exception e) {
            assertNotEquals("", e.getMessage());
        }

        //Проверяем код, содержащий ошибки (не корректные имена процедур)
        code.clear();
        String[] codeLines3 = {
                "sub 0main",
                "set x 10",
                "print x",
                "call func",
                "",
                "sub 12",
                "sub func"
        };
        for (String line : codeLines3) {
            code.add(line);
        }
        try {
            Utils.getSubList(code);
            fail("Тестируемый метод не находит не корректные имена процедур");
        } catch (Exception e) {
            assertNotEquals("", e.getMessage());
        }

    }

    @Test
    public void checkName() {
        String[] lines = {
                "1func",
                "",
                "sub",
                "Call",
                "pRINT",
                "sEt",
                "_ab12",
                "x",
                "N"
        };
        boolean[] results = {
                false,
                false,
                false,
                false,
                false,
                false,
                true,
                true,
                true
        };
        for (int i = 0; i < lines.length; i++) {
            assertEquals("Проверка " + lines[i] + " не успешна!", Utils.checkName(lines[i]), results[i]);
        }
    }

}