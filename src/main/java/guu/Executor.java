package guu;

import javax.swing.*;
import java.awt.*;

public class Executor {

    private JPanel contentPane;

    public Executor() {
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(5, 5));

        //Тестовый код
        JLabel lab = new JLabel("Панель выполнения кода");
        lab.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lab);
    }

    public void start(String codeString) {
        System.out.println(codeString);
    }

    public JPanel getVisualComponent() {
        return contentPane;
    }

}
