import java.util.List;

class ListEntry {

    String name;
    String absolutePath;
    boolean isFile;
    List<ListEntry> children;

    public ListEntry(String name, String absolutePath, boolean isFile) {
        this.name = name;
        this.absolutePath = absolutePath;
        this.isFile = isFile;
    }

    public ListEntry(String name, String absolutePath, boolean isFile, List<ListEntry> children) {
        this.name = name;
        this.absolutePath = absolutePath;
        this.isFile = isFile;
        this.children = children;
    }
}
