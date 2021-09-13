//https://leetcode.com/problems/remove-invalid-parentheses/
package src.leetcode;

import java.util.*;

public class RemoveInvalidParenthesis {
    Map<String,Boolean> valid = new HashMap();

    //Method to check if a string is valid stack. Uses memorization to speedup the process
    boolean isValid(String s){
        if(valid.containsKey(s)){
            return valid.get(s);
        }
        Stack<Character> stack = new Stack<>();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                stack.push('(');
            }
            else if(s.charAt(i)==')'){
                if(stack.isEmpty()||stack.peek()!='('){
                    valid.put(s,false);
                    return false;
                }
                stack.pop();
            }
        }
        valid.put(s,stack.isEmpty());
        return stack.isEmpty();
    }

    public List<String> removeInvalidParentheses(String s) {
        //Perform BFS to iterate through all strings, in the descending order of lengths
        Queue<String> queue = new LinkedList();
        List<String> ans = new ArrayList();
        //Start BFS with string
        queue.add(s);
        Set<String> visited = new HashSet();
        visited.add(s);
        while (!queue.isEmpty()){
            String t = queue.poll();
            //Check if string is valid.
            if(isValid(t)){
                //Add to answer
                ans.add(t);
            }
            //If a valid string is found, then we need not BFS lower length strings
            if(ans.isEmpty()){
                for(int i=0;i<t.length();i++){
                    //Remove ith character and add to stack. Only remove ith character if it is a bracket
                    if(!Character.isAlphabetic(t.charAt(i))&&!visited.contains(t.substring(0,i)+t.substring(i+1))) {
                        queue.add(t.substring(0, i) + t.substring(i + 1));
                        visited.add(t.substring(0, i) + t.substring(i + 1));
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new RemoveInvalidParenthesis().removeInvalidParentheses("()())()"));
        System.out.println(new RemoveInvalidParenthesis().removeInvalidParentheses("(a)())()"));
        System.out.println(new RemoveInvalidParenthesis().removeInvalidParentheses(")("));
    }
}
