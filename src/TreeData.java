import java.io.File;

public class TreeData {

    private File file;

    public TreeData(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void onSelected() {
        System.out.println(this.file.getAbsolutePath());
    }

    @Override
    public String toString() {
        return file.getName();
    }
}
