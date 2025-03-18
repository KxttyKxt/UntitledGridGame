package core;

import util.Map2D;

import java.util.Map;

public class ChunkGrid {
    private final Map<Point2D, Chunk> chunkMap;
    private Chunk activeChunk;

    public static ChunkGrid newGrid(Chunk[][] chunks) {
        return new ChunkGrid(Map2D.of(chunks));
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChunkGrid that = (ChunkGrid) o;
        return this.chunkMap.equals(that.chunkMap);
    }


}