import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

//src: https://www.codejava.net/java-se/swing/jtree-basic-tutorial-and-examples
public class TreeExample extends JFrame
{
    private JTree tree;
    private DefaultTreeModel treeMod;
    private JTextField jtfPath;
    private JFileChooser jfcPath;
    private JButton btnChooseFolder;
    private String path = System.getProperty("user.home") + File.separator + "Desktop";  //Homedirectory as default
    public TreeExample()
    {

        System.out.println(System.getProperty("user.home"));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setTitle("JTree Example");

        JPanel panelNorth = new JPanel(new FlowLayout());
        //showing current selected dir
        jtfPath = new JTextField(path);
        jtfPath.setPreferredSize(new Dimension(200, 20));
        panelNorth.add(jtfPath);

        //button to choose dir with JFC
        btnChooseFolder = new JButton("choose");
        btnChooseFolder.setPreferredSize(new Dimension(100, 20));
        btnChooseFolder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jfcPath = new JFileChooser();
                jfcPath.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int res = jfcPath.showOpenDialog(null);

                if(res != JFileChooser.CANCEL_OPTION && res != JFileChooser.ERROR_OPTION){
                    //Not canceled or error
                    path = jfcPath.getSelectedFile().getAbsolutePath(); //Get Folderpath user choosed

                    File file = new File(path);

                    //create the root node
                    DefaultMutableTreeNode root2 = new DefaultMutableTreeNode(file.getName());
                    if(file.isDirectory()){
                        List<ListEntry> listEntries = ListFiles.listFiles(path);

                        for (int i = 0; i < listEntries.size(); i++) {
                            ListFiles.buildTree(listEntries.get(i),root2);
                        }
                    }

                    treeMod.setRoot(root2);
                    treeMod.reload();
                }
            }
        });
        panelNorth.add(btnChooseFolder);
        add(panelNorth, BorderLayout.NORTH);

        File file = new File(path);

        //create the root node
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(file.getName());
        if(file.isDirectory()){
            List<ListEntry> listEntries = ListFiles.listFiles(path);

            for (int i = 0; i < listEntries.size(); i++) {
                ListFiles.buildTree(listEntries.get(i),root);
            }
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
        treeMod = new DefaultTreeModel(root);
        tree = new JTree(treeMod);
        add(new JScrollPane(tree), BorderLayout.CENTER);


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