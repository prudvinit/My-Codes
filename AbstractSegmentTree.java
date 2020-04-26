import java.util.Arrays;

abstract class AbstractSegmentTree {
    public int tree[];
    int n;

    public abstract int getDefaultValue();

    public AbstractSegmentTree(int n) {
        int s = (int) Math.pow(2, Math.ceil(Math.log(n + 1) / Math.log(2)) + 1) - 1;
        tree = new int[s];
    }

    void updateSegmentTree(int u, int val, int left, int right, int current) {
        if (u < left || u > right || left > right) {
            return;
        }

        if (left == right) {
            tree[current] = val;
            return;
        }
        int mid = (left + right) >> 1;

        updateSegmentTree(u, val, left, mid, 2 * current + 1);
        updateSegmentTree(u, val, mid + 1, right, 2 * current + 2);
        tree[current] = associativeFunction(tree[2 * current + 1], tree[2 * current + 2]);
    }

    int getVal(int qLeft, int qRight, int left, int right, int current) {
        if (qRight < left || qLeft > right || left>right) {
            return getDefaultValue();
        }
        if (qLeft <= left && qRight >= right) {
            return tree[current];
        }
        int mid = (left + right) >> 1;
        return associativeFunction(getVal(qLeft, qRight, left, mid, 2 * current + 1), getVal(qLeft, qRight, mid + 1, right, 2 * current + 2));
    }

    public abstract int associativeFunction(int leftVal, int rightVal);

}

class MaxSegmentTree extends AbstractSegmentTree{

    public MaxSegmentTree(int n) {
        super(n);
    }

    @Override
    public int getDefaultValue() {
        return 0;
    }

    @Override
    public int associativeFunction(int leftVal, int rightVal) {
        return Math.max(leftVal,rightVal);
    }
}
