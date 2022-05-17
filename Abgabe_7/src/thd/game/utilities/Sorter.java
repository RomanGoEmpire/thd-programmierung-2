package thd.game.utilities;

import thd.gameobjects.base.Position;

import java.util.ArrayList;
import java.util.Collections;
import thd.gameview.GameView;

import java.util.*;

/**
 * Sorter-Class.
 */
class Sorter {
    public static void main(String[] args) {
        ArrayList<Position> positions = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            double xPos = (Math.round(random.nextDouble(GameView.WIDTH) * 100)) / 100d;
            double yPos = (Math.round(random.nextDouble(GameView.HEIGHT) * 100)) / 100d;
            Position x = new Position(xPos, yPos);
            positions.add(x);
        }

        Sorter sorter = new Sorter();
        sorter.xOrder(positions);
        System.out.println(positions);
        sorter.yOrder(positions);
        System.out.println(positions);
        sorter.naturalOrder(positions);
        System.out.println(positions);
        sorter.centricOrder(positions);
        System.out.println(positions);
    }


    void naturalOrder(ArrayList<Position> positions) {
        Collections.sort(positions);
    }

    void xOrder(ArrayList<Position> positions) {
        class Xcompare implements Comparator<Position> {
            @Override
            public int compare(Position o1, Position o2) {
                return Double.compare(o1.x, o2.x);
            }
        }
        Xcompare xCompare = new Xcompare();
        positions.sort(xCompare);
    }

    void yOrder(ArrayList<Position> positions) {
        Comparator<Position> xCompare = new Comparator<>() {
            @Override
            public int compare(Position o1, Position o2) {
                return Double.compare(o1.y, o2.y);
            }
        };
        positions.sort(xCompare);
    }

    void centricOrder(ArrayList<Position> positions) {
        Position centricPoint = new Position(GameView.WIDTH / 2d, GameView.HEIGHT / 2d);
        Comparator<Position> centricComparator = (p1, p2) -> Double.compare(p1.distance(centricPoint), p2.distance(centricPoint));
        positions.sort(centricComparator);
    }
}
