package sortingvisualiser;

import javax.swing.*;
import java.awt.*;

public class SortingFrame extends JFrame {
    private final SortingPanel sortingPanel;
    private final JButton startButton, resetButton, stopButton;
    private final JComboBox<String> algorithmDropdown;
    private final JSlider speedSlider;

    public SortingFrame() {
        setTitle("Sorting Visualizer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLayout(new BorderLayout());

        sortingPanel = new SortingPanel();
        add(sortingPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        algorithmDropdown = new JComboBox<>(new String[]{
            "Bubble Sort", "Selection Sort", "Insertion Sort", "Merge Sort", "Quick Sort"
        });
        controlPanel.add(new JLabel("Algorithm:"));
        controlPanel.add(algorithmDropdown);

        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        resetButton = new JButton("Reset");

        controlPanel.add(startButton);
        controlPanel.add(stopButton);
        controlPanel.add(resetButton);

        controlPanel.add(new JLabel("Speed:"));
        speedSlider = new JSlider(1, 100, 50);
        controlPanel.add(speedSlider);

        add(controlPanel, BorderLayout.SOUTH);

        startButton.addActionListener(e -> {
            String algo = (String) algorithmDropdown.getSelectedItem();
            sortingPanel.startSorting(algo);
        });

        stopButton.addActionListener(e -> sortingPanel.stopSorting());

        resetButton.addActionListener(e -> sortingPanel.generateArray());

        speedSlider.addChangeListener(e ->
            sortingPanel.setDelay(101 - speedSlider.getValue())
        );

        setVisible(true);
    }
}
