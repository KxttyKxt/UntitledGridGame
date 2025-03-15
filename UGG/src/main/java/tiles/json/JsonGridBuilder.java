package tiles.json;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import org.apache.commons.io.IOUtils;
import tiles.Tile;
import tiles.TileGrid;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public abstract class JsonGridBuilder {
    private List<String> pattern;
    private Map<String, String> patternMap;

    private final JsonTileRegistry jsonTileRegistry;

    public JsonGridBuilder(JsonTileRegistry jsonTileRegistry) {
        this.jsonTileRegistry = jsonTileRegistry;
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
        return jsonTileRegistry.get(tileName);
    }

}
