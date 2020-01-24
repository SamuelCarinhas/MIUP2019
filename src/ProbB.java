import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ProbB {

    //WA Test Case - 11

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer data = new StringTokenizer(input.readLine());

        long dI = Long.parseLong(data.nextToken()),
                dH = Long.parseLong(data.nextToken()),
                x0 = Long.parseLong(data.nextToken()),
                a = Long.parseLong(data.nextToken()),
                b = Long.parseLong(data.nextToken()),
                m = Long.parseLong(data.nextToken()),
                E = Long.parseLong(data.nextToken()),
                j = Long.parseLong(data.nextToken()),
                p = Long.parseLong(data.nextToken()),
                l = Long.parseLong(data.nextToken());

        char[] terrain = input.readLine().toCharArray();

        PRG prg = new PRG(x0, a, b, m, dH, dI);

        int currentPos = 0;
        long consumedEnergy = 0, walked = 0;
        boolean finish = false;

        while(true) {
            long jumpLength = prg.getJumpLength();
            if(terrain[currentPos] == 'D') jumpLength *= -1;
            if(terrain[currentPos] == 'P') {
                E += p;
                terrain[currentPos] = '.';
            }

            if(currentPos + jumpLength > l) jumpLength = l - currentPos;
            if(currentPos + jumpLength < 0) jumpLength = -currentPos;
            currentPos += jumpLength;

            E -= j;

            if(E < 0) {
                E += j;
                consumedEnergy += E;
                break;
            }

            if(currentPos < 0) currentPos = 0;
            walked += Math.abs(jumpLength);
            consumedEnergy += j;

            if(currentPos == l) {
                finish = true;
                break;
            }
        }

        System.out.println(walked);
        System.out.println(consumedEnergy);
        System.out.println(finish ? "yes" : "no");

    }

    static class PRG {

        private long xN, a, b, m, dH, dI;

        public PRG(long xN, long a, long b, long m, long dH, long dI) {
            this.xN = xN;
            this.a = a;
            this.b = b;
            this.m = m;
            this.dH = dH;
            this.dI = dI;
        }

        private long getNextRandom() {
            long old = (a * xN + b) % m;
            xN = old;
            return old;
        }

        public long getJumpLength() {
            return dI + (getNextRandom() % (dH - dI + 1));
        }

    }

}
