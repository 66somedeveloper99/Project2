package MShape.Drawing;

import java.awt.*;
import javax.swing.*;

/**
 * This class draws on screen
 */
public class Drawer extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        this.revalidate();
    }

    public Drawer() {
        super();
        JFrame frame = new JFrame("title");
        frame.add(this);
        frame.setVisible(true);
        frame.setSize(new Dimension(400, 300));
    }

    public enum Style {
        Solid,
        Dashed,
        Dotted
    }

    public void Down() {
        System.out.println("down");
    }

    public void Up() {
        System.out.println("up");

    }

    public void Move(int x, int y) {
        System.out.println("move to " + x + ", " + y);

    }

    public void SetStyle(Style style) {
        System.out.println("style : " + style.toString());

    }

    public void SetColor(Color color) {
        System.out.println("col to " + color);

    }

    public void SetSize(int size) {
        System.out.println("size to " + size);

    }

}
