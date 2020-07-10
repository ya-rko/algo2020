package tree;


public class SplayTree implements Tree {
    SplayTreeNode root;


    public SplayTree(int key) {
        root = new SplayTreeNode(key, null);
    }

    @Override
    public void insert(int newKey) {
        if (root != null) {
            root.insert(newKey);
        }
    }

    @Override
    public boolean search(int key) {
        return root != null && root.searchNode(key) != null;
    }

    @Override
    public void remove(int key) {
        if (root != null) {
            root.removeNode(key);
        }
    }


    private final class SplayTreeNode {
        private int key;
        private SplayTreeNode parent;
        private SplayTreeNode left;
        private SplayTreeNode right;


        public SplayTreeNode(int rootKey, SplayTreeNode parent) {
            this.key = rootKey;
            this.parent = parent;
        }


        private SplayTreeNode parent(SplayTreeNode node) {
            return node.parent;
        }


        private SplayTreeNode grandparent(SplayTreeNode node) {
            return parent(node) != null ? parent(node.parent) : null;
        }


        private void splay(SplayTreeNode node) {
            while (parent(node) != null) {
                if (node == parent(node).left) {
                    if (grandparent(node) == null) {
                        smallRightRotation(parent(node));
                    } else if (parent(node) == grandparent(node).left) {
                        smallRightRotation(grandparent(node));
                        smallRightRotation(parent(node));
                    } else {
                        smallRightRotation(parent(node));
                        smallLeftRotation(parent(node));
                    }
                } else {
                    if (grandparent(node) == null) {
                        smallLeftRotation(parent(node));
                    } else if (parent(node) == grandparent(node).right) {
                        smallLeftRotation(grandparent(node));
                        smallLeftRotation(parent(node));
                    } else {
                        smallLeftRotation(parent(node));
                        smallRightRotation(parent(node));
                    }
                }
            }
        }


        private void smallRightRotation(SplayTreeNode node) {
            SplayTreeNode b = node.left;
            if (b == null) {
                return;
            }

            SplayTreeNode c = b.right;
            node.left = c;
            b.right = node;

            changeChildInParent(node, b);
            b.parent = node.parent;
            node.parent = b;
            if (c != null) {
                c.parent = node;
            }
        }


        private void smallLeftRotation(SplayTreeNode node) {
            SplayTreeNode a = node.right;
            if (a == null) {
                return;
            }

            SplayTreeNode c = a.left;
            node.right = c;
            a.left = node;

            changeChildInParent(node, a);
            a.parent = node.parent;
            node.parent = a;
            if (c != null) {
                c.parent = node;
            }
        }


        private void changeChildInParent(SplayTreeNode oldChild, SplayTreeNode newChild) {
            if (oldChild.parent != null) {
                if (oldChild.parent.left == oldChild) {
                    oldChild.parent.left = newChild;
                }
                if (oldChild.parent.right == oldChild) {
                    oldChild.parent.right = newChild;
                }
            } else {
                SplayTree.this.root = newChild;
            }
        }


        public void insert(int newKey) {
            if (newKey < key) {
                if (left != null) {
                    left.insert(newKey);
                } else {
                    left = new SplayTreeNode(newKey, this);
                    splay(left);
                }
            } else {
                if (right != null) {
                    right.insert(newKey);
                } else {
                    right = new SplayTreeNode(newKey, this);
                    splay(right);
                }
            }
        }


        private SplayTreeNode searchNode(int key) {
            if (key == this.key) {
                splay(this);
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


        private SplayTreeNode findMin() {
            return left != null ? left.findMin() : this;
        }


        private SplayTreeNode merge(SplayTreeNode left, SplayTreeNode right) {
            if (left == null) {
                return right;
            }
            if (right == null) {
                return left;
            }
            SplayTreeNode minNode = right.findMin();
            if (minNode != null) {
                splay(minNode);
                minNode.left = left;
                left.parent = minNode;
            }

            return minNode;
        }

        private void removeNode(int key) {
            SplayTreeNode root = searchNode(key);
            if (root == null) {
                return;
            }

            if (root.left != null) {
                root.left.parent = null;
            }
            if (root.right != null) {
                root.right.parent = null;
            }

            merge(root.left, root.right);
        }
    }
}