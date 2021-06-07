import javax.swing.*;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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

        System.out.println(path);

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
                    // set loading text
                    TreeExample.this.setTitle("JTree Example - Loading...");
                    updateTree(file, treeMod);
                    TreeExample.this.setTitle("JTree Example");

                    jtfPath.setText(path);
                }
            }
        });
        panelNorth.add(btnChooseFolder);
        add(panelNorth, BorderLayout.NORTH);

        //create the root node
        TreeExample.this.setTitle("JTree Example - Loading...");
        DefaultMutableTreeNode root = createRoot(new File(path));
        TreeExample.this.setTitle("JTree Example");

        //create the tree by passing in the root node
        treeMod = new DefaultTreeModel(root);
        tree = new JTree(treeMod);
        add(new JScrollPane(tree), BorderLayout.CENTER);

        tree.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent event) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                        event.getPath().getLastPathComponent();
                TreeData treeData = (TreeData) node.getUserObject();
                treeData.onSelected();
            }
        });

        tree.addTreeExpansionListener(new TreeExpansionListener() {
            @Override
            public void treeExpanded(TreeExpansionEvent event) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                        event.getPath().getLastPathComponent();
                System.out.println(node);
                TreeData treeData = (TreeData) node.getUserObject();
                treeData.onExpanded(node);
            }

            @Override
            public void treeCollapsed(TreeExpansionEvent event) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                        event.getPath().getLastPathComponent();
                TreeData treeData = (TreeData) node.getUserObject();
                treeData.onCollapsed(node);
            }
        });

        this.pack();
        this.setVisible(true);
    }

    public static void setLAF(){
        try {
            // Set System L&F
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (InstantiationException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static DefaultMutableTreeNode createRoot(File file){
        // create tree model
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(file.getName());
        root.setUserObject(new TreeData(file));
        if(file.isDirectory()){
            // get files
            File[] childrenFiles = file.listFiles();
            // build tree, go 2 levels deep
            ListFiles.buildTree(childrenFiles,root,2);
        }
        return root;
    }
    public static void updateTree(File file, DefaultTreeModel treeMod){
        DefaultMutableTreeNode root = createRoot(file);
        treeMod.setRoot(root);
        treeMod.reload();
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                setLAF();
                new TreeExample();
            }
        });
    }
}