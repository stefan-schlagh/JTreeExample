import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class ListFiles {

    public static void main(String[] args) {

        String path = "D:\\Google Drive\\HTL\\Schuljahr 2021";
        File file = new File(path);

        if(file.isDirectory()){
            ListEntry listEntry1 = listFiles(file);
            printList(listEntry1);

        }
    }
    public static List<ListEntry> listFiles(String path){
        File file = new File(path);

        if(file.isFile()){
            return null;
        }else{
            // children of list entry
            List<ListEntry> children = new LinkedList<>();
            // get files
            File[] childrenFiles = file.listFiles();
            if(childrenFiles != null) {
                // loop over files
                for (File child : childrenFiles) {
                    //list.add(new ListEntry(child.getName(),child.getAbsolutePath(),false,child.listFiles()));
                    children.add(listFiles(child));
                }
            }
            return children;
        }
    }
    // create file tree
    public static ListEntry listFiles(File file){
        // if file, children is null
        if(file.isFile()){
            return new ListEntry(file.getName(),file.getAbsolutePath(),true,null);
        }else{
            // children of list entry
            List<ListEntry> children = new LinkedList<>();
            // get files
            File[] childrenFiles = file.listFiles();
            if(childrenFiles != null) {
                // loop over files
                for (File child : childrenFiles) {
                    //list.add(new ListEntry(child.getName(),child.getAbsolutePath(),false,child.listFiles()));
                    children.add(listFiles(child));
                }
            }
            return new ListEntry(file.getName(),file.getAbsolutePath(),false,children);
        }
    }
    // print tree
    public static void printList(ListEntry listEntry){
        // if directory, loop over children
        if(listEntry.isFile)
            System.out.println(listEntry.name);
        else {
            //print item
            System.out.println("/" + listEntry.name);
            //list children
            for (int i = 0; i < listEntry.children.size(); i++) {
                ListEntry item = listEntry.children.get(i);
                printList(item);
            }
        }
    }
    // build tree view
    public static void buildTree(ListEntry listEntry,DefaultMutableTreeNode parent){
        // if directory, loop over children
        if(listEntry.isFile)
            parent.add(new DefaultMutableTreeNode(listEntry.name));
        else {
            //print item
            DefaultMutableTreeNode dirNode = new DefaultMutableTreeNode(listEntry.name);
            parent.add(dirNode);
            //list children
            for (int i = 0; i < listEntry.children.size(); i++) {
                ListEntry item = listEntry.children.get(i);
                buildTree(item,dirNode);
            }
        }
    }
}
