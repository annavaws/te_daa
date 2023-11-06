import java.util.*;

public class RandomizedShellSort {
    public static final int C = 1; // number of region compare-exchange repetitions
    private static Random rand = new Random();

    public static void exchange(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void compareExchange(int[] a, int i, int j) {
        if (((i < j) && (a[i] > a[j])) || ((i > j) && (a[i] < a[j])))
            exchange(a, i, j);
    }

    public static void permuteRandom(int a[]) {
        for (int i = 0; i < a.length; i++) // Use the Knuth random perm. algorithm
            exchange(a, i, rand.nextInt(a.length - i) + i);
    }

    // compare-exchange two regions of length offset each
    public static void compareRegions(int[] a, int s, int t, int offset) {
        int mate[] = new int[offset]; // index offset array
        for (int count = 0; count < C; count++) { // do C region compare-exchanges
            for (int i = 0; i < offset; i++)
                mate[i] = i;
            permuteRandom(mate); // comment this out to get a deterministic Shellsort
            for (int i = 0; i < offset; i++)
                compareExchange(a, s + i, t + mate[i]);
        }
    }

    public static void randomizedShellSort(int[] a) {
        int n = a.length; // we assume that n is a power of 2
        // MyRandom rand = new MyRandom(); // random number generator (not shown)
        for (int offset = n / 2; offset > 0; offset /= 2) {
            for (int i = 0; i < n - offset; i += offset) // compare-exchange up
                compareRegions(a, i, i + offset, offset);
            for (int i = n - offset; i >= offset; i -= offset) // compare-exchange down
                compareRegions(a, i - offset, i, offset);
            for (int i = 0; i < n - 3 * offset; i += offset) // compare 3 hops up
                compareRegions(a, i, i + 3 * offset, offset);
            for (int i = 0; i < n - 2 * offset; i += offset) // compare 2 hops up
                compareRegions(a, i, i + 2 * offset, offset);
            for (int i = 0; i < n; i += 2 * offset) // compare odd-even regions
                compareRegions(a, i, i + offset, offset);
            for (int i = offset; i < n - offset; i += 2 * offset) // compare even-odd regions
                compareRegions(a, i, i + offset, offset);
        }
    }

}