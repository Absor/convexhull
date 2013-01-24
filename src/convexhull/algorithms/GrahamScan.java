package convexhull.algorithms;

import convexhull.comparators.AngleComparator;
import convexhull.datastructures.LinkedList;

/**
 *
 * @author Heikki Haapala
 */
public class GrahamScan implements Algorithm {

    /**
     *
     * @param points
     * @return
     */
    @Override
    public LinkedList useAlgorithm(LinkedList points) {
        AngleComparator angleComparator = new AngleComparator(null);
        points.sort(angleComparator);
        
        return null;
    }
}
