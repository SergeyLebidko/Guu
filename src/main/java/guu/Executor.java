package guu;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

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
        if (codeLines.size()==0){
            String errMessage = "Текст программы не содержит ни одной строки...";
            context.getErr().add(errMessage);
            outputArea.append(errMessage);
            return;
        }

        //Добавляем строки кода в текущий контекст
        context.getCode().addAll(codeLines);
    }

    public JPanel getVisualComponent() {
        return contentPane;
    }

    public Context getContext() {
        return context;
    }

}
