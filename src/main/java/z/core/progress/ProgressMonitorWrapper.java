package z.core.progress;

import z.util.Assert;


/**
 * An abstract wrapper around a progress monitor which,
 * unless overridden, forwards <code>IProgressMonitor</code>
 * and <code>IProgressMonitorWithBlocking</code> methods to the wrapped progress monitor.
 * <p/>
 * This class can be used without OSGi running.
 * </p><p>
 * Clients may subclass.
 * </p>
 */
public abstract class ProgressMonitorWrapper implements ProgressMonitor {

    /**
     * The wrapped progress monitor.
     */
    private ProgressMonitor progressMonitor;

    /**
     * Creates a new wrapper around the given monitor.
     *
     * @param monitor the progress monitor to forward to
     */
    protected ProgressMonitorWrapper(ProgressMonitor monitor) {
        Assert.notNull(monitor);
        progressMonitor = monitor;
    }

    /**
     * This implementation of a <code>ProgressMonitor</code>
     * method forwards to the wrapped progress monitor.
     * Clients may override this method to do additional
     * processing.
     *
     * @see ProgressMonitor#beginTask(String, int)
     */
    public void beginTask(String name, int totalWork) {
        progressMonitor.beginTask(name, totalWork);
    }

    /**
     * This implementation of a <code>ProgressMonitor</code>
     * method forwards to the wrapped progress monitor.
     * Clients may override this method to do additional
     * processing.
     *
     * @see ProgressMonitor#done()
     */
    public void done() {
        progressMonitor.done();
    }

    /**
     * Returns the wrapped progress monitor.
     *
     * @return the wrapped progress monitor
     */
    public ProgressMonitor getWrappedProgressMonitor() {
        return progressMonitor;
    }

    /**
     * This implementation of a <code>ProgressMonitor</code>
     * method forwards to the wrapped progress monitor.
     * Clients may override this method to do additional
     * processing.
     *
     * @see ProgressMonitor#internalWorked(double)
     */
    public void internalWorked(double work) {
        progressMonitor.internalWorked(work);
    }

    /**
     * This implementation of a <code>ProgressMonitor</code>
     * method forwards to the wrapped progress monitor.
     * Clients may override this method to do additional
     * processing.
     *
     * @see ProgressMonitor#isCanceled()
     */
    public boolean isCanceled() {
        return progressMonitor.isCanceled();
    }

    /**
     * This implementation of a <code>ProgressMonitor</code>
     * method forwards to the wrapped progress monitor.
     * Clients may override this method to do additional
     * processing.
     *
     * @see ProgressMonitor#setCanceled(boolean)
     */
    public void setCanceled(boolean b) {
        progressMonitor.setCanceled(b);
    }

    /**
     * This implementation of a <code>ProgressMonitor</code>
     * method forwards to the wrapped progress monitor.
     * Clients may override this method to do additional
     * processing.
     *
     * @see ProgressMonitor#setTaskName(String)
     */
    public void setTaskName(String name) {
        progressMonitor.setTaskName(name);
    }

    /**
     * This implementation of a <code>ProgressMonitor</code>
     * method forwards to the wrapped progress monitor.
     * Clients may override this method to do additional
     * processing.
     *
     * @see ProgressMonitor#subTask(String)
     */
    public void subTask(String name) {
        progressMonitor.subTask(name);
    }

    /**
     * This implementation of a <code>ProgressMonitor</code>
     * method forwards to the wrapped progress monitor.
     * Clients may override this method to do additional
     * processing.
     *
     * @see ProgressMonitor#worked(int)
     */
    public void worked(int work) {
        progressMonitor.worked(work);
    }
}
