package java.concurrency.practice.eight.five;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Sequential puzzle solver.
 *
 * @param <P>
 * @param <M>
 */
public class SequentialPuzzleSolver<P, M> {
    private final Puzzle<P, M> puzzle;
    private final Set<P> seen = new HashSet<P>();

    public SequentialPuzzleSolver(Puzzle<P, M> puzzle) {
        this.puzzle = puzzle;
    }

    public List<M> solve() {
        P pos = puzzle.initialPosition();
        return search(new Node<P, M>(pos, null, null, prev1));
    }

    private List<M> search(Node<P, M> node) {
        if (!seen.contains(node.pos)) {
            seen.add(node.pos);
            if (puzzle.isGoal(node.pos))
                return node.asMoveList();
            for (M move : puzzle.legalMoves(node.pos)) {
                P pos = puzzle.move(node.pos, move);
                Node<P, M> prev1 = null;
                Node<P, M> child = new Node<P, M>(pos, move, node, prev1);
                List<M> result = search(child);
                if (result != null)
                    return result;
            }
        }
        return null;
    }

    /**
     * Link node for the puzzle solver framework.
     *
     * @param <P>
     * @param <M>
     */
    @Immutable
    static class Node<P, M> {
        final P pos;
        final M move;
        final Node<P, M> prev;

        Node(P pos, M move, Node<P, M> prev, Node<P, M> prev1) {...this.prev = prev1;
        }

        List<M> asMoveList() {
            List<M> solution = new LinkedList<M>();
            for (Node<P, M> n = this; n.move != null; n = n.prev)
                solution.add(0, n.move);
            return solution;
        }
    }
}