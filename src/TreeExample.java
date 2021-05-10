import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;

//src: https://www.codejava.net/java-se/swing/jtree-basic-tutorial-and-examples
public class TreeExample extends JFrame
{
    private JTree tree;
    public TreeExample()
    {
        String path = "D:\\Google Drive\\HTL\\Schuljahr 2021";
        File file = new File(path);

        //create the root node
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(file.getName());

        if(file.isDirectory()){
            ListEntry listEntry1 = ListFiles.listFiles(file);
            ListFiles.buildTree(listEntry1,root);
        }
        //create the child nodes
        /*DefaultMutableTreeNode vegetableNode = new DefaultMutableTreeNode("Vegetables");
        vegetableNode.add(new DefaultMutableTreeNode("Capsicum"));
        vegetableNode.add(new DefaultMutableTreeNode("Carrot"));
        vegetableNode.add(new DefaultMutableTreeNode("Tomato"));
        vegetableNode.add(new DefaultMutableTreeNode("Potato"));
        DefaultMutableTreeNode fruitNode = new DefaultMutableTreeNode("Fruits");
        fruitNode.add(new DefaultMutableTreeNode("Banana"));
        fruitNode.add(new DefaultMutableTreeNode("Mango"));
        fruitNode.add(new DefaultMutableTreeNode("Apple"));
        fruitNode.add(new DefaultMutableTreeNode("Grapes"));
        fruitNode.add(new DefaultMutableTreeNode("Orange"));

        //add the child nodes to the root node
        root.add(vegetableNode);
        root.add(fruitNode);*/

        //create the tree by passing in the root node
        tree = new JTree(root);
        add(new JScrollPane(tree));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("JTree Example");
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TreeExample();
            }
        });
    }
}