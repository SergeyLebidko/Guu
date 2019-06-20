package guu;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void checkGetCodeLines(){
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
        for (String line: lines){
            codeString+=line+"\n";
        }

        int expectedLines = lines.length;
        int actualLines = Utils.getCodeLines(codeString).size();
        assertEquals("Проверка разделения кода на строки не успешна!", expectedLines, actualLines);
    }

    @Test
    public void checkGetSubList(){
        ArrayList<String> code = new ArrayList<>();
        String[] codeLines = {
                "sub main",
                "set x 10",
                "print x",
                "call func",
                "",
                "sub func",
                "sub start"
        };
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
        for (int i=0; i<lines.length;i++){
            assertEquals("Проверка "+lines[i]+" не успешна!", Utils.checkName(lines[i]), results[i]);
        }
    }

}