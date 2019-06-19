package guu;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Context {

    private ArrayList<String> code;                   //Список строк кода
    private ArrayList<String> err;                    //Список ошибок
    private HashMap<String, Integer> vars;            //Массив имен переменных и их значений
    private HashMap<String, Integer> subs;            //Массив имен процедур и их адресов
    private LinkedList<StackElement> stack;           //Стек вызовов процедур

    private JTextArea outputArea;                     //Панель вывода

    public Context() {
        code = new ArrayList<>();
        err = new ArrayList<>();
        vars = new HashMap<>();
        stack = new LinkedList<>();
        subs = null;
    }

    public void init() {
        code.clear();
        err.clear();
        vars.clear();
        stack.clear();
        subs = null;
    }

    //Установка панели вывода информации
    public void setOutputArea(JTextArea outputArea) {
        this.outputArea = outputArea;
    }

    //Метод для получения текущей панели вывода информации
    public JTextArea getOutputArea() {
        return outputArea;
    }

    //Необходим для доступа к строкам исходного кода
    public ArrayList<String> getCode() {
        return code;
    }

    //Необходим для доступа к стеку ошибок
    public ArrayList<String> getErr() {
        return err;
    }

    //Необходим для доступа к переменным и их значениям
    public HashMap<String, Integer> getVars() {
        return vars;
    }

    //Негобходим для доступа к стеку с целью получения трассировки вызовов
    public LinkedList<StackElement> getStack() {
        return stack;
    }

    //Необходим для доступа к списку процедур с целью получения их адресов
    public HashMap<String, Integer> getSubs() {
        return subs;
    }

    //Необходим для установки списка процедур и их адресов
    public void setSubs(HashMap<String, Integer> subs) {
        this.subs = subs;
    }

    //Необходим для проверки, есть ли в стеке элементы или нет
    public boolean isEmptyStack(){
        return stack.isEmpty();
    }

    //Необходим для получения следующей по ходу выполнения команды. Если достигнут конец файла или стек пуст - возвращает null
    public String getNextCodeLine(){
        if (!isEmptyStack()){
            StackElement element = stack.peekLast();
            element.shiftPointer();
            if (element.getPointer()>=code.size())return null;
            return code.get(element.getPointer());
        }
        return null;
    }

    //Необходим для удаления последнего элемента из стека с целью возврата к вызвавшей процедуре
    public void removeLastStackElement(){
        if (!isEmptyStack()){
            stack.pollLast();
        }
    }

    //Метод возвращает имя текущей выполняемой процедуры
    public String getCurrentSubName(){
        if (!isEmptyStack()){
            return stack.peekLast().getSubName();
        }
        return "";
    }

    //Метод возвращает текущее положение указателя
    public Integer getCurrentPointer(){
        if (!isEmptyStack()){
            return stack.peekLast().getPointer();
        }
        return -1;
    }

}
