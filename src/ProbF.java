import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ProbF {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(input.readLine());

        LinkedList<Account> accountList = new LinkedList<>();
        Map<Account, LinkedList<Account>> graph = new HashMap<>();

        for(int i = 0; i < n; i++) {
            StringTokenizer data = new StringTokenizer(input.readLine());

            String username = data.nextToken(), email = data.nextToken(), ip = data.nextToken();

            accountList.add(new Account(username, email, ip));
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i == j) continue;
                if(accountList.get(i).email.equals(accountList.get(j).email) || accountList.get(i).ip.equals(accountList.get(j).ip)) {
                    graph.putIfAbsent(accountList.get(i), new LinkedList<>());
                    graph.get(accountList.get(i)).add(accountList.get(j));
                }
            }
        }

        LinkedList<Account> toRemove = new LinkedList<>();
        HashSet<Account> visited = new HashSet<>();

        for(int i = 0; i < n; i++) {
            Queue<Account> queue = new LinkedList<>();
            queue.add(accountList.get(i));
            while(!queue.isEmpty()) {
                Account current = queue.element();
                visited.add(current);
                queue.remove();

                if(!graph.containsKey(current)) continue;

                for(Account adj : graph.get(current)) {
                    if(!visited.contains(adj)) {
                        toRemove.add(adj);
                        queue.add(adj);
                        visited.add(adj);
                    }
                }
            }
        }

        HashSet<Account> knew = new HashSet<>();

        for(Account account : toRemove) {
            if(!knew.contains(account)) {
                knew.add(account);
                accountList.remove(account);
            }
        }

        System.out.println(accountList.size());

    }


    static class Account {

        String username, email, ip;

        public Account(String username, String email, String ip) {
            this.username = username;
            this.email = email;
            this.ip = ip;
        }

        @Override
        public String toString() {
            return username + " " + email + " " + ip;
        }
    }

}