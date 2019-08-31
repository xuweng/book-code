package java.concurrency.practice.eight.five;

import java.util.Set;

/**
 * Abstraction for puzzles like the “sliding blocks puzzle”.
 *
 * @param <P>
 * @param <M>
 */
public interface Puzzle<P, M> {
    P initialPosition();

    boolean isGoal(P position);

    Set<M> legalMoves(P position);

    P move(P position, M move);
}
