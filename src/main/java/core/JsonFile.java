package core;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JsonFile {

    private static String jsonPath = "src/main/resources/jobs.json";
    private static JSONParser parser;
    private static JSONObject jsonObject;


    public static void setJSONFile() {
        parser = new JSONParser();
        try (FileReader reader = new FileReader(jsonPath)) {
            jsonObject = (JSONObject) parser.parse(reader);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public static String getJSONValue(String keyJSON) {
        String value = null;
        try {
            value = (String) jsonObject.get(keyJSON);
            return value;
        } catch ( Exception e ) {
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            e.printStackTrace();
        }
        return value;
    }

}
