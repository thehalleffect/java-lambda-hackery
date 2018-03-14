package net.jmiller.lambdathrow.functional;

/**
 * hacking around java's lambda idiosyncracies
 *
 * borrowed from https://stackoverflow.com/a/27661504
 * @param <T>
 * @param <U>
 * @param <E>
 */
@FunctionalInterface
public interface ThrowingBiConsumer<T, U, E extends Exception> {
    void accept(T t, U u) throws E;
}
