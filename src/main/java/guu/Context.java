package guu;

import java.util.ArrayList;
import java.util.HashMap;

public class Context {

    private ArrayList<String> code;                   //Список строк кода
    private ArrayList<String> err;                    //Список ошибок
    private HashMap<String, Integer> vars;            //Массив имен переменных и их значений
    private HashMap<String, Integer> subs;            //Массив имен процедур и их адресов

    public Context() {
        code = new ArrayList<>();
        err = new ArrayList<>();
        vars = new HashMap<>();
        subs = null;
    }

    public void init() {
        code.clear();
        err.clear();
        vars.clear();
        subs = null;
    }

    public ArrayList<String> getCode() {
        return code;
    }

    public ArrayList<String> getErr() {
        return err;
    }

    public HashMap<String, Integer> getSubs() {
        return subs;
    }

    public void setSubs(HashMap<String, Integer> subs) {
        this.subs = subs;
    }
}
