package net.jmiller.lambdathrow.util;

import net.jmiller.lambdathrow.functional.*;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * providing some useful ways to hack around java lambda's restriction on throwing checked exceptions.
 *
 * borrowed from https://stackoverflow.com/a/27661504
 */
public class FunctionalUtils {

    /**
     * e.g.:
     * .forEach(rethrowConsumer(name -> System.out.println(Class.forName(name))));
     * or
     * .forEach(rethrowConsumer(ClassNameUtil::println));
     */
    public static <T, E extends Exception> Consumer<T> rethrowConsumer(ThrowingConsumer<T, E> consumer) throws E {
        return t -> {
            try { consumer.accept(t); }
            catch (Exception exception) { throwAsUnchecked(exception); }
        };
    }

    public static <T, U, E extends Exception> BiConsumer<T, U> rethrowBiConsumer(ThrowingBiConsumer<T, U, E> biConsumer) throws E {
        return (t, u) -> {
            try { biConsumer.accept(t, u); }
            catch (Exception exception) { throwAsUnchecked(exception); }
        };
    }

    /**
     * e.g.:
     * .map(rethrowFunction(name -> Class.forName(name)));
     * or
     * .map(rethrowFunction(Class::forName));
     */
    public static <T, R, E extends Exception> Function<T, R> rethrowFunction(ThrowingFunction<T, R, E> function) throws E {
        return t -> {
            try { return function.apply(t); }
            catch (Exception exception) { throwAsUnchecked(exception); return null; }
        };
    }

    /**
     * e.g.:
     * rethrowSupplier(() -> new StringJoiner(new String(new byte[]{77, 97, 114, 107}, "UTF-8"))),
     */
    public static <T, E extends Exception> Supplier<T> rethrowSupplier(ThrowingSupplier<T, E> function) throws E {
        return () -> {
            try { return function.get(); }
            catch (Exception exception) { throwAsUnchecked(exception); return null; }
        };
    }

    /**
     * e.g.:
     * uncheck(() -> Class.forName("xxx"));
     */
    public static void uncheck(ThrowingRunnable t)
    {
        try { t.run(); }
        catch (Exception exception) { throwAsUnchecked(exception); }
    }

    /**
     * e.g.:
     * uncheck(() -> Class.forName("xxx"));
     */
    public static <R, E extends Exception> R uncheck(ThrowingSupplier<R, E> supplier)
    {
        try { return supplier.get(); }
        catch (Exception exception) { throwAsUnchecked(exception); return null; }
    }

    /** e.g.:
     * uncheck(Class::forName, "xxx");
     */
    public static <T, R, E extends Exception> R uncheck(ThrowingFunction<T, R, E> function, T t) {
        try { return function.apply(t); }
        catch (Exception exception) { throwAsUnchecked(exception); return null; }
    }

    @SuppressWarnings ("unchecked")
    private static <E extends Throwable> void throwAsUnchecked(Exception exception) throws E { throw (E) exception; }
}
