package guu;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Context {

    private static final int MAX_STACK_SIZE = 20;     //Максимальная глубина вызовов (максимальный размер стека)

    private ArrayList<String> code;                   //Список строк кода
    private HashMap<String, Integer> vars;            //Массив имен переменных и их значений
    private HashMap<String, Integer> subs;            //Массив имен процедур и их адресов
    private LinkedList<StackElement> stack;           //Стек вызовов процедур
    private boolean err;                              //Признак возникновения ошибки

    private JTextArea outputArea;                     //Панель вывода

    public Context() {
        code = new ArrayList<>();
        vars = new HashMap<>();
        stack = new LinkedList<>();
        subs = null;
    }

    public void init() {
        code.clear();
        vars.clear();
        stack.clear();
        subs = null;
        err = false;
        if (outputArea != null) {
            outputArea.setText("");
        }
    }

    //Метод устанавливает компонент для вывода информации пользователю. Необходим для работы операторов вывода
    public void setOutputArea(JTextArea outputArea) {
        this.outputArea = outputArea;
    }

    //Метод добавляет строку в панель вывода
    public void addTextToOutputArea(String text) {
        if (outputArea != null) outputArea.append(text + "\n");
    }

    //Метод добавляет строки кода в текущий контекст
    public void setCode(ArrayList<String> code) {
        this.code.addAll(code);
    }

    //Метод необходим для доступа к строкам исходного кода
    public ArrayList<String> getCode() {
        return code;
    }

    //Метод добавляет значение переменной
    public void addVarValue(String varName, int value) {
        vars.put(varName, value);
    }

    //Метод необходим для получения значение переменной
    public Integer getVarValue(String varName) {
        return vars.get(varName);
    }

    //Метод необходим для доступа к стеку с целью получения трассировки вызовов
    public LinkedList<StackElement> getStack() {
        return stack;
    }

    //Метод вносит элемент в стек. При этом контролируется размер стека (дабы избежать его переполнения)
    public void addElementToStack(StackElement element) throws Exception {
        if (stack.size() == MAX_STACK_SIZE) throw new Exception("Стек переполнен");
        stack.add(element);
    }

    //Необходим для установки списка процедур и их адресов
    public void setSubs(HashMap<String, Integer> subs) {
        this.subs = subs;
    }

    //Метод необходим для получения адреса процедуры. Если процедура не найдена - возвращает null
    public Integer getSubAdress(String subName) {
        return subs.get(subName);
    }

    //Метод необходим для проверки, пуст ли стек
    public boolean isEmptyStack() {
        return stack.isEmpty();
    }

    //Метод необходим для получения следующей по ходу выполнения команды. Если достигнут конец файла или стек пуст - возвращает null
    public String getNextCodeLine() {
        if (!isEmptyStack()) {
            StackElement element = stack.peekLast();
            element.shiftPointer();
            if (element.getPointer() >= code.size()) return null;
            return code.get(element.getPointer());
        }
        return null;
    }

    //Метод необходим для удаления последнего элемента из стека
    public void removeLastStackElement() {
        if (!isEmptyStack()) {
            stack.pollLast();
        }
    }

    //Метод возвращает имя текущей выполняемой процедуры
    public String getCurrentSubName() {
        if (!isEmptyStack()) {
            return stack.peekLast().getSubName();
        }
        return "";
    }

    //Метод возвращает текущее положение указателя
    public Integer getCurrentPointer() {
        if (!isEmptyStack()) {
            return stack.peekLast().getPointer();
        }
        return -1;
    }

    //Метод устанавливает признак возникновения ошибки во время выполнения кода или подготовки контекста
    public void setErrFlag(){
        err = true;
    }

    //Метод возвращает true, если во время выполнения были зарегистрированы ошибки
    public boolean getErrFlag(){
        return err;
    }

}
