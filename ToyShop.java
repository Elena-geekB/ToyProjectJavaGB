import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;

public class ToyShop {
    private int[] toyIds;
    private String[] toyNames;
    private int[] toyWeights;

    public ToyShop(String toyData1, String toyData2, String toyData3) {
        String[] data1 = toyData1.split(" ");
        String[] data2 = toyData2.split(" ");
        String[] data3 = toyData3.split(" ");

        if (data1.length != 3 || data2.length != 3 || data3.length != 3) {
            throw new IllegalArgumentException("Каждая строка должна содержать ровно 3 элемента");
        }

        toyIds = new int[3];
        toyNames = new String[3];
        toyWeights = new int[3];

        for (int i = 0; i < 3; i++) {
            toyIds[i] = Integer.parseInt(data1[i]);
            toyNames[i] = data2[i];
            toyWeights[i] = Integer.parseInt(data3[i]);
        }
    }

    public void writeToTextFile(String filename, int times) {
        try (FileWriter writer = new FileWriter(filename)) {
            PriorityQueue<String> toyQueue = new PriorityQueue<>();

            for (int i = 0; i < toyIds.length; i++) {
                for (int j = 0; j < toyWeights[i]; j++) {
                    toyQueue.offer(toyNames[i]);
                }
            }

            for (int i = 0; i < times; i++) {
                String toy = toyQueue.poll();
                writer.write("Игрушка " + (i + 1) + ": " + toy + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String toyData1 = "1 2 3";
        String toyData2 = "Мягкая_игрушка LEGO_Set Кукла";
        String toyData3 = "5 3 2";

        ToyShop toyShop = new ToyShop(toyData1, toyData2, toyData3);
        toyShop.writeToTextFile("toys.txt", 10);
    }
}
