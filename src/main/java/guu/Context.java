package guu;

import java.util.ArrayList;
import java.util.HashMap;

public class Context {

    private ArrayList<String> code;                   //Список строк кода
    private ArrayList<String> err;                    //Список ошибок
    private HashMap<String, Integer> vars;            //Массив имен переменных и их значений

    public Context() {
        code = new ArrayList<>();
        err = new ArrayList<>();
        vars = new HashMap<>();
    }

    public void init() {
        code.clear();
        err.clear();
        vars.clear();
    }

    public ArrayList<String> getCode() {
        return code;
    }

    public ArrayList<String> getErr() {
        return err;
    }

}
