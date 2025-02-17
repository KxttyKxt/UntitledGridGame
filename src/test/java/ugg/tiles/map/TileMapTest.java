package ugg.tiles.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ugg.tiles.tiles.Tile;

public class TileMapTest {
    @BeforeEach
    public void resetTileMatrixGraph() {
        tileMap = new TileMap();
    }
    static TileMap tileMap;

    private void addTilesToMatrixGraph() {
        tileMap.addTile(0, 0, new Tile("origin"));

        tileMap.addTile(0, 1, new Tile("north"));
        tileMap.addTile(1, 1, new Tile("northeast"));
        tileMap.addTile(1, 0, new Tile("east"));
        tileMap.addTile(1, -1, new Tile("southeast"));
        tileMap.addTile(0, -1, new Tile("south"));
        tileMap.addTile(-1, -1, new Tile("southwest"));
        tileMap.addTile(-1, 0, new Tile("west"));
        tileMap.addTile(-1, 1, new Tile("northwest"));

        tileMap.updateTileNodeLinks();
    }
    private void addEastLineToMatrixGraph() {
        Tile origin = new Tile("Data");
        Tile east = new Tile("");
        Tile eastEast = new Tile("");

        tileMap.addTile(0, 0, origin);
        tileMap.addTile(1, 0, east);
        tileMap.addTile(2, 0, eastEast);
    }


    @Test
    public void test_addTile_tileNotOccupied() {
        Assertions.assertTrue(tileMap.isEmpty());
        Assertions.assertEquals(0, tileMap.size());

        Assertions.assertTrue(tileMap.addTile(0, 0, new Tile("getTile")));
    }

    @Test
    public void test_addTile_tileAlreadyOccupied() {
        tileMap.addTile(0, 0, new Tile("getTile"));

        Assertions.assertFalse(tileMap.isEmpty());
        Assertions.assertEquals(1, tileMap.size());

        Assertions.assertFalse(tileMap.addTile(0, 0, new Tile("new getTile")));

        Assertions.assertFalse(tileMap.isEmpty());
        Assertions.assertEquals(1, tileMap.size());
    }


    @Test
    public void test_removeTile() {
        tileMap.addTile(0, 0, new Tile("getTile"));

        Assertions.assertFalse(tileMap.isEmpty());
        Assertions.assertEquals(1, tileMap.size());

        tileMap.removeTile(0, 0);

        Assertions.assertTrue(tileMap.isEmpty());
        Assertions.assertEquals(0, tileMap.size());
    }


    @Test
    public void test_getTile() {
        Tile tile = new Tile("getTile");
        tileMap.addTile(0, 0, tile);

        Tile actualTile = tileMap.getTile(0, 0);

        Assertions.assertEquals(tile, actualTile);
    }


    @Test
    public void test_setTile_overExisting() {
        Tile tile = new Tile("getTile");
        tileMap.addTile(0, 0, tile);

        Tile newTile = new Tile("new getTile");
        tileMap.setTile(0, 0, newTile);

        Assertions.assertEquals(newTile, tileMap.getTile(0, 0));
    }

    @Test
    public void test_setTile_new() {
        Tile newTile = new Tile("new getTile");
        tileMap.setTile(0, 0, newTile);

        Assertions.assertEquals(newTile, tileMap.getTile(0, 0));
    }


    @Test
    public void test_linkTiles_eastWest() {
        Tile originTile = new Tile("origin");
        Tile eastTile = new Tile("east");

        tileMap.addTile(0, 0, originTile);
        tileMap.addTile(1, 0, eastTile);

        tileMap.updateTileNodeLinks();

        TileNode originNode = tileMap.getTileNode(0, 0);
        TileNode eastNode = tileMap.getTileNode(1, 0);

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
        String actualOriginNodeToString = tileMap.getTileNode(0, 0).toString();

        Assertions.assertEquals(expectedOriginNodeToString, actualOriginNodeToString);
    }

    @Test
    public void test_linkTiles_Bidirectional_north() {
        addTilesToMatrixGraph();

        TileNode originNode = tileMap.getTileNode(0, 0);
        TileNode bidirectedOriginNode = originNode
                .getAdjacentNode(TileNodeDirection.NORTH)
                .getAdjacentNode(TileNodeDirection.SOUTH);

        Assertions.assertEquals(originNode, bidirectedOriginNode);
    }

    @Test
    public void test_linkTiles_cornerAdjacentToSides_NE() {
        addTilesToMatrixGraph();

        TileNode northeastNode = tileMap.getTileNode(1, 1);
        TileNode northNode = tileMap.getTileNode(0, 1);
        TileNode eastNode = tileMap.getTileNode(1, 0);

        Assertions.assertEquals(northNode, northeastNode.getAdjacentNode(TileNodeDirection.WEST));
        Assertions.assertEquals(eastNode, northeastNode.getAdjacentNode(TileNodeDirection.SOUTH));
    }

    @Test
    public void test_linkTiles_sidesAdjacentToCorner_NE() {
        addTilesToMatrixGraph();

        TileNode northeastNode = tileMap.getTileNode(1, 1);
        TileNode northNode = tileMap.getTileNode(0, 1);
        TileNode eastNode = tileMap.getTileNode(1, 0);

        Assertions.assertEquals(northeastNode, northNode.getAdjacentNode(TileNodeDirection.EAST));
        Assertions.assertEquals(northeastNode, eastNode.getAdjacentNode(TileNodeDirection.NORTH));
    }

    @Test
    public void test_linkTiles_allDirections_bidirectional() {
        addTilesToMatrixGraph();

        for (int i = 0; i < TileNodeDirection.values().length; i++) {
            TileNodeDirection currentDirection = TileNodeDirection.fromOrdinal(i);
            TileNode originNode = tileMap.getTileNode(0, 0);

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

        Assertions.assertEquals("Data", tileMap.getTile(0, 0).toString());
        tileMap.getTile(0, 0).transferContentsTo(tileMap.getTile(1, 0));
        Assertions.assertEquals("Data", tileMap.getTile(1, 0).toString());
        tileMap.getTile(1, 0).transferContentsTo(tileMap.getTile(2, 0));
        Assertions.assertEquals("Data", tileMap.getTile(2, 0).toString());
    }

    @Test
    public void test_moveTileContentsByCoords_true() {
        addEastLineToMatrixGraph();

        TileNodeDirection[] directions = {TileNodeDirection.EAST, TileNodeDirection.EAST};
        Assertions.assertTrue(tileMap.moveTileContentsByCoords(0, 0, directions));

        Assertions.assertEquals("Data", tileMap.getTile(2, 0).toString());
    }

    @Test
    public void test_moveTileContentsByCoords_true_nullBetween() {
        addEastLineToMatrixGraph();
        tileMap.removeTile(1, 0);

        TileNodeDirection[] directions = {TileNodeDirection.EAST, TileNodeDirection.EAST};
        Assertions.assertTrue(tileMap.moveTileContentsByCoords(0, 0, directions));

        Assertions.assertEquals("Data", tileMap.getTile(2, 0).toString());
    }

    @Test
    public void test_moveTileContentsByCoords_false() {
        addEastLineToMatrixGraph();

        tileMap.setTile(2, 0, new Tile("Occupied"));

        TileNodeDirection[] directions = {TileNodeDirection.EAST, TileNodeDirection.EAST};
        Assertions.assertFalse(tileMap.moveTileContentsByCoords(0, 0, directions));

        Assertions.assertEquals("Occupied", tileMap.getTile(2, 0).toString());
    }


    @Test
    public void test_moveTileContentsContiguously_true() {
        addEastLineToMatrixGraph();

        int x = 0; int y = 0;
        TileNodeDirection[] directions = {TileNodeDirection.EAST, TileNodeDirection.EAST};

        Assertions.assertTrue(tileMap.moveTileContentsContiguously(x, y, directions));

        Assertions.assertEquals("", tileMap.getTile(0, 0).toString());
        Assertions.assertEquals("", tileMap.getTile(1, 0).toString());
        Assertions.assertEquals("Data", tileMap.getTile(2, 0).toString());
    }

    @Test
    public void test_moveTileContentsContiguously_falseByNullTile() {
        addEastLineToMatrixGraph();
        tileMap.removeTile(1, 0);

        int x = 0; int y = 0;
        TileNodeDirection[] directions = {TileNodeDirection.EAST, TileNodeDirection.EAST};

        Assertions.assertFalse(tileMap.moveTileContentsContiguously(x, y, directions));
    }

    @Test
    public void test_moveTileContentsContiguously_falseByNotEmptyOnWay() {
        addEastLineToMatrixGraph();
        tileMap.setTile(1, 0, new Tile("Occupied"));

        int x = 0; int y = 0;
        TileNodeDirection[] directions = {TileNodeDirection.EAST, TileNodeDirection.EAST};

        Assertions.assertFalse(tileMap.moveTileContentsContiguously(x, y, directions));
    }

    @Test
    public void test_moveTileContentsContiguously_falseByNotEmptyAtEnd() {
        addEastLineToMatrixGraph();
        tileMap.setTile(2, 0, new Tile("Occupied"));

        int x = 0; int y = 0;
        TileNodeDirection[] directions = {TileNodeDirection.EAST, TileNodeDirection.EAST};

        Assertions.assertFalse(tileMap.moveTileContentsContiguously(x, y, directions));
    }


    @Test
    public void test_checkForContiguousPath_true() {
        addEastLineToMatrixGraph();

        TileNodeDirection[] directions = {TileNodeDirection.EAST, TileNodeDirection.EAST};

        Assertions.assertTrue(tileMap.checkForContiguousPath(0, 0, directions));
    }

    @Test
    public void test_checkForContiguousPath_false_wrongPathMakesNull() {
        addEastLineToMatrixGraph();

        TileNodeDirection[] directions = {TileNodeDirection.NORTH, TileNodeDirection.NORTH};

        Assertions.assertFalse(tileMap.checkForContiguousPath(0, 0, directions));
    }

    @Test
    public void test_checkForContiguousPath_false_tileInPathIsNotEmpty() {
        addEastLineToMatrixGraph();
        tileMap.setTile(1, 0, new Tile("In the way"));

        TileNodeDirection[] directions = {TileNodeDirection.EAST, TileNodeDirection.EAST};

        Assertions.assertFalse(tileMap.checkForContiguousPath(0, 0, directions));
    }
}
