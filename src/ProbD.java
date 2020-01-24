import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ProbD {

    // BIG TLE XD

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer crimeData = new StringTokenizer(input.readLine());
        String[] startDate = crimeData.nextToken().split(":");
        String[] endDate = crimeData.nextToken().split(":");
        int need = Integer.parseInt(crimeData.nextToken());
        int crimeStart = Integer.parseInt(startDate[0])*60 + Integer.parseInt(startDate[1]);
        int crimeEnd = Integer.parseInt(endDate[0])*60 + Integer.parseInt(endDate[1]);

        int suspects = Integer.parseInt(input.readLine());
        for(int i = 0; i < suspects; i++) {
            int alibis = Integer.parseInt(input.readLine());
            boolean[] alibi = new boolean[24*60+1];
            for(int j = 0; j < 24*60+1; j++) alibi [j] = false;
            for(int j = 0; j < alibis; j++) {
                StringTokenizer alibiData = new StringTokenizer(input.readLine());
                String[] alibiSData = alibiData.nextToken().split(":");
                String[] alibiEData = alibiData.nextToken().split(":");
                int extra = Integer.parseInt(alibiData.nextToken());
                int alibiStart = Integer.parseInt(alibiSData[0])*60 + Integer.parseInt(alibiSData[1]) - extra;
                int alibiEnd = Integer.parseInt(alibiEData[0])*60 + Integer.parseInt(alibiEData[1]) + extra;
                if(alibiStart < 0) alibiStart = 0;
                if(alibiEnd > 24*60) alibiEnd = 24*60;
                for(int l = alibiStart; l < alibiEnd; l++) alibi[l] = true;
            }
            boolean suspect = false;
            for(int j = crimeStart; j < crimeEnd - need; j++) {
                boolean found = true;
                for(int l = j; l < j + need; l++) {
                    if(alibi[l] == true) {
                        found = false;
                        break;
                    }
                }
                if(found) {
                    suspect = true;
                    break;
                }
            }
            System.out.println(!suspect ? "yes" : "no");
        }
    }

}
