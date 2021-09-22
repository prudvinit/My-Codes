package src;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class MainTest {
    public static void main(String[] args) {
        List<Integer> a = new ArrayList(Arrays.asList(1,2,3,4));
        List<Integer> b = new ArrayList(Arrays.asList(3,4,5,8));
        a.retainAll(b);
        System.out.println(a);

    }
}

