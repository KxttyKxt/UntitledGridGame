package ugg.tiles;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ugg.tiles.graph.TileMatrixGraph;
import ugg.tiles.graph.TileNodeDirection;
import ugg.tiles.graph.nodes.TileNode;
import ugg.tiles.tiles.Tile;

public class TileMatrixGraphTest {
    @BeforeEach
    public void resetTileMatrixGraph() {
        matrixGraph = new TileMatrixGraph();
    }
    static TileMatrixGraph matrixGraph;

    @Test
    public void test_addTile_tileNotAlreadyOccupied() {
        Assertions.assertTrue(matrixGraph.isEmpty());
        Assertions.assertEquals(0, matrixGraph.size());

        Assertions.assertTrue(matrixGraph.addTile(0, 0, new Tile("tile")));
    }

    @Test
    public void test_addTile_tileAlreadyOccupied() {
        matrixGraph.addTile(0, 0, new Tile("tile"));

        Assertions.assertFalse(matrixGraph.isEmpty());
        Assertions.assertEquals(1, matrixGraph.size());

        Assertions.assertFalse(matrixGraph.addTile(0, 0, new Tile("new tile")));

        Assertions.assertFalse(matrixGraph.isEmpty());
        Assertions.assertEquals(1, matrixGraph.size());
    }


    @Test
    public void test_removeTile() {
        matrixGraph.addTile(0, 0, new Tile("tile"));

        Assertions.assertFalse(matrixGraph.isEmpty());
        Assertions.assertEquals(1, matrixGraph.size());

        matrixGraph.removeTile(0, 0);

        Assertions.assertTrue(matrixGraph.isEmpty());
        Assertions.assertEquals(0, matrixGraph.size());
    }


    @Test
    public void test_getTile() {
        Tile tile = new Tile("tile");
        matrixGraph.addTile(0, 0, tile);

        Tile actualTile = matrixGraph.getTile(0, 0);

        Assertions.assertEquals(tile, actualTile);
    }


    @Test
    public void test_setTile_exists() {
        Tile tile = new Tile("tile");
        matrixGraph.addTile(0, 0, tile);

        Tile newTile = new Tile("new tile");
        matrixGraph.setTile(0, 0, newTile);

        Assertions.assertEquals(newTile, matrixGraph.getTile(0, 0));
    }

    @Test
    public void test_setTile_doesNotExist() {
        Tile newTile = new Tile("new tile");
        matrixGraph.setTile(0, 0, newTile);

        Assertions.assertEquals(newTile, matrixGraph.getTile(0, 0));
    }


    @Test
    public void test_linkTiles_eastWest() {
        Tile originTile = new Tile("origin");
        Tile eastTile = new Tile("east");

        matrixGraph.addTile(0, 0, originTile);
        matrixGraph.addTile(1, 0, eastTile);

        matrixGraph.linkAllTileNodesByCoordinates();

        TileNode originNode = matrixGraph.getTileNode(0, 0);
        TileNode eastNode = matrixGraph.getTileNode(1, 0);

        Assertions.assertEquals(eastTile, originNode.getAdjacentNode(TileNodeDirection.EAST).getKey());
        Assertions.assertEquals(originTile, eastNode.getAdjacentNode(TileNodeDirection.WEST).getKey());
    }

    @Test
    public void test_linkTiles_allDirections_OriginTest() {
        addTilesToMatrixGraph();

        String expectedOriginNodeToString = """
                
                ** origin **
                north:     north
                northeast: northeast
                east:      east
                southeast: southeast
                south:     south
                southwest: southwest
                west:      west
                northwest: northwest
                """;
        String actualOriginNodeToString = matrixGraph.getTileNode(0, 0).toString();

        Assertions.assertEquals(expectedOriginNodeToString, actualOriginNodeToString);
    }
    private void addTilesToMatrixGraph() {
        matrixGraph.addTile(0, 0, new Tile("origin"));

        matrixGraph.addTile(0, 1, new Tile("north"));
        matrixGraph.addTile(1, 1, new Tile("northeast"));
        matrixGraph.addTile(1, 0, new Tile("east"));
        matrixGraph.addTile(1, -1, new Tile("southeast"));
        matrixGraph.addTile(0, -1, new Tile("south"));
        matrixGraph.addTile(-1, -1, new Tile("southwest"));
        matrixGraph.addTile(-1, 0, new Tile("west"));
        matrixGraph.addTile(-1, 1, new Tile("northwest"));

        matrixGraph.linkAllTileNodesByCoordinates();
    }

    @Test
    public void test_linkTiles_Bidirectional_north() {
        addTilesToMatrixGraph();

        TileNode originNode = matrixGraph.getTileNode(0, 0);
        TileNode bidirectedOriginNode = originNode
                .getAdjacentNode(TileNodeDirection.NORTH)
                .getAdjacentNode(TileNodeDirection.SOUTH);

        Assertions.assertEquals(originNode, bidirectedOriginNode);
    }

    @Test
    public void test_linkTiles_cornerAdjacentToSides_NE() {
        addTilesToMatrixGraph();

        TileNode northeastNode = matrixGraph.getTileNode(1, 1);
        TileNode northNode = matrixGraph.getTileNode(0, 1);
        TileNode eastNode = matrixGraph.getTileNode(1, 0);

        Assertions.assertEquals(northNode, northeastNode.getAdjacentNode(TileNodeDirection.WEST));
        Assertions.assertEquals(eastNode, northeastNode.getAdjacentNode(TileNodeDirection.SOUTH));
    }

    @Test
    public void test_linkTiles_sidesAdjacentToCorner_NE() {
        addTilesToMatrixGraph();

        TileNode northeastNode = matrixGraph.getTileNode(1, 1);
        TileNode northNode = matrixGraph.getTileNode(0, 1);
        TileNode eastNode = matrixGraph.getTileNode(1, 0);

        Assertions.assertEquals(northeastNode, northNode.getAdjacentNode(TileNodeDirection.EAST));
        Assertions.assertEquals(northeastNode, eastNode.getAdjacentNode(TileNodeDirection.NORTH));
    }

    @Test
    public void test_linkTiles_allDirections_bidirectional() {
        addTilesToMatrixGraph();

        for (int i = 0; i < TileNodeDirection.values().length; i++) {
            TileNodeDirection currentDirection = TileNodeDirection.fromOrdinal(i);
            TileNode originNode = matrixGraph.getTileNode(0, 0);

            assert currentDirection != null;
            Assertions.assertEquals(originNode,
                    originNode.getAdjacentNode(currentDirection)
                            .getAdjacentNode(currentDirection.opposite())
            );
        }
    }
}
