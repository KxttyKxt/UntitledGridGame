package tiles;

public class ChunkGrid {
    private final ChunkNode[][] grid;
    private ChunkNode activeNode;

    public static ChunkGrid newGrid(Chunk[][] chunks) {
        ChunkNode[][] grid = new ChunkNode[chunks.length][chunks[0].length];

        for (int row = 0; row < chunks.length; row++)
            for (int col = 0; col < chunks[row].length; col++)
                grid[row][col] = ChunkNode.newNode(chunks[row][col]);

        return new ChunkGrid(grid);
    }

    private ChunkGrid(ChunkNode[][] chunks) {
        grid = new ChunkNode[chunks.length][chunks[0].length];
        activeNode = grid[0][0];
    }

    public void setActiveNode(int row, int col) {
        setActiveNode(grid[row][col]);
    }
    private void setActiveNode(ChunkNode node) {
        activeNode = node;
    }
}

class ChunkNode {
    Chunk chunk;

    static ChunkNode newNode(Chunk chunk) {
        return new ChunkNode(chunk);
    }

    private ChunkNode(Chunk chunk) {
        this.chunk = chunk;
    }
}