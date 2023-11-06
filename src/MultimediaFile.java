import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MultimediaFile {
    private String name;
    private String format;
    private String path;
    private Float size; // size in megabytes
    private Date lastModified;
    private boolean isFavourite;

    public MultimediaFile(String denumire, String format, String path, Float size, Date lastModified) {
        this.name = denumire;
        this.format = format;
        this.path = path;
        this.size = size;
        this.lastModified = lastModified;
        isFavourite = false;
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

    public Float getSize() {
        return size;
    }

    public void setSize(Float size) {
        this.size = size;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String getDirectory() {
        Path path = FileSystems.getDefault().getPath(this.path);
        Path parent = path.getParent();
        return parent != null ? parent.toString() : null;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    @Override
    public String toString() {
        return this.name + " | " + this.format + " | " + this.path + " | " + size + "MB" + " | "
                + new SimpleDateFormat("dd MMM yyyy hh:mm:ss").format(lastModified);
    }
}
