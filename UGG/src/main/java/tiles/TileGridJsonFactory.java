package tiles;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public class TileGridJsonFactory {
    private static ArrayList<String> pattern;
    private static HashMap<String, String> patternMap;

    public static TileGrid constructGridFromJson(URL jsonFileURL) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException {
        Object jsonDocument = convertToDocument(jsonFileURL);

        pattern = JsonPath.read(jsonDocument, "$.pattern");
        patternMap = JsonPath.read(jsonDocument, "$.pattern-map");

        return generateGrid();
    }


    private static Object convertToDocument(URL jsonFileURL) throws IOException {
        String json = readFileToString(jsonFileURL);
        return convertJsonStringToDocument(json);
    }

    private static String readFileToString(URL jsonFileURL) throws IOException {
        return IOUtils.toString(jsonFileURL, StandardCharsets.UTF_8);
    }

    private static Object convertJsonStringToDocument(String json) {
        return Configuration.defaultConfiguration().jsonProvider().parse(json);
    }


    private static TileGrid generateGrid() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        int rowsInPattern = pattern.size();
        int rowLength = pattern.getFirst().length();

        Tile[][] matrixForGrid = new Tile[rowsInPattern][rowLength];

        for (int row = 0; row < matrixForGrid.length; row++)
            for (int col = 0; col < matrixForGrid[row].length; col++)
                matrixForGrid[row][col] = getTileFromPatternMap(pattern.get(row).charAt(col));

        return new TileGrid(matrixForGrid);
    }

    private static Tile getTileFromPatternMap(char patternLetter) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String tileSubclassName = patternMap.get(String.valueOf(patternLetter));
        Class<?> tileSubclass = Class.forName(String.format("tiles.%s", tileSubclassName));

        return (Tile) tileSubclass.getDeclaredConstructor().newInstance();
    }
}
