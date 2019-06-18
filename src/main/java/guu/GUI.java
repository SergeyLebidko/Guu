package guu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {

    private static final int FRM_WIDTH = 1000;
    private static final int FRM_HEIGHT = 700;

    private static final String EDITOR_PANE = "editor";
    private static final String RUN_PANE = "run";

    private static final Font mainFont = new Font("Arial", Font.PLAIN, 18);

    private Resources resources;
    private Executor executor;

    private JFrame frm;
    private JButton startBtn;
    private JButton stopBtn;

    private JPanel workPane;
    private CardLayout workPaneLayout;
    private JPanel editorPane;
    private JPanel runPane;

    private JTextArea codeArea;

    public GUI(Resources resources, Executor executor) {
        this.resources = resources;
        this.executor = executor;

        frm = new JFrame("Guu");
        frm.setIconImage(resources.getImage("logo"));
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setSize(FRM_WIDTH, FRM_HEIGHT);
        frm.setResizable(false);
        int xPos = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - FRM_WIDTH / 2;
        int yPos = Toolkit.getDefaultToolkit().getScreenSize().height / 2 - FRM_HEIGHT / 2;
        frm.setLocation(xPos, yPos);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(5, 5));

        JPanel btnPane = new JPanel();
        btnPane.setLayout(new FlowLayout(FlowLayout.LEFT));
        contentPane.add(btnPane, BorderLayout.NORTH);

        startBtn = new JButton(resources.getImageIcon("start"));
        startBtn.setToolTipText("Запустить");
        stopBtn = new JButton(resources.getImageIcon("stop"));
        stopBtn.setToolTipText("Остановить");
        stopBtn.setEnabled(false);
        btnPane.add(startBtn);
        btnPane.add(stopBtn);

        workPane = new JPanel();
        workPaneLayout = new CardLayout();
        workPane.setLayout(workPaneLayout);

        editorPane = new JPanel();
        editorPane.setLayout(new BorderLayout());
        editorPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        codeArea = new JTextArea();
        codeArea.setFont(mainFont);
        JScrollPane scrollPane = new JScrollPane(codeArea);
        editorPane.add(scrollPane, BorderLayout.CENTER);

        runPane = new JPanel();
        runPane.setLayout(new BorderLayout());
        runPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        runPane.add(executor.getVisualComponent(), BorderLayout.CENTER);

        workPane.add(editorPane, EDITOR_PANE);
        workPane.add(runPane, RUN_PANE);
        contentPane.add(workPane, BorderLayout.CENTER);

        createActionListeners();

        frm.setContentPane(contentPane);
        frm.setVisible(true);
    }

    private void createActionListeners() {
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopBtn.setEnabled(true);
                startBtn.setEnabled(false);
                workPaneLayout.show(workPane, RUN_PANE);
                executor.start(codeArea.getText());
            }
        });

        stopBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopBtn.setEnabled(false);
                startBtn.setEnabled(true);
                workPaneLayout.show(workPane, EDITOR_PANE);
            }
        });
    }

}
