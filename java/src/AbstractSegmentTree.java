package src;

import java.util.Arrays;

abstract class AbstractSegmentTree {
    private int tree[];
    private int n;

    public abstract int getIdentityValue();

    public abstract int associativeFunction(int leftVal, int rightVal);

    public AbstractSegmentTree(int n) {
        int s = (int) Math.pow(2, Math.ceil(Math.log(n + 1) / Math.log(2)) + 1) - 1;
        tree = new int[s];
        this.n = n;
    }

    private void update(int u, int val, int left, int right, int current) {
        if (u < left || u > right || left > right) {
            return;
        }

        if (left == right) {
            tree[current] = val;
            return;
        }
        int mid = (left + right) >> 1;
        update(u, val, left, mid, 2 * current + 1);
        update(u, val, mid + 1, right, 2 * current + 2);
        tree[current] = associativeFunction(tree[2 * current + 1], tree[2 * current + 2]);
    }

    public void update(int pos, int value) {
        update(pos, value, 0, n - 1, 0);
    }

    private int get(int qLeft, int qRight, int left, int right, int current) {
        if (qRight < left || qLeft > right || left > right) {
            return getIdentityValue();
        }
        if (qLeft <= left && qRight >= right) {
            return tree[current];
        }
        int mid = (left + right) >> 1;
        return associativeFunction(get(qLeft, qRight, left, mid, 2 * current + 1), get(qLeft, qRight, mid + 1, right, 2 * current + 2));
    }

    public int get(int qLeft, int qRight) {
        return get(qLeft, qRight, 0, n - 1, 0);
    }

    public void printTree() {
        System.out.println(Arrays.toString(tree));
    }
}

class MaxSegmentTree extends AbstractSegmentTree {

    public MaxSegmentTree(int n) {
        super(n);
    }

    @Override
    public int getIdentityValue() {
        return 0;
    }

    @Override
    public int associativeFunction(int leftVal, int rightVal) {
        return Math.max(leftVal, rightVal);
    }
}
