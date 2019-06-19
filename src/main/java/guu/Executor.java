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

        contentPane.add(new JScrollPane(outputArea));
    }

    public JPanel getVisualComponent() {
        return contentPane;
    }

    public void start(String codeString) {
        //Очищаем область вывода результатов работы программы
        outputArea.setText("");

        //Подготавливаем контекст
        prepareContext(codeString);

        //Выполняем вызов функции main
        executeLine("call main");

        //Запускаем метод, посторочно выполняющий код
        executCode();
    }

    private void prepareContext(String codeString) {
        //Инициализируем текущий контекст выполнения
        context.init();

        //Разбиваем код на отдельные строки
        ArrayList<String> codeLines = Utils.getCodeLines(codeString);

        //Первая проверка правильности: код должен содержать хотя бы одну строку
        boolean findNotEmptyLine = false;
        for (String line : codeLines) {
            if (!line.equals("")) {
                findNotEmptyLine = true;
                break;
            }
        }
        if (!findNotEmptyLine) {
            String errMessage = "Текст программы не содержит ни одной строки...";
            context.getErr().add(errMessage);
            outputArea.append(errMessage);
            return;
        }

        //Добавляем строки кода в текущий контекст
        context.getCode().addAll(codeLines);

        //Получаем имена и адреса всех функций и добавляем их в контекст выполнения
        try {
            HashMap<String, Integer> subs = Utils.getSubList(context.getCode());
            context.setSubs(subs);
        } catch (Exception e) {
            context.getErr().add(e.getMessage());
            outputArea.append(e.getMessage());
            return;
        }
    }

    private void executCode() {
        //Цикл перебора команд программы
    }

    private void executeLine(String line) {
        //Выполнение команды из строки line
    }

}
