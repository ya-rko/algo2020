package tree;

import java.util.Random;


public class RandomTree implements Tree {
    RandomTreeNode root;

    public RandomTree(int key) {
        root = new RandomTreeNode(key, null);
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

    private final class RandomTreeNode {
        private int key;
        private int height;
        private RandomTreeNode parent;
        private RandomTreeNode left;
        private RandomTreeNode right;


        public RandomTreeNode(int rootKey, RandomTreeNode parent) {
            this.key = rootKey;
            this.parent = parent;
        }


        private int getHeight(RandomTreeNode node) {
            return node == null ? 0 : node.height;
        }


        public void recalcHeight() {
            height = getHeight(left) + getHeight(right) + 1;
        }

        public void insert(int newKey) {
            int random = new Random().nextInt(Integer.MAX_VALUE);
            if ((random % (getHeight(this) + 1)) == 0) {
                insertRoot(newKey);
            } else if (newKey < key) {
                if (left != null) {
                    left.insert(newKey);
                } else {
                    left = new RandomTreeNode(newKey, this);
                }
            } else {
                if (right != null) {
                    right.insert(newKey);
                } else {
                    right = new RandomTreeNode(newKey, this);
                }
            }
            recalcHeight();
        }


        private void insertRoot(int newKey) {
            if (key == newKey) {
                return;
            }
            if (newKey < key) {
                if (left != null) {
                    left.insertRoot(newKey);
                    smallRightRotation(left);
                } else {
                    left = new RandomTreeNode(newKey, this);
                }
            } else {
                if (right != null) {
                    right.insertRoot(newKey);
                    smallLeftRotation(right);
                } else {
                    right = new RandomTreeNode(newKey, this);
                }
            }
        }


        public boolean search(int key) {
            return searchNode(key) != null;
        }


        private RandomTreeNode searchNode(int key) {
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


        private RandomTreeNode join(RandomTreeNode tree1, RandomTreeNode tree2) {
            if (tree1 == null) {
                return tree2;
            }
            if (tree2 == null) {
                return tree1;
            }

            tree1.right = join(tree1.right, tree2);
            tree1.recalcHeight();
            return tree1;
        }


        private RandomTreeNode removeNode(RandomTreeNode node, int key) {
            if (node == null) {
                return null;
            }
            if (node.key == key) {
                RandomTreeNode newNode = join(node.left, node.right);
                if (newNode != null) {
                    node.key = newNode.key;
                    node.left = newNode.left;
                    node.right = newNode.right;
                } else {
                    changeChildInParent(node, null);
                }
                return newNode;
            } else if (key < node.key) {
                node.left = removeNode(node.left, key);
            } else {
                node.right = removeNode(node.right, key);
            }
            node.recalcHeight();
            return node;
        }


        private void smallRightRotation(RandomTreeNode node) {
            RandomTreeNode b = node.left;
            if (b == null){
                return;
            }

            RandomTreeNode c = b.right;
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


        private void smallLeftRotation(RandomTreeNode node) {
            RandomTreeNode a = node.right;
            if (a == null) {
                return;
            }

            RandomTreeNode c = a.left;
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


        private void changeChildInParent(RandomTreeNode oldChild, RandomTreeNode newChild) {
            if (oldChild.parent != null) {
                if (oldChild.parent.left == oldChild) {
                    oldChild.parent.left = newChild;
                }
                if (oldChild.parent.right == oldChild) {
                    oldChild.parent.right = newChild;
                }
            } else {
                RandomTree.this.root = newChild;
            }
        }
    }
}