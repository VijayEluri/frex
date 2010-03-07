package z.util;

/**
 * <code>Assert</code> is useful for for embedding runtime sanity checks
 * in code. The predicate methods all test a condition and throw some
 * type of unchecked exception if the condition does not hold.
 * <p/>
 * Assertion failure exceptions, like most runtime exceptions, are
 * thrown when something is misbehaving. Assertion failures are invariably
 * unspecified behavior; consequently, clients should never rely on
 * these being thrown (and certainly should not being catching them
 * specifically).
 * </p><p>
 * This class is not intended to be instantiated or sub-classed by clients.
 * </p>
 */
public final class Assert {
    /* This class is not intended to be instantiated. */

    private Assert() {
    }

    /**
     * Asserts that an argument is legal. If the given boolean is
     * not <code>true</code>, an <code>IllegalArgumentException</code>
     * is thrown.
     *
     * @param expression the outcode of the check
     * @return <code>true</code> if the check passes (does not return
     *         if the check fails)
     * @throws IllegalArgumentException if the legality test failed
     */
    public static boolean argument(boolean expression) {
        return argument(expression, "Assert.argument(false) called"); //$NON-NLS-1$
    }

    /**
     * Asserts that an argument is legal. If the given boolean is
     * not <code>true</code>, an <code>IllegalArgumentException</code>
     * is thrown.
     * The given message is included in that exception, to aid debugging.
     *
     * @param expression the outcode of the check
     * @param message    the message to include in the exception
     * @return <code>true</code> if the check passes (does not return
     *         if the check fails)
     * @throws IllegalArgumentException if the legality test failed
     */
    public static boolean argument(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
        return expression;
    }

    /**
     * Asserts that the given object is not <code>null</code>. If this
     * is not the case, some kind of unchecked exception is thrown.
     *
     * @param object the value to test
     * @throws NullPointerException if the object is <code>null</code>
     */
    public static void notNull(Object object) {
        notNull(object, "Assert.notNull(null) called"); //$NON-NLS-1$
    }

    /**
     * Asserts that the given object is not <code>null</code>. If this
     * is not the case, some kind of unchecked exception is thrown.
     * The given message is included in that exception, to aid debugging.
     *
     * @param object  the value to test
     * @param message the message to include in the exception
     * @throws NullPointerException if the object is <code>null</code>
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new NullPointerException(message); //$NON-NLS-1$
        }
    }

    /**
     * Asserts that the given boolean is <code>true</code>. If this
     * is not the case, some kind of unchecked exception is thrown.
     *
     * @param expression the outcode of the check
     * @return <code>true</code> if the check passes (does not return
     *         if the check fails)
     * @throws IllegalStateException if the given boolean is <code>false</code>
     */
    public static boolean state(boolean expression) {
        return state(expression, "Assert.state(false) called"); //$NON-NLS-1$
    }

    /**
     * Asserts that the given boolean is <code>true</code>. If this
     * is not the case, some kind of unchecked exception is thrown.
     * The given message is included in that exception, to aid debugging.
     *
     * @param expression the outcode of the check
     * @param message    the message to include in the exception
     * @return <code>true</code> if the check passes (does not return
     *         if the check fails)
     * @throws IllegalStateException if the given boolean is <code>false</code>
     */
    public static boolean state(boolean expression, String message) {
        if (!expression) {
            throw new IllegalStateException(message); //$NON-NLS-1$
        }
        return expression;
    }
}
