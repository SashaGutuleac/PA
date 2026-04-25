package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

// clasa principala care ridica aplicatia
public class MazeApp extends JFrame {

    // aici tinem panourile ca sa le putem folosi
    private ConfigPanel configPanel;
    private ControlPanel controlPanel;
    private DrawingPanel canvas;

    public MazeApp() {
        super("labirint compulsory");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // bagam bucatile in memorie
        canvas = new DrawingPanel();
        configPanel = new ConfigPanel(this);
        controlPanel = new ControlPanel(this);

        // le punem pe pozitii cum a cerut in cerinta
        add(configPanel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        setSize(600, 600);
        setLocationRelativeTo(null);
    }

    // o facem publica ca sa o acceseze butoanele
    public DrawingPanel getCanvas() {
        return canvas;
    }

    // mainul clasic
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MazeApp().setVisible(true);
        });
    }
}

// panoul de sus
class ConfigPanel extends JPanel {
    private MazeApp frame;
    private JLabel label;
    private JSpinner sizeSpinner;
    private JButton drawBtn;

    public ConfigPanel(MazeApp frame) {
        this.frame = frame;

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        label = new JLabel("dimensiune labirint:");

        // bagam un spinner intre 2 si 100, default 10
        SpinnerNumberModel model = new SpinnerNumberModel(10, 2, 100, 1);
        sizeSpinner = new JSpinner(model);

        drawBtn = new JButton("deseneaza grila");

        // la click zicem la canvas sa se deseneze
        drawBtn.addActionListener(e -> {
            int size = (Integer) sizeSpinner.getValue();
            frame.getCanvas().initGrid(size);
        });

        add(label);
        add(sizeSpinner);
        add(drawBtn);
    }
}

// panoul de control pus jos
class ControlPanel extends JPanel {
    private MazeApp frame;
    private JButton createBtn, resetBtn, exitBtn;

    public ControlPanel(MazeApp frame) {
        this.frame = frame;
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        createBtn = new JButton("Create");
        resetBtn = new JButton("Reset");
        exitBtn = new JButton("Exit");

        // cand dau create, sparge peretii
        createBtn.addActionListener(e -> {
            frame.getCanvas().createMaze();
        });

        // dau reset, se sterge grila
        resetBtn.addActionListener(e -> {
            frame.getCanvas().resetGrid();
        });

        // gata programul
        exitBtn.addActionListener(e -> {
            System.exit(0);
        });

        add(createBtn);
        add(resetBtn);
        add(exitBtn);
    }
}

// un patratel din grila
class Cell {
    // la inceput are toti peretii
    boolean top = true;
    boolean right = true;
    boolean bottom = true;
    boolean left = true;
}

// unde se face magia cu desenul
class DrawingPanel extends JPanel {
    private int rows = 0;
    private int cols = 0;
    private Cell[][] grid;
    private Random rand = new Random();

    public DrawingPanel() {
        setBackground(Color.WHITE);
    }

    // initializeaza grila cu pereti
    public void initGrid(int size) {
        this.rows = size;
        this.cols = size;
        grid = new Cell[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Cell();
            }
        }
        repaint(); // desenam piesa
    }

    // spargem pereti
    public void createMaze() {
        if (grid == null) return;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // dau cu banu' sa vad daca scot peretele, sansa 40%
                if (rand.nextDouble() < 0.4) grid[i][j].top = false;
                if (rand.nextDouble() < 0.4) grid[i][j].right = false;
                if (rand.nextDouble() < 0.4) grid[i][j].bottom = false;
                if (rand.nextDouble() < 0.4) grid[i][j].left = false;
            }
        }
        repaint();
    }

    // curata tot
    public void resetGrid() {
        grid = null;
        repaint();
    }

    // functia de baza de la swing pt desenat linii
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (grid == null) return;

        // cat vine o celula impartit la spatiul ecranului
        int cellWidth = getWidth() / cols;
        int cellHeight = getHeight() / rows;

        g.setColor(Color.BLACK);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int x = j * cellWidth;
                int y = i * cellHeight;

                Cell c = grid[i][j];

                // tragem linia doar daca e pe true peretele
                if (c.top) g.drawLine(x, y, x + cellWidth, y);
                if (c.bottom) g.drawLine(x, y + cellHeight, x + cellWidth, y + cellHeight);
                if (c.left) g.drawLine(x, y, x, y + cellHeight);
                if (c.right) g.drawLine(x + cellWidth, y, x + cellWidth, y + cellHeight);
            }
        }
    }
}