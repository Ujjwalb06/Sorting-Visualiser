package sortingvisualiser;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class SortingPanel extends JPanel {
    private int[] array;
    private int delay = 50;
    private int highlighted1 = -1, highlighted2 = -1;
    private volatile boolean running = false;

    public SortingPanel() {
        generateArray();
    }

    public void generateArray() {
        Random rand = new Random();
        array = new int[20];  //  Reduced to 20 bars
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(400) + 50;
        }
        repaint();
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public boolean isRunning() {
        return running;
    }

    public void stopSorting() {  //  New stop method
        running = false;
    }

    public void repaintAndSleep() {
        repaint();
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void highlight(int i, int j) {
        highlighted1 = i;
        highlighted2 = j;
    }

    public void markSorted(int index) {
        Graphics g = getGraphics();
        if (g != null) {
            int width = getWidth() / array.length;
            int height = array[index];
            int x = index * width;
            g.setColor(Color.GREEN);
            g.fillRect(x, getHeight() - height, width - 2, height);
        }
    }

    public void startSorting(String algorithm) {
        if (running) return;
        running = true;
        new Thread(() -> {
            switch (algorithm) {
                case "Bubble Sort" -> SortingAlgorithms.bubbleSort(this, array);
                case "Selection Sort" -> SortingAlgorithms.selectionSort(this, array);
                case "Insertion Sort" -> SortingAlgorithms.insertionSort(this, array);
                case "Merge Sort" -> SortingAlgorithms.mergeSort(this, array, 0, array.length - 1);
                case "Quick Sort" -> SortingAlgorithms.quickSort(this, array, 0, array.length - 1);
            }
            running = false;
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = getWidth() / array.length;
        for (int i = 0; i < array.length; i++) {
            if (i == highlighted1)
                g.setColor(Color.YELLOW);
            else if (i == highlighted2)
                g.setColor(Color.RED);
            else
                g.setColor(new Color(100, 149, 237)); // Original blue color
            g.fillRect(i * width, getHeight() - array[i], width - 2, array[i]);
        }
    }
}
