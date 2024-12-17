package ugg.tiles.graph;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ugg.tiles.tiles.Tile;

public class TileMatrixGraphTest {
    @BeforeEach
    public void resetTileMatrixGraph() {
        matrixGraph = new TileMatrixGraph();
    }
    static TileMatrixGraph matrixGraph;

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

        matrixGraph.updateTileNodeLinks();
    }
    private void addEastLineToMatrixGraph() {
        Tile origin = new Tile("Data");
        Tile east = new Tile("");
        Tile eastEast = new Tile("");

        matrixGraph.addTile(0, 0, origin);
        matrixGraph.addTile(1, 0, east);
        matrixGraph.addTile(2, 0, eastEast);
    }


    @Test
    public void test_addTile_tileNotOccupied() {
        Assertions.assertTrue(matrixGraph.isEmpty());
        Assertions.assertEquals(0, matrixGraph.size());

        Assertions.assertTrue(matrixGraph.addTile(0, 0, new Tile("getTile")));
    }

    @Test
    public void test_addTile_tileAlreadyOccupied() {
        matrixGraph.addTile(0, 0, new Tile("getTile"));

        Assertions.assertFalse(matrixGraph.isEmpty());
        Assertions.assertEquals(1, matrixGraph.size());

        Assertions.assertFalse(matrixGraph.addTile(0, 0, new Tile("new getTile")));

        Assertions.assertFalse(matrixGraph.isEmpty());
        Assertions.assertEquals(1, matrixGraph.size());
    }


    @Test
    public void test_removeTile() {
        matrixGraph.addTile(0, 0, new Tile("getTile"));

        Assertions.assertFalse(matrixGraph.isEmpty());
        Assertions.assertEquals(1, matrixGraph.size());

        matrixGraph.removeTile(0, 0);

        Assertions.assertTrue(matrixGraph.isEmpty());
        Assertions.assertEquals(0, matrixGraph.size());
    }


    @Test
    public void test_getTile() {
        Tile tile = new Tile("getTile");
        matrixGraph.addTile(0, 0, tile);

        Tile actualTile = matrixGraph.getTile(0, 0);

        Assertions.assertEquals(tile, actualTile);
    }


    @Test
    public void test_setTile_overExisting() {
        Tile tile = new Tile("getTile");
        matrixGraph.addTile(0, 0, tile);

        Tile newTile = new Tile("new getTile");
        matrixGraph.setTile(0, 0, newTile);

        Assertions.assertEquals(newTile, matrixGraph.getTile(0, 0));
    }

    @Test
    public void test_setTile_new() {
        Tile newTile = new Tile("new getTile");
        matrixGraph.setTile(0, 0, newTile);

        Assertions.assertEquals(newTile, matrixGraph.getTile(0, 0));
    }


    @Test
    public void test_linkTiles_eastWest() {
        Tile originTile = new Tile("origin");
        Tile eastTile = new Tile("east");

        matrixGraph.addTile(0, 0, originTile);
        matrixGraph.addTile(1, 0, eastTile);

        matrixGraph.updateTileNodeLinks();

        TileNode originNode = matrixGraph.getTileNode(0, 0);
        TileNode eastNode = matrixGraph.getTileNode(1, 0);

        Assertions.assertEquals(eastTile, originNode.getAdjacentNode(TileNodeDirection.EAST).getTile());
        Assertions.assertEquals(originTile, eastNode.getAdjacentNode(TileNodeDirection.WEST).getTile());
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


    @Test
    public void test_transferTileContents_manual() {
        addEastLineToMatrixGraph();

        Assertions.assertEquals("Data", matrixGraph.getTile(0, 0).toString());
        matrixGraph.getTile(0, 0).transferContentsTo(matrixGraph.getTile(1, 0));
        Assertions.assertEquals("Data", matrixGraph.getTile(1, 0).toString());
        matrixGraph.getTile(1, 0).transferContentsTo(matrixGraph.getTile(2, 0));
        Assertions.assertEquals("Data", matrixGraph.getTile(2, 0).toString());
    }

    @Test
    public void test_moveTileContentsByCoords_true() {
        addEastLineToMatrixGraph();

        TileNodeDirection[] directions = {TileNodeDirection.EAST, TileNodeDirection.EAST};
        Assertions.assertTrue(matrixGraph.moveTileContentsByCoords(0, 0, directions));

        Assertions.assertEquals("Data", matrixGraph.getTile(2, 0).toString());
    }

    @Test
    public void test_moveTileContentsByCoords_true_nullBetween() {
        addEastLineToMatrixGraph();
        matrixGraph.removeTile(1, 0);

        TileNodeDirection[] directions = {TileNodeDirection.EAST, TileNodeDirection.EAST};
        Assertions.assertTrue(matrixGraph.moveTileContentsByCoords(0, 0, directions));

        Assertions.assertEquals("Data", matrixGraph.getTile(2, 0).toString());
    }

    @Test
    public void test_moveTileContentsByCoords_false() {
        addEastLineToMatrixGraph();

        matrixGraph.setTile(2, 0, new Tile("Occupied"));

        TileNodeDirection[] directions = {TileNodeDirection.EAST, TileNodeDirection.EAST};
        Assertions.assertFalse(matrixGraph.moveTileContentsByCoords(0, 0, directions));

        Assertions.assertEquals("Occupied", matrixGraph.getTile(2, 0).toString());
    }


    @Test
    public void test_moveTileContentsContiguously_true() {
        addEastLineToMatrixGraph();

        int x = 0; int y = 0;
        TileNodeDirection[] directions = {TileNodeDirection.EAST, TileNodeDirection.EAST};

        Assertions.assertTrue(matrixGraph.moveTileContentsContiguously(x, y, directions));

        Assertions.assertEquals("", matrixGraph.getTile(0, 0).toString());
        Assertions.assertEquals("", matrixGraph.getTile(1, 0).toString());
        Assertions.assertEquals("Data", matrixGraph.getTile(2, 0).toString());
    }

    @Test
    public void test_moveTileContentsContiguously_falseByNullTile() {
        addEastLineToMatrixGraph();
        matrixGraph.removeTile(1, 0);

        int x = 0; int y = 0;
        TileNodeDirection[] directions = {TileNodeDirection.EAST, TileNodeDirection.EAST};

        Assertions.assertFalse(matrixGraph.moveTileContentsContiguously(x, y, directions));
    }

    @Test
    public void test_moveTileContentsContiguously_falseByNotEmptyOnWay() {
        addEastLineToMatrixGraph();
        matrixGraph.setTile(1, 0, new Tile("Occupied"));

        int x = 0; int y = 0;
        TileNodeDirection[] directions = {TileNodeDirection.EAST, TileNodeDirection.EAST};

        Assertions.assertFalse(matrixGraph.moveTileContentsContiguously(x, y, directions));
    }

    @Test
    public void test_moveTileContentsContiguously_falseByNotEmptyAtEnd() {
        addEastLineToMatrixGraph();
        matrixGraph.setTile(2, 0, new Tile("Occupied"));

        int x = 0; int y = 0;
        TileNodeDirection[] directions = {TileNodeDirection.EAST, TileNodeDirection.EAST};

        Assertions.assertFalse(matrixGraph.moveTileContentsContiguously(x, y, directions));
    }


    @Test
    public void test_checkForContiguousPath_true() {
        addEastLineToMatrixGraph();

        TileNodeDirection[] directions = {TileNodeDirection.EAST, TileNodeDirection.EAST};

        Assertions.assertTrue(matrixGraph.checkForContiguousPath(0, 0, directions));
    }

    @Test
    public void test_checkForContiguousPath_false_wrongPathMakesNull() {
        addEastLineToMatrixGraph();

        TileNodeDirection[] directions = {TileNodeDirection.NORTH, TileNodeDirection.NORTH};

        Assertions.assertFalse(matrixGraph.checkForContiguousPath(0, 0, directions));
    }

    @Test
    public void test_checkForContiguousPath_false_tileInPathIsNotEmpty() {
        addEastLineToMatrixGraph();
        matrixGraph.setTile(1, 0, new Tile("In the way"));

        TileNodeDirection[] directions = {TileNodeDirection.EAST, TileNodeDirection.EAST};

        Assertions.assertFalse(matrixGraph.checkForContiguousPath(0, 0, directions));
    }
}
