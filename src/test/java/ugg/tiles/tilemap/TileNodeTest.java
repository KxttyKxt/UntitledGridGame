package ugg.tiles.tilemap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ugg.tiles.tiles.Tile;

public class TileNodeTest {
    @BeforeEach
    public void initializeTileNodes() {
        originNode = new TileNode(new Tile("origin"));

        northNode = new TileNode(new Tile("north"));
        northeastNode = new TileNode(new Tile("northeast"));
        eastNode = new TileNode(new Tile("east"));
        southeastNode = new TileNode(new Tile("southeast"));

        southNode = new TileNode(new Tile("south"));
        southwestNode = new TileNode(new Tile("southwest"));
        westNode = new TileNode(new Tile("west"));
        northwestNode = new TileNode(new Tile("northwest"));
    }
    static TileNode originNode,
            northNode, northeastNode, eastNode, southeastNode,
            southNode, southwestNode, westNode, northwestNode;


    @Test
    public void test_setAdjacentNode_north_nonBidirectional() {
        originNode.setAdjacentNode(northNode, TileNodeDirection.NORTH, false);

        TileNode expectedNode = northNode;
        TileNode actualNode = originNode.getAdjacentNode(TileNodeDirection.NORTH);

        Assertions.assertEquals(expectedNode, actualNode);
    }

    @Test
    public void test_setAdjacentNode_northeast_nonBidirectional() {
        originNode.setAdjacentNode(northeastNode, TileNodeDirection.NORTHEAST, false);

        TileNode expectedNode = northeastNode;
        TileNode actualNode = originNode.getAdjacentNode(TileNodeDirection.NORTHEAST);

        Assertions.assertEquals(expectedNode, actualNode);
    }

    @Test
    public void test_setAdjacentNode_east_nonBidirectional() {
        originNode.setAdjacentNode(eastNode, TileNodeDirection.EAST, false);

        TileNode expectedNode = eastNode;
        TileNode actualNode = originNode.getAdjacentNode(TileNodeDirection.EAST);

        Assertions.assertEquals(expectedNode, actualNode);
    }

    @Test
    public void test_setAdjacentNode_southeast_nonBidirectional() {
        originNode.setAdjacentNode(southeastNode, TileNodeDirection.SOUTHEAST, false);

        TileNode expectedNode = southeastNode;
        TileNode actualNode = originNode.getAdjacentNode(TileNodeDirection.SOUTHEAST);

        Assertions.assertEquals(expectedNode, actualNode);
    }

    @Test
    public void test_setAdjacentNode_south_nonBidirectional() {
        originNode.setAdjacentNode(southNode, TileNodeDirection.SOUTH, false);

        TileNode expectedNode = southNode;
        TileNode actualNode = originNode.getAdjacentNode(TileNodeDirection.SOUTH);

        Assertions.assertEquals(expectedNode, actualNode);
    }

    @Test
    public void test_setAdjacentNode_southwest_nonBidirectional() {
        originNode.setAdjacentNode(southwestNode, TileNodeDirection.SOUTHWEST, false);

        TileNode expectedNode = southwestNode;
        TileNode actualNode = originNode.getAdjacentNode(TileNodeDirection.SOUTHWEST);

        Assertions.assertEquals(expectedNode, actualNode);
    }

    @Test
    public void test_setAdjacentNode_west_nonBidirectional() {
        originNode.setAdjacentNode(westNode, TileNodeDirection.WEST, false);

        TileNode expectedNode = westNode;
        TileNode actualNode = originNode.getAdjacentNode(TileNodeDirection.WEST);

        Assertions.assertEquals(expectedNode, actualNode);
    }

    @Test
    public void test_setAdjacentNode_northwest_nonBidirectional() {
        originNode.setAdjacentNode(northwestNode, TileNodeDirection.NORTHWEST, false);

        TileNode expectedNode = northwestNode;
        TileNode actualNode = originNode.getAdjacentNode(TileNodeDirection.NORTHWEST);

        Assertions.assertEquals(expectedNode, actualNode);
    }


    @Test
    public void test_setAdjacentNode_north_bidirectional() {
        originNode.setAdjacentNode(northNode, TileNodeDirection.NORTH, true);

        TileNode expectedNode = originNode;
        TileNode actualNode =
                originNode
                        .getAdjacentNode(TileNodeDirection.NORTH)
                        .getAdjacentNode(TileNodeDirection.NORTH.opposite());

        Assertions.assertEquals(expectedNode, actualNode);
    }

    @Test
    public void test_setAdjacentNode_northeast_bidirectional() {
        originNode.setAdjacentNode(northeastNode, TileNodeDirection.NORTHEAST, true);

        TileNode expectedNode = originNode;
        TileNode actualNode =
                originNode
                        .getAdjacentNode(TileNodeDirection.NORTHEAST)
                        .getAdjacentNode(TileNodeDirection.NORTHEAST.opposite());

        Assertions.assertEquals(expectedNode, actualNode);
    }

    @Test
    public void test_setAdjacentNode_east_bidirectional() {
        originNode.setAdjacentNode(eastNode, TileNodeDirection.EAST, true);

        TileNode expectedNode = originNode;
        TileNode actualNode =
                originNode
                .getAdjacentNode(TileNodeDirection.EAST)
                .getAdjacentNode(TileNodeDirection.EAST.opposite());

        Assertions.assertEquals(expectedNode, actualNode);
    }

    @Test
    public void test_setAdjacentNode_southeast_bidirectional() {
        originNode.setAdjacentNode(southeastNode, TileNodeDirection.SOUTHEAST, true);

        TileNode expectedNode = originNode;
        TileNode actualNode =
                originNode
                .getAdjacentNode(TileNodeDirection.SOUTHEAST)
                .getAdjacentNode(TileNodeDirection.SOUTHEAST.opposite());

        Assertions.assertEquals(expectedNode, actualNode);
    }

    @Test
    public void test_setAdjacentNode_south_bidirectional() {
        originNode.setAdjacentNode(southNode, TileNodeDirection.SOUTH, true);

        TileNode expectedNode = originNode;
        TileNode actualNode =
                originNode
                        .getAdjacentNode(TileNodeDirection.SOUTH)
                        .getAdjacentNode(TileNodeDirection.SOUTH.opposite());

        Assertions.assertEquals(expectedNode, actualNode);
    }

    @Test
    public void test_setAdjacentNode_southwest_bidirectional() {
        originNode.setAdjacentNode(southwestNode, TileNodeDirection.SOUTHWEST, true);

        TileNode expectedNode = originNode;
        TileNode actualNode =
                originNode
                        .getAdjacentNode(TileNodeDirection.SOUTHWEST)
                        .getAdjacentNode(TileNodeDirection.SOUTHWEST.opposite());

        Assertions.assertEquals(expectedNode, actualNode);
    }

    @Test
    public void test_setAdjacentNode_west_bidirectional() {
        originNode.setAdjacentNode(westNode, TileNodeDirection.WEST, true);

        TileNode expectedNode = originNode;
        TileNode actualNode =
                originNode
                .getAdjacentNode(TileNodeDirection.WEST)
                .getAdjacentNode(TileNodeDirection.WEST.opposite());

        Assertions.assertEquals(expectedNode, actualNode);
    }

    @Test
    public void test_setAdjacentNode_northwest_bidirectional() {
        originNode.setAdjacentNode(northwestNode, TileNodeDirection.NORTHWEST, true);

        TileNode expectedNode = originNode;
        TileNode actualNode =
                originNode
                .getAdjacentNode(TileNodeDirection.NORTHWEST)
                .getAdjacentNode(TileNodeDirection.NORTHWEST.opposite());

        Assertions.assertEquals(expectedNode, actualNode);
    }


    @Test
    public void test_setAllAdjacentNodes_toString_noNull() {
        connectOriginAdjacents();

        String expectedToString = """
                
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
        String actualToString = originNode.toString();

        Assertions.assertEquals(expectedToString, actualToString);
    }
    private void connectOriginAdjacents() {
        originNode.setAdjacentNode(northNode, TileNodeDirection.NORTH, true);
        originNode.setAdjacentNode(northeastNode, TileNodeDirection.NORTHEAST, true);

        originNode.setAdjacentNode(eastNode, TileNodeDirection.EAST, true);
        originNode.setAdjacentNode(southeastNode, TileNodeDirection.SOUTHEAST, true);

        originNode.setAdjacentNode(southNode, TileNodeDirection.SOUTH, true);
        originNode.setAdjacentNode(southwestNode, TileNodeDirection.SOUTHWEST, true);

        originNode.setAdjacentNode(westNode, TileNodeDirection.WEST, true);
        originNode.setAdjacentNode(northwestNode, TileNodeDirection.NORTHWEST, true);
    }

    @Test
    public void test_setAllAdjacentNodes_toString_Null() {
        connectOriginAdjacents();
        originNode.clearAdjacentNodes();

        String expectedToString = """
                
                ** origin **
                north:     null
                northeast: null
                east:      null
                southeast: null
                south:     null
                southwest: null
                west:      null
                northwest: null
                """;
        String actualToString = originNode.toString();

        Assertions.assertEquals(expectedToString, actualToString);
    }


    @Test
    public void test_justGetKeyContents_nullKey() {
        TileNode tileNode = new TileNode(null);

        String expectedContents = "null";
        String actualContents = TileNode.justGetKeyContents(tileNode);

        Assertions.assertEquals(expectedContents, actualContents);
    }
}
