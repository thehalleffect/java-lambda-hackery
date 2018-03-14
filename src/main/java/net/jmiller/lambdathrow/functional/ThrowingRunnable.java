package net.jmiller.lambdathrow.functional;

/**
 * hacking around java's lambda idiosyncracies
 *
 * borrowed from https://stackoverflow.com/a/27661504
 * @param <E>
 */
@FunctionalInterface
public interface ThrowingRunnable<E extends Exception> {
    void run() throws E;
}
