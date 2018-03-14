package net.jmiller.lambdathrow.functional;

/**
 * hacking around java's lambda idiosyncracies
 *
 * borrowed from https://stackoverflow.com/a/27661504
 * @param <T>
 * @param <E>
 */
@FunctionalInterface
public interface ThrowingConsumer<T, E extends Exception> {
    void accept(T t) throws E;
}
