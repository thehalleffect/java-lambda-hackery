package net.jmiller.lambdathrow.functional;

/**
 * hacking around java's lambda idiosyncracies
 *
 * borrowed from https://stackoverflow.com/a/27661504
 * @param <T>
 * @param <R>
 * @param <E>
 */
@FunctionalInterface
public interface ThrowingFunction<T, R, E extends Exception> {
    R apply(T t) throws E;
}
