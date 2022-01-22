package UserInterface;

import javax.swing.*;
import java.awt.*;

public class MainGUI {

    public JFrame frame;
    public JPanel content;

    public JToolBar toolbar_pnl = new JToolBar(JToolBar.TOP);
    public JButton open_btn = new JButton("Open");
    public JButton run_btn = new JButton("Run");
    public JButton save_btn = new JButton("Save");

    public JPanel script_pnl = new JPanel();
    static public JTextArea script_txt = new JTextArea();

    public MainGUI() {
        frame = new JFrame("M Lang Graphics");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add((content = new JPanel()));
        frame.setSize(new Dimension(600, 800));
        frame.setMaximumSize(new Dimension(600, 800));
        frame.setResizable(false);

        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        // toolbar_pnl.setla
        toolbar_pnl.setLayout(new BoxLayout(toolbar_pnl, BoxLayout.X_AXIS));
        toolbar_pnl.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        toolbar_pnl.add(open_btn, BorderLayout.PAGE_START);
        toolbar_pnl.add(save_btn, BorderLayout.PAGE_START);
        toolbar_pnl.add(run_btn, BorderLayout.PAGE_END);

        run_btn.addActionListener(new RunButtonListener());
        open_btn.addActionListener(new OpenButtonListener());
        save_btn.addActionListener(new SaveButtonListener());

        script_pnl.add(script_txt);
        // script_txt.setSize(600, 600);
        script_txt.setPreferredSize(new Dimension(400, 600));
        script_txt.setMargin(new Insets(10, 10, 10, 10));

        content.add(toolbar_pnl);
        content.add(script_pnl);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // center
    }
}
