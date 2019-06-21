package guu;

import javax.swing.*;
import java.awt.*;

public class DebugDialog {

    private static final int DIALOG_WIDTH = 400;
    private static final int DIALOG_HEIGHT = 300;

    private static JDialog dialog;

    private static JButton entryBtn;
    private static JButton skipBtn;
    private static JButton varsBtn;
    private static JButton stackBtn;

    private static JDialog varsDialog;
    private static JDialog stackDialog;

    public static void createDialog(JFrame owner){
        //Создаем диалог запроса входа в процедуру
        dialog = new JDialog(owner, true);
        //dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setSize(DIALOG_WIDTH, DIALOG_HEIGHT);
        dialog.setResizable(false);
        int xPos = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - DIALOG_WIDTH / 2;
        int yPos = Toolkit.getDefaultToolkit().getScreenSize().height / 2 - DIALOG_HEIGHT / 2;
        dialog.setLocation(xPos, yPos);

    }

    public static boolean showDialog() {
        dialog.setVisible(true);
        return true;
    }

}
