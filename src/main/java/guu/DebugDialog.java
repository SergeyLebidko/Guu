package guu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class DebugDialog {

    private static final int DIALOG_WIDTH = 600;
    private static final int DIALOG_HEIGHT = 300;

    private static final Font mainFont = new Font("Arial", Font.PLAIN, 18);

    private static JDialog dialog;
    private static Context currentContext;
    private static JTextArea outputArea;
    private static boolean answer;

    private static JButton entryBtn;
    private static JButton skipBtn;
    private static JButton varsBtn;
    private static JButton stackBtn;

    public static void createDialog(JFrame owner) {
        //Создаем диалог запроса входа в процедуру
        dialog = new JDialog(owner, true);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setSize(DIALOG_WIDTH, DIALOG_HEIGHT);
        dialog.setResizable(false);
        int xPos = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - DIALOG_WIDTH / 2;
        int yPos = Toolkit.getDefaultToolkit().getScreenSize().height / 2 - DIALOG_HEIGHT / 2;
        dialog.setLocation(xPos, yPos);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(5, 5));
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(mainFont);
        contentPane.add(new JScrollPane(outputArea));

        JPanel btnPane = new JPanel();
        btnPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
        entryBtn = new JButton("Войти");
        skipBtn = new JButton("Пропустить");
        varsBtn = new JButton("Переменные");
        stackBtn = new JButton("Стек");
        btnPane.add(entryBtn);
        btnPane.add(skipBtn);
        btnPane.add(varsBtn);
        btnPane.add(stackBtn);
        contentPane.add(btnPane, BorderLayout.SOUTH);

        dialog.setContentPane(contentPane);

        createListeners();
    }

    private static void createListeners() {
        entryBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answer = true;
                dialog.setVisible(false);
            }
        });

        skipBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answer = false;
                dialog.setVisible(false);
            }
        });

        varsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Set<Map.Entry<String, Integer>> varSet = currentContext.getVars();
                outputArea.append("Переменные и их значения:\n");
                for (Map.Entry entry : varSet) {
                    outputArea.append("   " + entry.getKey() + " = " + entry.getValue() + "\n");
                }
                outputArea.append("\n");
            }
        });

        stackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LinkedList<StackElement> stack = currentContext.getStack();
                outputArea.append("Стек вызовов:\n");
                for (StackElement element : stack) {
                    outputArea.append("   " + element.getSubName() + "\n");
                }
                outputArea.append("\n");
            }
        });
    }

    public static boolean showDialog(Context context) {
        currentContext = context;
        outputArea.setText("");
        outputArea.append("Вход в процедуру: " + context.getCurrentSubName() + "\n");
        outputArea.append("\n");

        dialog.setVisible(true);
        return answer;
    }

}
