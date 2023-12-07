package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
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
        timeAListConstruction();
    }

    /** timeAListConstruction method
     * @author CoderBak
     * */
    public static void timeAListConstruction() {
        AList<Integer> listOfN = new AList<>();
        AList<Integer> listOfCall = new AList<>();
        AList<Double> listOfTime = new AList<>();
        AList<Integer> currentAList;
        int testCnt = 8;
        listOfN.addLast(1000);
        listOfN.addLast(2000);
        listOfN.addLast(4000);
        listOfN.addLast(8000);
        listOfN.addLast(16000);
        listOfN.addLast(32000);
        listOfN.addLast(64000);
        listOfN.addLast(128000);
        for (int i = 1; i <= testCnt; i += 1) {
            int currentN = listOfN.get(i - 1);
            int callCnt = 0;
            currentAList = new AList<>();
            Stopwatch timeKeeper = new Stopwatch();
            for (int j = 1; j <= currentN; j += 1) {
                currentAList.addLast(j);
                callCnt += 1;
            }
            double timeInSeconds = timeKeeper.elapsedTime();
            listOfCall.addLast(callCnt);
            listOfTime.addLast(timeInSeconds);
        }
        printTimingTable(listOfN, listOfTime, listOfCall);
    }
}
