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
        eastNode = new SimpleTileNode(new Tile("east"));
        southNode = new SimpleTileNode(new Tile("south"));
        westNode = new SimpleTileNode(new Tile("west"));
    }
    static SimpleTileNode originNode, northNode, eastNode, southNode, westNode;


    @Test
    public void test_setAdjacentNode_north_nonBidirectional() {
        originNode.setAdjacentNode(northNode, TileNodeDirection.NORTH, false);

        SimpleTileNode expectedNode = northNode;
        SimpleTileNode actualNode = (SimpleTileNode) originNode.getAdjacentNode(TileNodeDirection.NORTH);

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
    public void test_setAdjacentNode_south_nonBidirectional() {
        originNode.setAdjacentNode(southNode, TileNodeDirection.SOUTH, false);

        SimpleTileNode expectedNode = southNode;
        SimpleTileNode actualNode = (SimpleTileNode) originNode.getAdjacentNode(TileNodeDirection.SOUTH);

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
    public void test_setAdjacentNode_west_bidirectional() {
        originNode.setAdjacentNode(eastNode, TileNodeDirection.WEST, true);

        SimpleTileNode expectedNode = originNode;
        SimpleTileNode actualNode =
                (SimpleTileNode) originNode
                .getAdjacentNode(TileNodeDirection.WEST)
                .getAdjacentNode(TileNodeDirection.WEST.opposite());

        Assertions.assertEquals(expectedNode, actualNode);
    }


    @Test
    public void test_setAllAdjacentNodes_toString_noNull() {
        connectOriginAdjacents();

        String expectedToString = """
                
                ** origin **
                north: north
                east:  east
                south: south
                west:  west
                """;
        String actualToString = originNode.toString();

        Assertions.assertEquals(expectedToString, actualToString);
    }
    private void connectOriginAdjacents() {
        originNode.setAdjacentNode(northNode, TileNodeDirection.NORTH, true);
        originNode.setAdjacentNode(eastNode, TileNodeDirection.EAST, true);
        originNode.setAdjacentNode(southNode, TileNodeDirection.SOUTH, true);
        originNode.setAdjacentNode(westNode, TileNodeDirection.WEST, true);
    }

    @Test
    public void test_setAllAdjacentNodes_toString_Null() {
        connectOriginAdjacents();
        originNode.clearAdjacentNodes();

        String expectedToString = """
                
                ** origin **
                north: null
                east:  null
                south: null
                west:  null
                """;
        String actualToString = originNode.toString();

        Assertions.assertEquals(expectedToString, actualToString);
    }


}
