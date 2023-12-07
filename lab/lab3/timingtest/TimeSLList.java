package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    /** timeGetLast method
     * @author CoderBak
     * */
    public static void timeGetLast() {
        int calls = 10000;
        AList<Integer> listOfN = new AList<>();
        AList<Integer> listOfCnt = new AList<>();
        AList<Double> listOfTime = new AList<>();
        SLList<Integer> currentSLList;
        int testCnt = 8;
        listOfN.addLast(1000);
        listOfN.addLast(2000);
        listOfN.addLast(4000);
        listOfN.addLast(8000);
        listOfN.addLast(16000);
        listOfN.addLast(32000);
        listOfN.addLast(64000);
        listOfN.addLast(128000);
        for(int i = 1; i <= testCnt; i += 1) {
            int currentN = listOfN.get(i - 1);
            int callCnt = 0;
            currentSLList = new SLList<>();
            for(int j = 1; j <= currentN; j += 1) {
                currentSLList.addLast(j);
            }
            Stopwatch timeKeeper = new Stopwatch();
            for(int j = 1; j <= calls; j += 1) {
                currentSLList.getLast();
                callCnt += 1;
            }
            double timeInSeconds = timeKeeper.elapsedTime();
            listOfTime.addLast(timeInSeconds);
            listOfCnt.addLast(callCnt);
        }
        printTimingTable(listOfN, listOfTime, listOfCnt);
    }

}
