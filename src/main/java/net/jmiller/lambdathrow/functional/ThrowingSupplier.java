package net.jmiller.lambdathrow.functional;

/**
 * hacking around java's lambda idiosyncracies
 *
 * borrowed from https://stackoverflow.com/a/27661504
 * @param <T>
 * @param <E>
 */
@FunctionalInterface
public interface ThrowingSupplier<T, E extends Exception> {
    T get() throws E;
}
