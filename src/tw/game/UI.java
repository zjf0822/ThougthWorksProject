package tw.game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

/**
 * Created by zhoujifa-VAIO on 2018/6/9.
 */
public class UI extends JFrame {
    private final World world;

    public UI(int rows,int columns) {
        world = new World(rows, columns);
        new Thread(world).start();
        add(world);
    }

    public static void main(String[]args) {
        Scanner scanner = new Scanner(System.in);
        int rows = 0;
        int cols = 0;
        try {
            rows = scanner.nextInt();
            cols = scanner.nextInt();
            while (rows <= 0 || cols <= 0) {
                System.out.println("invalid input, rows and cols should more than zero");
                rows = scanner.nextInt();
                cols = scanner.nextInt();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("输入的类型错误" + ex.getMessage());
        }


        UI frame = new UI(rows, cols);

        JMenuBar menu = new JMenuBar();
        frame.setJMenuBar(menu);

        JMenu options =new JMenu("Options");
        menu.add(options);

        JMenuItem arrow=options.add("Arrow");
        arrow.addActionListener(frame.new ArrowActionListener());
        JMenuItem square=options.add("Square");
        square.addActionListener(frame.new SquareActionListener());
        JMenuItem random = options.add("Random");
        random.addActionListener(frame.new RandomActionListener());

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);
        frame.setTitle("Game of Life");
        frame.setVisible(true);
        frame.setResizable(true);
    }

    private class ArrowActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            world.setArrow();
        }
    }
    private class SquareActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            world.setSquare();
        }
    }

    private class RandomActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            world.random();
        }
    }
}
