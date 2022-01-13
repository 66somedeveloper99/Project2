package MShape.Drawing;

import java.awt.*;
import javax.swing.*;

import MShape.MLang.Commands.StyleCommand;

/**
 * This class draws on screen
 */
public class Drawer extends JPanel {


    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;

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

    }

    public void Up() {

    }

    public void Move(int x, int y) {

    }

    public void SetStyle(Style style) {

    }

    public void SetColor(Color color) {
        
    }
    public void SetSize(int size) {

    }


}
