package operationrene.mapframework;

import java.io.*;

import flexjson.*;

public class LevelLoader {

    public static LevelMap loadLevel() {
        String ls = System.getProperty("line.separator");

        JSONDeserializer deserializer = new JSONDeserializer();
        StringBuilder sb = new StringBuilder();
        FileReader fr = null;
        BufferedReader br = new BufferedReader(fr);

        try {

            String line = "";
            while((line = br.readLine()) != null) {
                sb.append(line);
                sb.append(ls);
            }

            return (LevelMap) deserializer.deserialize(sb.toString());
        } catch (Exception ex) {
            System.out.println(ex.getStackTrace());
        }

        return null;
    }

    public static String saveLevel(LevelMap level, String path) {
        JSONSerializer serializer = new JSONSerializer();
        String json = serializer.serialize(level);

        FileWriter fw = null;
        try {
            fw = new FileWriter(path);
            fw.write(json);
            fw.close();
            return json;
        } catch (Exception ex) {
            System.out.println(ex.getStackTrace());
        }

        return null;
    }

}
