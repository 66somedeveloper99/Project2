package UserInterface;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class MUtilities {
    // so it can't be instansiated
    private MUtilities() {}

    static public File GetFileFromUser() {
        JFileChooser fileChooser = new JFileChooser(".");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setDialogTitle("Select an M file");
        fileChooser.setMultiSelectionEnabled(false);

        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().endsWith("M");
            }

            @Override
            public String getDescription() {
                return "M file";
            }
        });

        int op = fileChooser.showOpenDialog(null);
        if (op == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }

        return null;
    }
}
