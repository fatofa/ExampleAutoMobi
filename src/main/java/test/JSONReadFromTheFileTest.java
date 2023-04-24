package test;
import io.cucumber.core.gherkin.messages.internal.gherkin.internal.com.eclipsesource.json.ParseException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
public class JSONReadFromTheFileTest {
    static final String JSON_FILE = "jobs.json";

    public static void main(String[] args) throws FileNotFoundException, ParseException, IOException, org.json.simple.parser.ParseException {
        //createJSONFile();
        System.out.println("createJSONFile");
        extractNode();
    }

    @SuppressWarnings("unchecked")
    static void createJSONFile() {

        JSONObject durationObject = new JSONObject();

        durationObject.put("start","2016-01-01 00:00:00");
        durationObject.put("end", "2016-02-28 00:00:00");

        JSONObject obj1 = new JSONObject();

        obj1.put("employer","ITHS");
        obj1.put("duration", durationObject);
        obj1.put("title", "Database teacher");
        obj1.put("description", "Teacher for a Database course");

        JSONObject obj2 = new JSONObject();
        JSONObject durationObject2 = new JSONObject();

        durationObject2.put("start","2016-06-01 00:00:00");
        durationObject2.put("end", "2016-12-31 00:00:00");
        obj2.put("employer","GU");
        obj2.put("duration", durationObject2);
        obj2.put("title", "Database teacher");
        obj2.put("description", "Lecturer for an introductory Java course etc");

        JSONArray list = new JSONArray();
        list.add(obj1);
        list.add(obj2);

        JSONObject obj = new JSONObject();
        obj.put("jobs", list);


        try (FileWriter file = new FileWriter(JSON_FILE)) {
            file.write(obj.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void extractNode() throws org.json.simple.parser.ParseException, FileNotFoundException, IOException {

        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader("jobs.json")) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
//            String name = (String)jsonObject.get("employer");
            System.out.println(jsonObject);
            System.out.println(jsonObject.get("age"));
        }
    }

    static void parseJSONObject(JSONObject jsonObject) {
        JSONObject emp = (JSONObject) jsonObject.get("employer");
        String firstName = (String) emp.get("description");
        System.out.println(firstName);
    }
}