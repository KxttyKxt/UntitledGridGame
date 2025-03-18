package util;

import com.google.common.collect.Maps;
import core.Point2D;

import java.util.Map;

public class Convert {
    public static <T> Map<Point2D, T> matrixToPointMap(T[][] matrix) {
        Map<Point2D, T> toReturn = Maps.newHashMap();
        int xMax = matrix[0].length - 1;
        int yMax = matrix.length - 1;

        for (int x = 0; x <= xMax; x++)
            for (int y = 0; y <= yMax; y++)
                toReturn.put(Point2D.of(x, y), matrix[y][x]);

        return toReturn;
    }
}
