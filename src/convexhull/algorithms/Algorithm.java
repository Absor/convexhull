package convexhull.algorithms;

import convexhull.datastructures.LinkedList;

/**
 * Interface for all algorithms and Akl-Toussaint heuristics.
 *
 * @author Heikki Haapala
 */
public interface Algorithm {

    /**
     * Gets a set of points, processes them and returns the result set.
     *
     * @param points    set of points to run algorithm on
     * @return          result set of points after algorithm has handled the
     *                   input set
     */
    public LinkedList useAlgorithm(LinkedList points);
}
