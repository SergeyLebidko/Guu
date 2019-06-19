package guu;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilsTest {

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