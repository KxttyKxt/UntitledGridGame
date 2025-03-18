package tiles;

import util.Convert;

import java.util.Map;

public class ChunkGrid {
    private final Map<Point2D, Chunk> chunkMap;
    private Chunk activeChunk;

    public static ChunkGrid newGrid(Chunk[][] chunks) {
        return new ChunkGrid(Convert.matrixToPointMap(chunks));
    }

    private ChunkGrid(Map<Point2D, Chunk> chunkMap) {
        this.chunkMap = chunkMap;
        setActiveChunk(Point2D.of(0, 0));
    }

    public Chunk getActiveChunk() {
        return activeChunk;
    }
    public void setActiveChunk(Point2D pointOfChunk) {
        activeChunk = chunkMap.get(pointOfChunk);
    }

    @Override
    public String toString() {
        return activeChunk.toString();
    }

}