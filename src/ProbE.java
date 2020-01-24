import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class ProbE {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int m = Integer.parseInt(input.readLine());

        Stack<Integer> stack = new Stack<>();

        boolean correct = true;

        for(int i = 0; i < m; i++) {
            int n = Integer.parseInt(input.readLine());
            if(n > 0) stack.add(n);
            else if(!stack.isEmpty() && stack.lastElement() == -n) stack.pop();
            else correct = false;
        }

        if(correct && stack.isEmpty()) System.out.println("y");
        else System.out.println("n");

    }

}