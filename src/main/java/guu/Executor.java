package guu;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import static guu.ReservedWords.*;

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

    public void start(String codeString) {
        //Очищаем область вывода результатов работы программы
        outputArea.setText("");

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

        //Получаем имена и адреса всех функций
        try {
            HashMap<String, Integer> subs = Utils.getSubList(context.getCode());
            context.setSubs(subs);
        } catch (Exception e) {
            context.getErr().add(e.getMessage());
            outputArea.append(e.getMessage());
            return;
        }

        //Получаем адрес функции main
        Integer pointer = context.getSubs().get("main");
        if (pointer == null) {
            String errMessage = "Процедура main не найдена";
            context.getErr().add(errMessage);
            outputArea.append(errMessage);
            return;
        }

    }

    public JPanel getVisualComponent() {
        return contentPane;
    }

    public Context getContext() {
        return context;
    }

}
