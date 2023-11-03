import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Date;

public class FileReader {

    public static MultimediaFile selectFile() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Multimedia Files", "jpg", "png", "mp3", "wav");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();
            return new MultimediaFile(
                    selectedFile.getName(),
                    selectedFile.getAbsolutePath().substring(selectedFile.getAbsolutePath().length() - 3),
                    selectedFile.getPath(),
                    Float.parseFloat(new DecimalFormat("#.##").format((float) selectedFile.length() / 1000000)),
                    new Date(selectedFile.lastModified()));  // To convert bytes to megabytes
        } else return null;
    }
}
