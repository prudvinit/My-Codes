import java.util.LinkedList;

/**
 *
 * @author prudvi.maddala@oracle.com
 */
public class AhoCorasickAlgorithm {

    /**
     * @param args the command line arguments
     */
    Trie root;

    public AhoCorasickAlgorithm() {
        root = new Trie(-1);
        root.parent = root.blueLink = null;
    }

    static class Trie {

        Trie childs[], parent, blueLink, greenLink;
        boolean leaf = false;
        int c = 0, ch, totalGreen = 0;

        Trie(int ch) {
            this.ch = ch;
            childs = new Trie[26];
        }

        @Override
        public String toString() {
            return "[" + (char) (ch + 97) + "]";
        }
    }

    void insert(String s) {
        Trie temp = root;
        int ind;
        for (int i = 0; i < s.length(); i++) {
            ind = s.charAt(i) - 97;
            if (temp.childs[ind] == null) {
                temp.childs[ind] = new Trie(ind);
                temp.childs[ind].blueLink = root;
            }
            temp.childs[ind].c++;
            temp.childs[ind].parent = temp;
            temp = temp.childs[ind];
        }
        temp.leaf = true;
    }

    void runBFSAndFormBlueLinks() {
        LinkedList<Trie> q = new LinkedList();
        q.addLast(root);
        Trie node, parent, parentLink, temp;
        int ind;
        while (!q.isEmpty()) {
            node = q.removeFirst();
            ind = node.ch;
            for (int i = 0; i < 26; i++) {
                if (node.childs[i] != null && node.childs[i].c >= 0) {
                    q.addLast(node.childs[i]);
                }
            }
            if (node != root && node.parent != root) {
                parentLink = node.parent.blueLink;
                while (parentLink != null) {
                    if (parentLink.childs[ind] != null && parentLink.childs[ind].c >= 0) {
                        node.blueLink = parentLink.childs[ind];
                        break;
                    }
                    parentLink = parentLink.blueLink;
                }
            }
            temp = node.blueLink;
            node.greenLink = null;
            node.totalGreen = 0;
            while (temp != null && temp != root) {
                if (temp.leaf) {
                    node.greenLink = temp;
                    node.totalGreen = 1 + node.greenLink.totalGreen;
                    break;
                }
                temp = temp.blueLink;
            }
        }

    }

    void delete(String s) {
        Trie temp = root;
        int ind;
        for (int i = 0; i < s.length(); i++) {
            ind = s.charAt(i) - 97;
            temp = temp.childs[ind];
            temp.c--;
        }
        temp.leaf = false;
    }

    int runAhoCorasickAlgorithm(String pattern) {
        runBFSAndFormBlueLinks();
        Trie temp = root, blue, green;
        int ind, count = 0;
        for (int i = 0; i < pattern.length(); i++) {
            ind = pattern.charAt(i) - 97;
            if (temp.childs[ind] != null && temp.childs[ind].c >= 1) {
                if (temp.childs[ind].leaf) {
                    count++;
                }
                count = count + temp.childs[ind].totalGreen;
                temp = temp.childs[ind];
            } else if (temp != root) {
                temp = temp.blueLink;
                i--;
            }
        }
        return count;
    }

}
