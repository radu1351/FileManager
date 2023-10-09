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

    @Override
    public String toString() {
        return this.name + " " + this.format + " " + this.path;
    }
}
