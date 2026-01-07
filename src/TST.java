public class TST {

    // Make Node accessible to Boggle
    public class TSTNode {
        char c;
        TSTNode left, mid, right;
        boolean isEndOfWord;
    }

    private TSTNode root;

    public void insert(String word) {
        if (word == null || word.isEmpty()) return;
        root = insert(root, word.toLowerCase(), 0);
    }

    private TSTNode insert(TSTNode node, String word, int index) {
        char c = word.charAt(index);

        if (node == null) {
            node = new TSTNode();
            node.c = c;
        }

        if (c < node.c) {
            node.left = insert(node.left, word, index);
        } else if (c > node.c) {
            node.right = insert(node.right, word, index);
        } else if (index < word.length() - 1) {
            node.mid = insert(node.mid, word, index + 1);
        } else {
            node.isEndOfWord = true;
        }

        return node;
    }

    public boolean contains(String word) {
        if (word == null || word.isEmpty()) return false;
        TSTNode node = get(root, word.toLowerCase(), 0);
        return node != null && node.isEndOfWord;
    }

    private TSTNode get(TSTNode node, String word, int index) {
        if (node == null) return null;

        char c = word.charAt(index);

        if (c < node.c) {
            return get(node.left, word, index);
        } else if (c > node.c) {
            return get(node.right, word, index);
        } else if (index < word.length() - 1) {
            return get(node.mid, word, index + 1);
        } else {
            return node;
        }
    }


    // Needed to start DFS
    public TSTNode getRoot() {
        return root;
    }

    // Move ONE character forward in the TST
    public TSTNode nextNode(TSTNode node, char c) {
        while (node != null) {
            if (c < node.c) node = node.left;
            else if (c > node.c) node = node.right;
            else return node;
        }
        return null;
    }
}
