package MShape.Drawing;

import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 * This class draws on screen
 */
public class Drawer extends JPanel {

    public boolean pen = true;
    public int X_1, Y_1, X_2, Y_2;
    public Style format = Style.Solid;
    public Color color = Color.black;
    public int Size = 1;

    class Phase {
        public BasicStroke stroke;
        public Color color;
        public int X_1, Y_1, X_2, Y_2;

        public Phase(BasicStroke stroke, Color color, int x_1, int y_1, int x_2, int y_2) {
            this.stroke = stroke;
            this.color = color;
            X_1 = x_1;
            Y_1 = y_1;
            X_2 = x_2;
            Y_2 = y_2;
        }
    }

    ArrayList<Phase> phases = new ArrayList<Phase>();

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        // g2d.setPaint(color);
        // BasicStroke drawingstroke = new BasicStroke(Size);
        // switch (format) {
        // case Solid:
        // break;
        // case Dashed:
        // drawingstroke = new BasicStroke(Size,
        // BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 9 }, 0);
        // break;
        // case Dotted:
        // drawingstroke = new BasicStroke(Size,
        // BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 2 }, 0);
        // break;
        // }
        // g2d.setStroke(drawingstroke);
        // g2d.drawLine(X_1, Y_1, X_2, Y_2);
        if (phases == null || phases.size() == 0)
            return;
        for (Phase phase : phases) {
            g2d.setPaint(phase.color);
            g2d.setStroke(phase.stroke);
            g2d.drawLine(phase.X_1, phase.Y_1, phase.X_2, phase.Y_2);
        }
    }

    public Drawer() {
        super();
        JFrame frame = new JFrame("Paint");
        frame.add(this);
        frame.setVisible(true);
        frame.setSize(new Dimension(400, 400));
    }

    public enum Style {
        Solid,
        Dashed,
        Dotted
    }

    public void Down() {
        this.pen = true;
    }

    public void Up() {
        this.pen = false;

    }

    public void Move(int x, int y) {
        X_1 = X_2;
        Y_1 = Y_2;
        X_2 = x;
        Y_2 = y;
        if (pen) {

            // add draw
            BasicStroke drawingstroke = new BasicStroke(Size);
            switch (format) {
                case Solid:
                    break;
                case Dashed:
                    drawingstroke = new BasicStroke(Size,
                            BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 9 }, 0);
                    break;
                case Dotted:
                    drawingstroke = new BasicStroke(Size,
                            BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] { 2 }, 0);
                    break;
            }
            phases.add(new Phase(drawingstroke, color, X_1, Y_1, X_2, Y_2));

            // repaint it
            this.repaint();
            this.revalidate();
        }
    }

    public void SetStyle(Style style) {
        format = style;
    }

    public void SetColor(Color c) {
        this.color = c;
    }

    public void SetSize(int size) {
        this.Size = size;
    }

}
