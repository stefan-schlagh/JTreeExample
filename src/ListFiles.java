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
    public static ListEntry listFiles(File file){
        List<ListEntry> list = new LinkedList<>();

        if(file.isFile()){
            return new ListEntry(file.getName(),file.getAbsolutePath(),true,null);
        }else{
            File[] children = file.listFiles();
            for(File child:children){
                if(child.isFile())
                    list.add(new ListEntry(child.getName(),child.getAbsolutePath(),true));
                else
                    //list.add(new ListEntry(child.getName(),child.getAbsolutePath(),false,child.listFiles()));
                    list.add(listFiles(child));
            }
            return new ListEntry(file.getName(),file.getAbsolutePath(),false,list);
        }
    }
    public static void printList(ListEntry listEntry){

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
    public static void buildTree(ListEntry listEntry,DefaultMutableTreeNode node){

        if(listEntry.isFile)
            node.add(new DefaultMutableTreeNode(listEntry.name));
        else {
            //print item
            DefaultMutableTreeNode dirNode = new DefaultMutableTreeNode(listEntry.name);
            node.add(dirNode);
            //list children
            for (int i = 0; i < listEntry.children.size(); i++) {
                ListEntry item = listEntry.children.get(i);
                buildTree(item,dirNode);
            }
        }
    }
}
