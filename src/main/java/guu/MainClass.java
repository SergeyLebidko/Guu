package guu;

import javax.swing.*;

public class MainClass {

    private static Resources resources;
    private static Executor executor;
    private static Context context;
    private static GUI gui;

    public static void main(String[] args) {
        resources = new Resources();
        context = new Context();
        executor = new Executor(context);

        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    gui = new GUI(resources, executor);
                }
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Не удалось запустить графический интерфейс", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }

}
