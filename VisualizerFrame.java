package sortingvisualiser;

import javax.swing.*;
import java.awt.*;

public class VisualizerFrame extends JFrame {

    private int[] array;

    public VisualizerFrame(int[] array) {
        this.array = array;

        setTitle("Sorting Visualizer");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int width = getWidth() / array.length;

        for (int i = 0; i < array.length; i++) {
            int height = array[i] * 5;
            g.setColor(Color.CYAN);
            g.fillRect(i * width, getHeight() - height, width - 2, height);
        }
    }

    public void updateArray(int[] newArray) {
        this.array = newArray;
        repaint();
    }
}

