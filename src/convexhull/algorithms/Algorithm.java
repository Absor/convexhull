package convexhull.algorithms;

import convexhull.datastructures.LinkedList;

/**
 *
 * @author Heikki Haapala
 */
public interface Algorithm {

    /**
     *
     * @param points
     * @return
     */
    public LinkedList useAlgorithm(LinkedList points);
}
