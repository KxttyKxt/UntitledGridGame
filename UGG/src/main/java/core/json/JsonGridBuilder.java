package core.json;

import core.Chunk;
import core.ChunkGrid;

import java.io.IOException;
import java.net.URL;

public class JsonGridBuilder {
    private final JsonChunkBuilder chunkBuilder;
    private JsonParser.JsonDoc doc;

    public static JsonGridBuilder withChunkBuilder(JsonChunkBuilder chunkBuilder) {
        return new JsonGridBuilder(chunkBuilder);
    }

    private JsonGridBuilder(JsonChunkBuilder chunkBuilder) {
        this.chunkBuilder = chunkBuilder;
    }


    public ChunkGrid constructGridFromJson(URL jsonFileURL) throws IOException {
        doc = JsonParser.parseFile(jsonFileURL);
        return generateGrid();
    }

    private ChunkGrid generateGrid() throws IOException {
        Chunk[][] matrixForGrid = new Chunk[doc.rows()][doc.columns()];

        for (int row = 0; row < matrixForGrid.length; row++)
            for (int col = 0; col < matrixForGrid[row].length; col++)
                matrixForGrid[row][col] = buildChunk(doc.fromPattern(row, col));

        return ChunkGrid.newGrid(matrixForGrid);
    }

    private Chunk buildChunk(char patternLetter) throws IOException {
        if (patternLetter == ' ')
            return null;

        String filename = doc.fromMap(patternLetter);
        String qualifiedFilename = String.format("chunk/%s.json", filename);
        URL chunkFileURL = getClass().getResource(qualifiedFilename);
        return chunkBuilder.constructChunkFromJson(chunkFileURL);
    }
}
