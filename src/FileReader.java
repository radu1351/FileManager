import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.text.DecimalFormat;

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
                    selectedFIle.getPath(),
                    Float.parseFloat(new DecimalFormat("#.##").format((float) selectedFIle.length() / 1000000)));  // To convert bytes to megabytes
        } else return null;
    }
}
