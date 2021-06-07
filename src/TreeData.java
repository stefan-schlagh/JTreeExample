import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;

public class TreeData {

    private File file;
    private boolean loaded;

    public TreeData(File file) {
        this.file = file;
        this.loaded = true;
    }

    public File getFile() {
        return file;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void onSelected() {
        System.out.println(file.getName());
    }

    public void onExpanded(DefaultMutableTreeNode node) {
        // loop children of node
        for(int i = 0;i < node.getChildCount();i ++){
            DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) node.getChildAt(i);
            TreeData treeData = (TreeData) childNode.getUserObject();
            // if child is not loaded, load
            if(!treeData.isLoaded()) {
                ListFiles.buildTree(treeData.getFile().listFiles(),childNode,1);
            }
        }
    }

    public void onCollapsed(DefaultMutableTreeNode node) {
        // loop children of node
        for(int i = 0;i < node.getChildCount();i ++){
            DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) node.getChildAt(i);
            TreeData treeData = (TreeData) childNode.getUserObject();

            if(childNode.getChildCount() > 0){
                childNode.removeAllChildren();
                treeData.setLoaded(false);
            }
        }
    }

    @Override
    public String toString() {
        return file.getName();
    }
}
