package guu;

import java.util.ArrayList;

public class Utils {

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

}
