import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FileReader {

    public static MultimediaFile selectFile() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Multimedia Files", "jpg", "png", "mp3", "wav");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFIle = chooser.getSelectedFile();
            return new MultimediaFile(
                    selectedFIle.getName(),
                    selectedFIle.getAbsolutePath().substring(selectedFIle.getAbsolutePath().length() - 3),
                    selectedFIle.getPath());
        } else return null;
    }
}
