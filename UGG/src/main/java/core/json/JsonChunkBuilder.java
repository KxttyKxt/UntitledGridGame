package core.json;

import core.Chunk;
import core.Tile;

import java.io.IOException;
import java.net.URL;

public class JsonChunkBuilder {
    private final ChunkBuilderTileRegistry chunkBuilderTileRegistry;
    private JsonParser.JsonDoc doc;

    public static JsonChunkBuilder usingRegistry(ChunkBuilderTileRegistry registry) {
        return new JsonChunkBuilder(registry);
    }

    private JsonChunkBuilder(ChunkBuilderTileRegistry registry) {
        this.chunkBuilderTileRegistry = registry;
    }


    public Chunk constructChunkFromJson(URL jsonFileURL) throws IOException {
        doc = JsonParser.parseFile(jsonFileURL);return generateChunk();
    }

    private Chunk generateChunk() {
        Tile[][] matrixForGrid = new Tile[doc.rows()][doc.columns()];

        for (int row = 0; row < matrixForGrid.length; row++)
            for (int col = 0; col < matrixForGrid[row].length; col++)
                matrixForGrid[row][col] = getGeneratedTile(doc.fromPattern(row, col));

        return new Chunk(matrixForGrid);
    }

    private Tile getGeneratedTile(char patternLetter) {
        if (patternLetter == ' ')
            return null;

        String tileName = doc.fromMap(patternLetter);
        return chunkBuilderTileRegistry.get(tileName);
    }
}
