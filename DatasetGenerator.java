import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class DatasetGenerator {
    static String directoryPath = "Datasets/";

    public static void main(String[] args) {
        int kecil = (int) Math.pow(2, 9);
        int sedang = (int) Math.pow(2, 13);
        int besar = (int) Math.pow(2, 16);
        int[] sizes = { kecil, sedang, besar };
        String[] orders = { "sorted", "random" };

        for (int size : sizes) {
            for (String order : orders) {
                if (order.equals("sorted")) {
                    List<Integer> dataset = generateDataset(size, order);
                    save(dataset, size, order);

                    Collections.reverse(dataset);
                    save(dataset, size, "reversed");
                }

                else {
                    List<Integer> dataset = generateDataset(size, order);
                    save(dataset, size, order);
                }
            }
        }
    }

    public static List<Integer> generateDataset(int size, String order) {
        List<Integer> dataset = new ArrayList<>();
        for (int i = 1; i <= size; i++) {
            dataset.add(i);
        }

        if (order.equals("sort")) {
            Collections.sort(dataset);
        } else if (order.equals("random")) {
            Collections.shuffle(dataset);
        }

        return dataset;
    }

    public static void save(List<Integer> dataset, int size, String order) {
        String fileName = "dataset_" + size + "_" + order + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(directoryPath + fileName))) {
            for (int data : dataset) {
                writer.write(Integer.toString(data));
                writer.newLine();
            }
            System.out.println("Dataset saved to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
