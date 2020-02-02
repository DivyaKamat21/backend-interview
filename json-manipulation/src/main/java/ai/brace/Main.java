package ai.brace;

import com.google.gson.*;
import java.io.*;
import java.util.*;

/***
 * Data class stores ids and textdata from the JSON files
 */
class Data {
    int id;
    String txtdata;

    public Data(int id, String txtdata) {
        this.id = id;
        this.txtdata = txtdata;
    }
}


public class Main {

    /**
     * HashMap to store the words from textdata and their counts
     */
    static Map wordCount = new HashMap<String, Integer>();

    public static void main(String[] args) {
        List<Data> dataList = new ArrayList<Data>();

        // Task 1
        System.out.println("\n\nTASK 1");
        String file1 = "src/main/resources/a1.json";
        dataList = addToList(dataList, file1);
        sortData(dataList);
        printOutput(dataList);

        // Task 2
        System.out.println("\n\nTASK 2");
        String file2 = "src/main/resources/a2.json";
        dataList = addToList(dataList, file2);
        printOutput(dataList);

        //Task 3
        System.out.println("\n\nTASK 3");
        System.out.println(wordCount);

    }

    /**
     * Comparator to sort textdata sentences in dataList
     * @param dataList
     */
    public static void sortData(List<Data> dataList) {
        Collections.sort(dataList, new Comparator<Data>() {
            public int compare(Data d1, Data d2) {
                return d1.id - d2.id;
            }
        });
    }

    /**
     * Prints the textdata from list of Data class
     * @param dataList
     */
    public static void printOutput(List<Data> dataList) {
        for (Data d : dataList)
            System.out.println(d.txtdata);
    }

    /**
     * Adds ids and textdata from the JsonArray to datalist
     * @param dataList
     * @param file
     * @return returns list containing data parsed from JSON file
     */
    public static List<Data> addToList(List<Data> dataList, String file) {

        Gson gson = new Gson();

        JsonParser jsonParser = new JsonParser();

        try {
            JsonElement jsonTree = jsonParser.parse(new FileReader(file));

            JsonObject jsonObject = jsonTree.getAsJsonObject();

            JsonArray array = jsonObject.getAsJsonArray("textArray");

            array.forEach((element) ->
            {
                if (element.isJsonObject()) {

                    int id = element.getAsJsonObject().get("id").getAsInt();
                    String txt = element.getAsJsonObject().get("textdata").getAsString();

                    //Adds words and counts to hashmap
                    for (String s : txt.split(" ")) {
                        int count = (int) wordCount.getOrDefault(s, 0);
                        wordCount.put(s, count + 1);
                    }

                    dataList.add(new Data(id, txt));

                }
            });
        } catch (Exception e) {
            //use logging framework here to log any exceptions
        }

        return dataList;
    }
}
