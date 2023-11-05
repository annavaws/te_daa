import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    static String directoryPath = "Datasets/";

    public static void main(String[] args) {

        // int[] sizes = { 512 };
        int[] sizes = { 512, 8192, 65536 };
        // String[] orders = { "random" };
        String[] orders = { "sorted", "random", "reversed" };
        String path = "dataset_";
        for (int size : sizes) {
            for (String order : orders) {
                path += size + "_" + order + ".txt";
                int[] dataset = readDataset(path);
                int[] dataset2 = dataset.clone();
                path = "dataset_";
                
             
                System.gc();
                long startTime = System.nanoTime();
                long startMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

                MaxHeapSort.heapSort(dataset);

                long endTime = System.nanoTime();
                System.gc();
                long endMemory =Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                long duration = endTime - startTime;
                double msDuration = (double) duration / 1000000;
                long usedMemory = endMemory - startMemory;
                System.out.println(outputMessage("Heap", order, size, msDuration, usedMemory));
                checkSorted(dataset);
                
                // Garbage Collector
                // System.gc();

                // long startTime2 = System.nanoTime();
                // long startMemory2 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

                // RandomizedShellSort.randomizedShellSort(dataset2);

                // long endTime2 = System.nanoTime();
                // long endMemory2 = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                // long duration2 = endTime2 - startTime2;
                // double msDuration2 = (double) duration2 / 1000000;
                // long usedMemory2 = endMemory2 - startMemory2;
                // System.out.println(outputMessage("Randomized Shell", order, size, msDuration2, usedMemory2));
                
            }
        }

    }

    public static String outputMessage(String algorithm, String order, int size, double msDuration, long usedMemory) {

        return String.format(
                "\n[Algorithm %s Sort] For %s %s data\nExecution Time: %s mileseconds\nMemory Usage: %s KB",
                algorithm, order, size, msDuration, usedMemory / 1024);

    }

    public static void checkSorted(int[] dataset) {
        boolean flag = true;
        for (int i = 0; i < dataset.length - 1; i++) {
            if (dataset[i] > dataset[i + 1]) {
                flag = false;
            }
        }

        if (flag) {
            System.out.println("Dataset Sorted");
        } else {
            System.out.println("Dataset Not Sorted");
        }

    }

    public static int[] readDataset(String path) {
        List<Integer> dataset = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(directoryPath + path))) {

            String num;
            while ((num = reader.readLine()) != null) {
                dataset.add(Integer.parseInt(num));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int[] datasetArray = new int[dataset.size()];
        for (int i = 0; i < dataset.size(); i++) {
            datasetArray[i] = dataset.get(i);
        }

        return datasetArray;
    }

}

// https://www.vogella.com/tutorials/JavaPerformance/article.html