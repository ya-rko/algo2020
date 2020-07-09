package tree;


public class BTree implements Tree {
    private int key;
    private BTree parent;
    private BTree left;
    private BTree right;


    public BTree(int rootKey, BTree parent) {
        this.key = rootKey;
        this.parent = parent;
    }

    @Override
    public void insert(int newKey) {
        if (newKey < key) {
            if (left != null) {
                left.insert(newKey);
            } else {
                left = new BTree(newKey, this);
            }
        } else {
            if (right != null) {
                right.insert(newKey);
            } else {
                right = new BTree(newKey, this);
            }
        }
    }


    public void insertTree(BTree newTree) {
        if (newTree.key < key) {
            if (left != null) {
                left.insertTree(newTree);
            } else {
                left = newTree;
                newTree.parent = this;
            }
        } else {
            if (right != null) {
                right.insertTree(newTree);
            } else {
                right = newTree;
                newTree.parent = this;
            }
        }
    }

    @Override
    public void remove(int key) {
        BTree node = searchNode(key);

        if (node != null) {
            removeNode(node);
        }
    }

    @Override
    public boolean search(int key) {
        return searchNode(key) != null;
    }


    private BTree searchNode(int key) {
        if (key == this.key) {
            return this;
        }

        if (key < this.key) {
            if (left == null) {
                return null;
            }
            return left.searchNode(key);
        } else {
            if (right == null) {
                return null;
            }
            return right.searchNode(key);
        }
    }


    private void removeNode(BTree node) {
        if (node.parent != null) {
            if (node.parent.left == node) {
                node.parent.left = null;
            }
            if (node.parent.right == node) {
                node.parent.right = null;
            }

            if (node.left != null) {
                node.parent.insertTree(node.left);
            }

            if (node.right != null) {
                node.parent.insertTree(node.right);
            }
        } else {
            BTree root = node;
            BTree leftChild = root.left;
            BTree rightChild = root.right;

            if (leftChild != null) {
                root.key = leftChild.key;
                root.left = leftChild.left;
                root.right = leftChild.right;

                if(leftChild.left != null) {
                    leftChild.left.parent = root;
                }

                if (rightChild != null) {
                    leftChild.right.parent = root;
                }
            } else {
                if (rightChild != null) {
                    root.key = rightChild.key;
                    root.left = rightChild.left;
                    root.right = rightChild.right;
                }
            }

            if (rightChild != null) {
                rightChild.parent = null;
                root.insertTree(rightChild);
            }
        }
    }
}
