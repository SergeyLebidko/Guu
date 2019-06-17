package guu;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;

public class MainClass {

    private static Resources resources;
    private static GUI gui;

    public static void main(String[] args) {
        resources = new Resources();

        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    gui = new GUI(resources);
                }
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Не удалось запустить графический интерфейс", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }


    }

}
