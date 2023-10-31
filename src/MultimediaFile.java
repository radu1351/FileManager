import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class MultimediaFile {
    private String name;
    private String format;
    private String path;

    public MultimediaFile(String denumire, String format, String path) {
        this.name = denumire;
        this.format = format;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDirectory() {
        Path path = FileSystems.getDefault().getPath(this.path);
        Path parent = path.getParent();
        return parent != null ? parent.toString() : null;
    }

    @Override
    public String toString() {
        return "Denumire: " + this.name + " | Format: " + this.format + " | Locatie: " + this.path;
    }
}
