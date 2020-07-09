package tree;

public class AVLTree implements Tree {
    private AVLTreeNode root;


    public AVLTree(int key) {
        this.root = new AVLTreeNode(key, null);
    }


    @Override
    public void insert(int newKey) {
        if (root != null) {
            root.insert(newKey);
        }
    }

    @Override
    public boolean search(int key) {
        return root != null && root.search(key);
    }

    @Override
    public void remove(int key) {
        if (root != null) {
            root.removeNode(root, key);
        }
    }


    private final class AVLTreeNode {
        private int key;
        private int height;
        private AVLTreeNode parent;
        private AVLTreeNode left;
        private AVLTreeNode right;


        public AVLTreeNode(int rootKey, AVLTreeNode parent) {
            this.key = rootKey;
            this.parent = parent;
        }


        public void insert(int newKey) {
            if (newKey < key) {
                if (left != null) {
                    left.insert(newKey);
                } else {
                    left = new AVLTreeNode(newKey, this);
                }
            } else {
                if (right != null) {
                    right.insert(newKey);
                } else {
                    right = new AVLTreeNode(newKey, this);
                }
            }
            recalcHeight();
            balance();
        }


        public boolean search(int key) {
            return searchNode(key) != null;
        }


        private AVLTreeNode searchNode(int key) {
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


        private int getHeight(AVLTreeNode node) {
            return node == null ? 0 : node.height;
        }


        public int getBalance() {
            return getHeight(left) - getHeight(right);
        }


        public void recalcHeight() {
            height = Math.max(getHeight(left), getHeight(right)) + 1;
        }


        private void balance() {
            recalcHeight();

            if (this.getBalance() >= 2) {
                if (this.left.getBalance() < 0) {
                    smallLeftRotation(this.left);
                }
                smallRightRotation(this);
            }

            if (this.getBalance() <= -2) {
                if (this.right.getBalance() > 0) {
                    smallRightRotation(this.right);
                }
                smallLeftRotation(this);
            }
        }

        private void smallRightRotation(AVLTreeNode node) {
            AVLTreeNode b = node.left;
            AVLTreeNode c = b.right;
            node.left = c;
            b.right = node;

            changeChildInParent(node, b);

            b.parent = node.parent;
            node.parent = b;
            if (c != null) {
                c.parent = node;
            }

            node.recalcHeight();
            b.recalcHeight();
        }


        private void smallLeftRotation(AVLTreeNode node) {
            AVLTreeNode a = node.right;
            AVLTreeNode c = a.left;
            node.right = c;
            a.left = node;

            changeChildInParent(node, a);

            a.parent = node.parent;
            node.parent = a;
            if (c != null) {
                c.parent = node;
            }

            node.recalcHeight();
            a.recalcHeight();
        }


        private AVLTreeNode findMin() {
            return left != null ? left.findMin() : this;
        }


        private AVLTreeNode removeMin(AVLTreeNode node) {
            if (node.left == null) {
                return node.right;
            }

            node.left = removeMin(node.left);
            return node;
        }


        private AVLTreeNode removeNode(AVLTreeNode node, int key) {
            if (node == null) {
                return null;
            }

            if (key < node.key) {
                node.left = removeNode(node.left, key);
            } else if (key > node.key) {
                node.right = removeNode(node.right, key);
            } else {
                AVLTreeNode leftChild = node.left;
                AVLTreeNode rightChild = node.right;

                if (rightChild == null) {
                    return leftChild;
                }

                AVLTreeNode minKeyNode = rightChild.findMin();
                minKeyNode.right = removeMin(rightChild);
                minKeyNode.left = leftChild;
                minKeyNode.balance();
                return minKeyNode;
            }
            node.balance();
            return node;
        }


        private void changeChildInParent(AVLTreeNode oldChild, AVLTreeNode newChild) {
            if (oldChild.parent != null) {
                if (oldChild.parent.left == oldChild) {
                    oldChild.parent.left = newChild;
                }
                if (oldChild.parent.right == oldChild) {
                    oldChild.parent.right = newChild;
                }
            } else {
                AVLTree.this.root = newChild;
            }
        }
    }
}