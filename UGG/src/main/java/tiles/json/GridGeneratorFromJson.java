package tiles.json;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import org.apache.commons.io.IOUtils;
import tiles.Tile;
import tiles.TileGrid;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class GridGeneratorFromJson {
    private ArrayList<String> pattern;
    private HashMap<String, String> patternMap;

    private final TileGeneratorFromJson tileGeneratorFromJson;

    public GridGeneratorFromJson(TileGeneratorFromJson tileGeneratorFromJson) {
        this.tileGeneratorFromJson = tileGeneratorFromJson;
    }

    protected TileGrid constructGridFromJson(URL jsonFileURL) throws IOException {
        Object jsonDocument = convertToDocument(jsonFileURL);

        pattern = JsonPath.read(jsonDocument, "$.pattern");
        patternMap = JsonPath.read(jsonDocument, "$.pattern-map");

        return generateGrid();
    }


    private Object convertToDocument(URL jsonFileURL) throws IOException {
        String json = readFileToString(jsonFileURL);
        return convertJsonStringToDocument(json);
    }

    private String readFileToString(URL jsonFileURL) throws IOException {
        return IOUtils.toString(jsonFileURL, StandardCharsets.UTF_8);
    }

    private Object convertJsonStringToDocument(String json) {
        return Configuration.defaultConfiguration().jsonProvider().parse(json);
    }


    private TileGrid generateGrid() {
        int rowsInPattern = pattern.size();
        int rowLength = pattern.getFirst().length();

        Tile[][] matrixForGrid = new Tile[rowsInPattern][rowLength];

        for (int row = 0; row < matrixForGrid.length; row++)
            for (int col = 0; col < matrixForGrid[row].length; col++)
                matrixForGrid[row][col] = getGeneratedTile(pattern.get(row).charAt(col));

        return new TileGrid(matrixForGrid);
    }

    private Tile getGeneratedTile(char patternLetter) {
        if (patternLetter == ' ')
            return null;

        String tileName = patternMap.get(String.valueOf(patternLetter));
        return tileGeneratorFromJson.generateTile(tileName);
    }

}
