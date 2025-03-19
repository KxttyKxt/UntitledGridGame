package core;

import util.Map2D;

import java.util.Map;

public class ChunkGrid {
    private final Map<Point2D, Chunk> chunkMap;

    public static ChunkGrid newGrid(Chunk[][] chunks) {
        return new ChunkGrid(Map2D.of(chunks));
    }

    private ChunkGrid(Map<Point2D, Chunk> chunkMap) {
        this.chunkMap = chunkMap;
    }


    public Chunk getChunk(Point2D chunkPoint) {
        return chunkMap.get(chunkPoint);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChunkGrid that = (ChunkGrid) o;
        return this.chunkMap.equals(that.chunkMap);
    }
}