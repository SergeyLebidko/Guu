package guu;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Executor {

    private static final Font mainFont = new Font("Arial", Font.PLAIN, 18);

    private JPanel contentPane;
    private JTextArea outputArea;

    private Context context;

    public Executor(Context context) {
        this.context = context;

        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(5, 5));

        outputArea = new JTextArea();
        outputArea.setFont(mainFont);
        outputArea.setEditable(false);
        context.setOutputArea(outputArea);

        contentPane.add(new JScrollPane(outputArea));
    }

    public JPanel getVisualComponent() {
        return contentPane;
    }

    public void start(String codeString) {
        //Подготавливаем контекст
        try {
            prepareContext(codeString);
        } catch (Exception e) {
            context.addTextToOutputArea("Ошибка: " + e.getMessage());
            return;
        }

        //Выводим код программы в консоль, помечая каждую строку номером
        int num = 0;
        for (String line : context.getCode()) {
            context.addTextToOutputArea(String.format("%4s", num + "") + ": " + line);
            num++;
        }
        context.addTextToOutputArea("");
        context.addTextToOutputArea("--------------------");
        context.addTextToOutputArea("");

        //Выполняем вызов функции main
        try {
            executeLine("call main");
        } catch (Exception e) {
            context.setErrFlag();
            context.addTextToOutputArea("Процедура main не найдена...");
            return;
        }

        //Запускаем метод, посторочно выполняющий код
        executeCode();
    }

    private void prepareContext(String codeString) throws Exception {
        //Инициализируем текущий контекст выполнения
        context.init();

        //Разбиваем код на отдельные строки
        ArrayList<String> codeLines = Utils.getCodeLines(codeString);

        //Добавляем строки кода в текущий контекст
        context.setCode(codeLines);

        //Получаем имена и адреса всех функций и добавляем их в контекст
        HashMap<String, Integer> subs = Utils.getSubList(context.getCode());
        context.setSubs(subs);
    }

    private void executeCode() {
        String line;

        //Цикл перебора команд программы
        while (true) {

            //Если стек пуст - программа завершается
            if (context.isEmptyStack()) {
                context.addTextToOutputArea("Выполнение завершено...");
                break;
            }

            //Получаем следующую строку кода
            line = context.getNextCodeLine();

            //Если получен null - поднимаемся вверх по стеку к вызывавшей процедуре
            if (line == null) {
                context.removeLastStackElement();
                continue;
            }

            //Если получена пустая строка - просто игнорируем её
            if (line.equals("")) {
                continue;
            }

            //В противном случае - пытаемся выполнить строку кода. В случае возникновения ошибки, прерываем выполнение программы
            try {
                executeLine(line);
            } catch (Exception e) {
                context.setErrFlag();
                context.addTextToOutputArea("Ошибка. Процедура: " + context.getCurrentSubName() + " Строка: " + context.getCurrentPointer() + " " + e.getMessage());
                break;
            }
        }
    }

    private void executeLine(String line) throws Exception {
        //Получаем оператор строки
        String command = line.split(" ")[0];

        ReservedWords word = null;
        try {
            word = ReservedWords.valueOf(command);
        } catch (IllegalArgumentException ex) {
            throw new Exception("Неизвестная команда " + command);
        }

        word.execute(line, context);
    }

}
