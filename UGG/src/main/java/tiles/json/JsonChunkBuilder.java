package tiles.json;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import org.apache.commons.io.IOUtils;
import tiles.Chunk;
import tiles.Tile;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public abstract class JsonChunkBuilder {
    private List<String> pattern;
    private Map<String, String> patternMap;

    private final JsonTileRegistry jsonTileRegistry =
            JsonTileRegistry.createRegistryWithMap(createMapForRegistry());

    protected abstract Map<String, Tile.Builder> createMapForRegistry();


    protected Chunk constructGridFromJson(URL jsonFileURL) throws IOException {
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


    private Chunk generateGrid() {
        int rowsInPattern = pattern.size();
        int rowLength = pattern.getFirst().length();

        Tile[][] matrixForGrid = new Tile[rowsInPattern][rowLength];

        for (int row = 0; row < matrixForGrid.length; row++)
            for (int col = 0; col < matrixForGrid[row].length; col++)
                matrixForGrid[row][col] = getGeneratedTile(pattern.get(row).charAt(col));

        return new Chunk(matrixForGrid);
    }

    private Tile getGeneratedTile(char patternLetter) {
        if (patternLetter == ' ')
            return null;

        String tileName = patternMap.get(String.valueOf(patternLetter));
        return jsonTileRegistry.get(tileName);
    }

}
