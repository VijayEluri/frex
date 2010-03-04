package z.core.progress;

/**
 * A default progress monitor implementation suitable for
 * subclassing.
 * <p/>
 * This implementation supports cancelation. The default
 * implementations of the other methods do nothing.
 * </p><p>
 * This class can be used without OSGi running.
 * </p>
 */
public class NullProgressMonitor implements ProgressMonitor {

    /**
     * Indicates whether cancel has been requested.
     */
    private boolean cancelled = false;

    /**
     * Constructs a new progress monitor.
     */
    public NullProgressMonitor() {
        super();
    }

    /**
     * This implementation does nothing.
     * Subclasses may override this method to do interesting
     * processing when a task begins.
     */
    public void beginTask(String name, int totalWork) {
        // do nothing
    }

    /**
     * This implementation does nothing.
     * Subclasses may override this method to do interesting
     * processing when a task is done.
     */
    public void done() {
        // do nothing
    }

    /**
     * This implementation does nothing.
     * Subclasses may override this method.
     */
    public void internalWorked(double work) {
        // do nothing
    }

    /**
     * This implementation returns the value of the internal
     * state variable set by <code>setCanceled</code>.
     * Subclasses which override this method should
     * override <code>setCanceled</code> as well.
     */
    public boolean isCanceled() {
        return cancelled;
    }

    /**
     * This implementation sets the value of an internal state variable.
     * Subclasses which override this method should override
     * <code>isCanceled</code> as well.
     */
    public void setCanceled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    /**
     * This implementation does nothing.
     * Subclasses may override this method to do something
     * with the name of the task.
     */
    public void setTaskName(String name) {
        // do nothing
    }

    /**
     * This implementation does nothing.
     * Subclasses may override this method to do interesting
     * processing when a subtask begins.
     */
    public void subTask(String name) {
        // do nothing
    }

    /**
     * This implementation does nothing.
     * Subclasses may override this method to do interesting
     * processing when some work has been completed.
     */
    public void worked(int work) {
        // do nothing
    }
}
