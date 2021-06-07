import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;

public class ListFiles {

    public static void buildTree(File file,DefaultMutableTreeNode parent,int i){
        if(i > 0) {
            // if file, children is null
            if (file.isFile()) {
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(file.getName());
                node.setUserObject(new TreeData(file));
                parent.add(node);
            } else {
                DefaultMutableTreeNode dirNode = new DefaultMutableTreeNode(file.getName());
                dirNode.setUserObject(new TreeData(file));
                parent.add(dirNode);
                // get files
                File[] childrenFiles = file.listFiles();
                if (childrenFiles != null) {
                    // loop over files
                    for (File child : childrenFiles) {
                        buildTree(child, dirNode, i-1);
                    }
                }
            }
            // set loaded to true
            TreeData treeData = (TreeData) parent.getUserObject();
            treeData.setLoaded(true);
        } else {
            TreeData treeData = (TreeData) parent.getUserObject();
            treeData.setLoaded(false);
        }
    }

    public static void buildTree(File[] files,DefaultMutableTreeNode parent,int i){
        if(files != null) {
            // loop over files
            for (File child : files) {
                // build tree, go 2 levels deep
                ListFiles.buildTree(child,parent,i);
            }
        }
    }
}
