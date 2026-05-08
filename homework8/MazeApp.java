package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import javax.imageio.ImageIO;

// clasa principala
public class MazeApp extends JFrame {

    private ConfigPanel configPanel;
    private ControlPanel controlPanel;
    private DrawingPanel canvas;

    public MazeApp() {
        super("labirint homework");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        canvas = new DrawingPanel();
        configPanel = new ConfigPanel(this);
        controlPanel = new ControlPanel(this);

        add(configPanel, BorderLayout.NORTH);
        add(canvas, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        // am facut fereastra putin mai mare ca avem multe butoane jos acum
        setSize(700, 700);
        setLocationRelativeTo(null);
    }

    public DrawingPanel getCanvas() {
        return canvas;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MazeApp().setVisible(true);
        });
    }
}

// panoul de sus ramane la fel
class ConfigPanel extends JPanel {
    private MazeApp frame;
    private JLabel label;
    private JSpinner sizeSpinner;
    private JButton drawBtn;

    public ConfigPanel(MazeApp frame) {
        this.frame = frame;
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        label = new JLabel("dimensiune:");
        SpinnerNumberModel model = new SpinnerNumberModel(10, 2, 100, 1);
        sizeSpinner = new JSpinner(model);
        drawBtn = new JButton("deseneaza grila");

        drawBtn.addActionListener(e -> {
            int size = (Integer) sizeSpinner.getValue();
            frame.getCanvas().initGrid(size);
        });

        add(label);
        add(sizeSpinner);
        add(drawBtn);
    }
}

// panoul de jos aici am bagat toata artileria grea ceruta de prof
class ControlPanel extends JPanel {
    private MazeApp frame;

    public ControlPanel(MazeApp frame) {
        this.frame = frame;
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // punem butoanele pe 2 randuri ca sunt multe
        setLayout(new GridLayout(2, 4, 5, 5));

        JButton createBtn = new JButton("Create");
        JButton resetBtn = new JButton("Reset");
        JButton exitBtn = new JButton("Exit");
        JButton validateBtn = new JButton("Validate");
        JButton exportBtn = new JButton("Save PNG");
        JButton saveBtn = new JButton("Save Data");
        JButton loadBtn = new JButton("Load Data");

        createBtn.addActionListener(e -> frame.getCanvas().createMaze());
        resetBtn.addActionListener(e -> frame.getCanvas().resetGrid());
        exitBtn.addActionListener(e -> System.exit(0));

        // cheama algoritmul care verifica daca are iesire
        validateBtn.addActionListener(e -> frame.getCanvas().validateMaze());

        // face poza la labirint
        exportBtn.addActionListener(e -> frame.getCanvas().exportPNG());

        // serializeaza obiectul si il salveaza
        saveBtn.addActionListener(e -> frame.getCanvas().saveMaze());

        // incarca obiectul la loc
        loadBtn.addActionListener(e -> frame.getCanvas().loadMaze());

        add(createBtn);
        add(validateBtn);
        add(exportBtn);
        add(saveBtn);
        add(loadBtn);
        add(resetBtn);
        add(exitBtn);
    }
}

//  clasa cell
class Cell implements Serializable {
    private static final long serialVersionUID = 1L; // ca sa nu dea figuri la salvare
    boolean top = true;
    boolean right = true;
    boolean bottom = true;
    boolean left = true;
}

// panoul in sine
class DrawingPanel extends JPanel {
    private int rows = 0;
    private int cols = 0;
    private Cell[][] grid;
    private Random rand = new Random();

    public DrawingPanel() {
        setBackground(Color.WHITE);

        //  dam click pe perete si dispare sau apare
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (grid == null) return;

                int cellW = getWidth() / cols;
                int cellH = getHeight() / rows;

                // aflam in ce patratel am dat click
                int j = e.getX() / cellW;
                int i = e.getY() / cellH;

                // ca sa nu crape daca dam click putin pe langa
                if (i >= rows || j >= cols) return;

                // calculam distanta de la mouse pana la cei 4 pereti ai celulei
                int cx = e.getX() - (j * cellW);
                int cy = e.getY() - (i * cellH);

                int distTop = cy;
                int distBottom = cellH - cy;
                int distLeft = cx;
                int distRight = cellW - cx;

                // aflam care perete e cel mai aproape de click-ul nostru
                int min = Math.min(Math.min(distTop, distBottom), Math.min(distLeft, distRight));

                Cell c = grid[i][j];

                // schimbam valoarea (daca e true se face false, si invers)
                if (min == distTop) c.top = !c.top;
                else if (min == distBottom) c.bottom = !c.bottom;
                else if (min == distLeft) c.left = !c.left;
                else if (min == distRight) c.right = !c.right;

                repaint(); // dam refresh pe ecran
            }
        });
    }

    public void initGrid(int size) {
        this.rows = size;
        this.cols = size;
        grid = new Cell[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = new Cell();
            }
        }
        repaint();
    }

    public void createMaze() {
        if (grid == null) return;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (rand.nextDouble() < 0.4) grid[i][j].top = false;
                if (rand.nextDouble() < 0.4) grid[i][j].right = false;
                if (rand.nextDouble() < 0.4) grid[i][j].bottom = false;
                if (rand.nextDouble() < 0.4) grid[i][j].left = false;
            }
        }
        repaint();
    }

    public void resetGrid() {
        grid = null;
        repaint();
    }

    //  validare drum de la start sus-stanga la finis jos-dreapta  algoritm bfs
    public void validateMaze() {
        if (grid == null) return;

        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{0, 0}); // plecam din coltul stanga sus
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int r = curr[0];
            int c = curr[1];

            // am ajuns la final
            if (r == rows - 1 && c == cols - 1) {
                JOptionPane.showMessageDialog(this, "yey! exista drum pana la final.");
                return;
            }

            // verificam daca putem merge in sus (nu tre sa fie perete nici la celula noastra, nici la aia de sus)
            if (r > 0 && !grid[r][c].top && !grid[r - 1][c].bottom && !visited[r - 1][c]) {
                visited[r - 1][c] = true; queue.add(new int[]{r - 1, c});
            }
            // in jos
            if (r < rows - 1 && !grid[r][c].bottom && !grid[r + 1][c].top && !visited[r + 1][c]) {
                visited[r + 1][c] = true; queue.add(new int[]{r + 1, c});
            }
            // la stanga
            if (c > 0 && !grid[r][c].left && !grid[r][c - 1].right && !visited[r][c - 1]) {
                visited[r][c - 1] = true; queue.add(new int[]{r, c - 1});
            }
            // la dreapta
            if (c < cols - 1 && !grid[r][c].right && !grid[r][c + 1].left && !visited[r][c + 1]) {
                visited[r][c + 1] = true; queue.add(new int[]{r, c + 1});
            }
        }

        // daca am terminat si nu am dat de return, e nasol
        JOptionPane.showMessageDialog(this, "ghinion. esti blocat, labirintul nu are iesire.");
    }

    //  export poza in format png
    public void exportPNG() {
        if (grid == null) return;
        try {
            // facem o imagine goala cat e panoul nostru
            BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = img.createGraphics();
            paint(g2d); // pictam panoul pe imagine
            g2d.dispose();

            // salvam pe disk
            ImageIO.write(img, "png", new File("labirint_meu.png"));
            JOptionPane.showMessageDialog(this, "poza a fost salvata ca labirint_meu.png in proiect");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "eroare la salvarea pozei");
        }
    }

    //  salvare labirint cu serializare (scriem tot array-ul in fisier)
    public void saveMaze() {
        if (grid == null) return;
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("maze.dat"))) {
            oos.writeObject(grid);
            JOptionPane.showMessageDialog(this, "starea a fost salvata in maze.dat");
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(this, "nu a mers salvarea datelor");
        }
    }

    // cerinta 4: incarcare labirint serializat inapoi in aplicatie
    public void loadMaze() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("maze.dat"))) {
            grid = (Cell[][]) ois.readObject();
            rows = grid.length;
            cols = grid[0].length;
            repaint();
            JOptionPane.showMessageDialog(this, "starea a fost incarcata cu succes");
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(this, "nu s-a gasit fisierul maze.dat sau e stricat");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (grid == null) return;

        int cellWidth = getWidth() / cols;
        int cellHeight = getHeight() / rows;

        // coloram patratul de start cu verde si finalul cu rosu ca sa stim de unde plecam
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, cellWidth, cellHeight);

        g.setColor(Color.RED);
        g.fillRect((cols - 1) * cellWidth, (rows - 1) * cellHeight, cellWidth, cellHeight);

        g.setColor(Color.BLACK); // culoarea pt pereti

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int x = j * cellWidth;
                int y = i * cellHeight;

                Cell c = grid[i][j];

                if (c.top) g.drawLine(x, y, x + cellWidth, y);
                if (c.bottom) g.drawLine(x, y + cellHeight, x + cellWidth, y + cellHeight);
                if (c.left) g.drawLine(x, y, x, y + cellHeight);
                if (c.right) g.drawLine(x + cellWidth, y, x + cellWidth, y + cellHeight);
            }
        }
    }
}