import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;

public class ListFiles {

    public static void buildTree(File file,DefaultMutableTreeNode parent){
        // if file, children is null
        if(file.isFile()){
            System.out.println(file.getName());
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(file.getName());
            node.setUserObject(new TreeData(file));
            parent.add(node);
        }else{
            DefaultMutableTreeNode dirNode = new DefaultMutableTreeNode(file.getName());
            dirNode.setUserObject(new TreeData(file));
            parent.add(dirNode);
            // get files
            File[] childrenFiles = file.listFiles();
            if(childrenFiles != null) {
                // loop over files
                for (File child : childrenFiles) {
                    buildTree(child,dirNode);
                }
            }
        }
    }
}
