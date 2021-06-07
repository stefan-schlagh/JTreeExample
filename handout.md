# JTree

mit JTree kann eine Baumansicht erstellt werden. Jeder Zweig kann zu und aufgeklappt werden. 

JTree erstellen:
````java
// treeModel erzeugen
treeMod = new DefaultTreeModel(root);
tree = new JTree(treeMod);
// Wurzel - Knoten
DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");
// Wurzel setzen
treeMod.setRoot(root);
````

Ein SelectionListener wird dann aktiviert, wenn ein Knoten ausgewählt wird.

````java
tree.addTreeSelectionListener(new TreeSelectionListener() {
    @Override
    public void valueChanged(TreeSelectionEvent event) {
        // der ausgewählte Knoten
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                event.getPath().getLastPathComponent();
    }
});
````

Ein ExpansionListener wird dann aktiviert, wenn ein Knoten zu oder aufgeklappt wird.

````java
tree.addTreeExpansionListener(new TreeExpansionListener() {
    @Override
    public void treeExpanded(TreeExpansionEvent event) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                event.getPath().getLastPathComponent();
        System.out.println(node);
    }

    @Override
    public void treeCollapsed(TreeExpansionEvent event) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                event.getPath().getLastPathComponent();
});
````