package ugg.tiles.graph.nodes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ugg.tiles.graph.TileNodeDirection;
import ugg.tiles.tiles.Tile;

public class SimpleTileNodeTest {
    @BeforeEach
    public void initializeTileNodes() {
        originNode = new SimpleTileNode(new Tile("origin"));

        northNode = new SimpleTileNode(new Tile("north"));
        northeastNode = new SimpleTileNode(new Tile("northeast"));
        eastNode = new SimpleTileNode(new Tile("east"));
        southeastNode = new SimpleTileNode(new Tile("southeast"));

        southNode = new SimpleTileNode(new Tile("south"));
        southwestNode = new SimpleTileNode(new Tile("southwest"));
        westNode = new SimpleTileNode(new Tile("west"));
        northwestNode = new SimpleTileNode(new Tile("northwest"));
    }
    static SimpleTileNode originNode,
            northNode, northeastNode, eastNode, southeastNode,
            southNode, southwestNode, westNode, northwestNode;


    @Test
    public void test_setAdjacentNode_north_nonBidirectional() {
        originNode.setAdjacentNode(northNode, TileNodeDirection.NORTH, false);

        SimpleTileNode expectedNode = northNode;
        SimpleTileNode actualNode = (SimpleTileNode) originNode.getAdjacentNode(TileNodeDirection.NORTH);

        Assertions.assertEquals(expectedNode, actualNode);
    }

    @Test
    public void test_setAdjacentNode_northeast_nonBidirectional() {
        originNode.setAdjacentNode(northeastNode, TileNodeDirection.NORTHEAST, false);

        SimpleTileNode expectedNode = northeastNode;
        SimpleTileNode actualNode = (SimpleTileNode) originNode.getAdjacentNode(TileNodeDirection.NORTHEAST);

        Assertions.assertEquals(expectedNode, actualNode);
    }

    @Test
    public void test_setAdjacentNode_east_nonBidirectional() {
        originNode.setAdjacentNode(eastNode, TileNodeDirection.EAST, false);

        SimpleTileNode expectedNode = eastNode;
        SimpleTileNode actualNode = (SimpleTileNode) originNode.getAdjacentNode(TileNodeDirection.EAST);

        Assertions.assertEquals(expectedNode, actualNode);
    }

    @Test
    public void test_setAdjacentNode_southeast_nonBidirectional() {
        originNode.setAdjacentNode(southeastNode, TileNodeDirection.SOUTHEAST, false);

        SimpleTileNode expectedNode = southeastNode;
        SimpleTileNode actualNode = (SimpleTileNode) originNode.getAdjacentNode(TileNodeDirection.SOUTHEAST);

        Assertions.assertEquals(expectedNode, actualNode);
    }

    @Test
    public void test_setAdjacentNode_south_nonBidirectional() {
        originNode.setAdjacentNode(southNode, TileNodeDirection.SOUTH, false);

        SimpleTileNode expectedNode = southNode;
        SimpleTileNode actualNode = (SimpleTileNode) originNode.getAdjacentNode(TileNodeDirection.SOUTH);

        Assertions.assertEquals(expectedNode, actualNode);
    }

    @Test
    public void test_setAdjacentNode_southwest_nonBidirectional() {
        originNode.setAdjacentNode(southwestNode, TileNodeDirection.SOUTHWEST, false);

        SimpleTileNode expectedNode = southwestNode;
        SimpleTileNode actualNode = (SimpleTileNode) originNode.getAdjacentNode(TileNodeDirection.SOUTHWEST);

        Assertions.assertEquals(expectedNode, actualNode);
    }

    @Test
    public void test_setAdjacentNode_west_nonBidirectional() {
        originNode.setAdjacentNode(westNode, TileNodeDirection.WEST, false);

        SimpleTileNode expectedNode = westNode;
        SimpleTileNode actualNode = (SimpleTileNode) originNode.getAdjacentNode(TileNodeDirection.WEST);

        Assertions.assertEquals(expectedNode, actualNode);
    }

    @Test
    public void test_setAdjacentNode_northwest_nonBidirectional() {
        originNode.setAdjacentNode(northwestNode, TileNodeDirection.NORTHWEST, false);

        SimpleTileNode expectedNode = northwestNode;
        SimpleTileNode actualNode = (SimpleTileNode) originNode.getAdjacentNode(TileNodeDirection.NORTHWEST);

        Assertions.assertEquals(expectedNode, actualNode);
    }


    @Test
    public void test_setAdjacentNode_north_bidirectional() {
        originNode.setAdjacentNode(northNode, TileNodeDirection.NORTH, true);

        SimpleTileNode expectedNode = originNode;
        SimpleTileNode actualNode =
                (SimpleTileNode) originNode
                        .getAdjacentNode(TileNodeDirection.NORTH)
                        .getAdjacentNode(TileNodeDirection.NORTH.opposite());

        Assertions.assertEquals(expectedNode, actualNode);
    }

    @Test
    public void test_setAdjacentNode_northeast_bidirectional() {
        originNode.setAdjacentNode(northeastNode, TileNodeDirection.NORTHEAST, true);

        SimpleTileNode expectedNode = originNode;
        SimpleTileNode actualNode =
                (SimpleTileNode) originNode
                        .getAdjacentNode(TileNodeDirection.NORTHEAST)
                        .getAdjacentNode(TileNodeDirection.NORTHEAST.opposite());

        Assertions.assertEquals(expectedNode, actualNode);
    }

    @Test
    public void test_setAdjacentNode_east_bidirectional() {
        originNode.setAdjacentNode(eastNode, TileNodeDirection.EAST, true);

        SimpleTileNode expectedNode = originNode;
        SimpleTileNode actualNode =
                (SimpleTileNode) originNode
                .getAdjacentNode(TileNodeDirection.EAST)
                .getAdjacentNode(TileNodeDirection.EAST.opposite());

        Assertions.assertEquals(expectedNode, actualNode);
    }

    @Test
    public void test_setAdjacentNode_southeast_bidirectional() {
        originNode.setAdjacentNode(southeastNode, TileNodeDirection.SOUTHEAST, true);

        SimpleTileNode expectedNode = originNode;
        SimpleTileNode actualNode =
                (SimpleTileNode) originNode
                .getAdjacentNode(TileNodeDirection.SOUTHEAST)
                .getAdjacentNode(TileNodeDirection.SOUTHEAST.opposite());

        Assertions.assertEquals(expectedNode, actualNode);
    }

    @Test
    public void test_setAdjacentNode_south_bidirectional() {
        originNode.setAdjacentNode(southNode, TileNodeDirection.SOUTH, true);

        SimpleTileNode expectedNode = originNode;
        SimpleTileNode actualNode =
                (SimpleTileNode) originNode
                        .getAdjacentNode(TileNodeDirection.SOUTH)
                        .getAdjacentNode(TileNodeDirection.SOUTH.opposite());

        Assertions.assertEquals(expectedNode, actualNode);
    }

    @Test
    public void test_setAdjacentNode_southwest_bidirectional() {
        originNode.setAdjacentNode(southwestNode, TileNodeDirection.SOUTHWEST, true);

        SimpleTileNode expectedNode = originNode;
        SimpleTileNode actualNode =
                (SimpleTileNode) originNode
                        .getAdjacentNode(TileNodeDirection.SOUTHWEST)
                        .getAdjacentNode(TileNodeDirection.SOUTHWEST.opposite());

        Assertions.assertEquals(expectedNode, actualNode);
    }

    @Test
    public void test_setAdjacentNode_west_bidirectional() {
        originNode.setAdjacentNode(westNode, TileNodeDirection.WEST, true);

        SimpleTileNode expectedNode = originNode;
        SimpleTileNode actualNode =
                (SimpleTileNode) originNode
                .getAdjacentNode(TileNodeDirection.WEST)
                .getAdjacentNode(TileNodeDirection.WEST.opposite());

        Assertions.assertEquals(expectedNode, actualNode);
    }

    @Test
    public void test_setAdjacentNode_northwest_bidirectional() {
        originNode.setAdjacentNode(northwestNode, TileNodeDirection.NORTHWEST, true);

        SimpleTileNode expectedNode = originNode;
        SimpleTileNode actualNode =
                (SimpleTileNode) originNode
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
        TileNode tileNode = new SimpleTileNode(null);

        String expectedContents = "null";
        String actualContents = TileNode.justGetKeyContents(tileNode);

        Assertions.assertEquals(expectedContents, actualContents);
    }
}
