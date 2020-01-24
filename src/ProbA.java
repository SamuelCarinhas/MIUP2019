import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ProbA {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer rightData = new StringTokenizer(input.readLine());
        StringTokenizer leftData = new StringTokenizer(input.readLine());

        int nRight = Integer.parseInt(rightData.nextToken()), nLeft = Integer.parseInt(leftData.nextToken());

        Map<Integer, LinkedList<Integer>> right = new HashMap<>(), left = new HashMap<>();
        Map<Integer, Boolean> stems = new HashMap<>();

        int maxLeft = 0, maxStem = 0;

        for(int i = 0; i < nRight; i++) {
            int n = Integer.parseInt(rightData.nextToken());

            int leaf = n % 10;
            int stem = n / 10;

            stems.putIfAbsent(stem, true);
            right.putIfAbsent(stem, new LinkedList<>());
            left.putIfAbsent(stem, new LinkedList<>());

            right.get(stem).add(leaf);

            maxStem = Math.max(maxStem, (stem + "").length());
        }

        for(int i = 0; i < nLeft; i++) {
            int n = Integer.parseInt(leftData.nextToken());

            int leaf = n % 10;
            int stem = n / 10;

            stems.putIfAbsent(stem, true);
            right.putIfAbsent(stem, new LinkedList<>());
            left.putIfAbsent(stem, new LinkedList<>());

            left.get(stem).add(leaf);

            maxStem = Math.max(maxStem, (stem + "").length());
        }

        LinkedList<Integer> stemList = new LinkedList<>();
        for(int v : stems.keySet()) {
            stemList.add(v);
            maxLeft = Math.max(maxLeft, left.get(v).size());
            Collections.sort(left.get(v));
            Collections.sort(right.get(v));
        }

        Collections.sort(stemList);

        for(int stem : stemList) {
            for(int i = left.get(stem).size(); i < maxLeft; i++) System.out.print('.');
            for(int i = left.get(stem).size() - 1; i >= 0; i--) System.out.print(left.get(stem).get(i));
            System.out.print(".|");
            for(int i = (stem + "").length(); i < maxStem; i++) System.out.print('.');
            System.out.print(stem + "|.");
            for(int leaf : right.get(stem)) System.out.print(leaf);
            System.out.println();
        }

    }

}