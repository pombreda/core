package org.flossware.reflect.caller.concurrent;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import org.flossware.common.IntegrityUtil;
import org.flossware.reflect.call.Call;
import org.flossware.reflect.call.CallStrategy;


/**
 *
 * Limiting concurrent access using a semaphore.
 *
 */
public class SemaphoreConcurrentCaller<V> extends AbstractSempahoreConcurrentCaller<V> {
    /**
     * Limits our concurrent calls.
     */
    private final Semaphore semaphore;

    /**
     * Creates a semaphore and ensures it allows at least 1 max call.
     *
     * @param maxCalls maximum number of simultaneous calls allowed.
     * @param isFair same as the isFair param used in Semaphore constructor.
     *
     * @return a semaphore for use.
     *
     * @throws IllegalArgumentException if maxCalls is less than 1.
     */
    protected static Semaphore createSemaphore(final int maxCalls, boolean isFair) {
        return new Semaphore(IntegrityUtil.ensure(maxCalls, 1, "Cannot have fewer than 1 concurrent calls!"), isFair);
    }

    /**
     * Creates a semaphore and ensures it allows at least 1 max call.
     *
     * @param maxCalls maximum number of simultaneous calls allowed.
     *
     * @return a semaphore for use.
     *
     * @throws IllegalArgumentException if maxCalls is less than 1.
     */
    protected static Semaphore createSemaphore(final int maxCalls) {
        return createSemaphore(maxCalls, true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Semaphore getSemaphore(final Call<V> call) {
        getLogger().log(Level.FINEST, "Current available permits [{0}]", semaphore.availablePermits());

        return semaphore;
    }

    /**
     * Set up the semaphore to use.
     *
     * @param callStrategy is the strategy to use when performing our call.
     * @param semaphore is the semaphore to use.
     *
     * @throws IllegalArgumentException if <code>semaphore</code> is null.
     */
    public SemaphoreConcurrentCaller(final CallStrategy<V> callStrategy, final Semaphore semaphore) {
        super(callStrategy);

        this.semaphore = IntegrityUtil.ensure(semaphore, "Cannot have a null semaphore!");
    }

    /**
     * Set up the semaphore to use.
     *
     * @param semaphore is the semaphore to use.
     *
     * @throws IllegalArgumentException if <code>semaphore</code> is null.
     */
    public SemaphoreConcurrentCaller(final Semaphore semaphore) {
        this.semaphore = IntegrityUtil.ensure(semaphore, "Cannot have a null semaphore!");
    }

    /**
     * Set up the semaphore to use.
     *
     * @param callStrategy is the strategy to use when performing our call.
     * @param maxCalls maximum number of simultaneous calls that can be made.
     *
     * @throws IllegalArgumentException if <code>semaphore</code> is null or max calls is less than 1.
     */
    public SemaphoreConcurrentCaller(final CallStrategy<V> callStrategy, final int maxCalls) {
        this(callStrategy, createSemaphore(maxCalls));
    }

    /**
     * Set up the maximum number of concurrent calls against a caller and the
     * caller for whom we are adding this embellishment.
     *
     * @param maxCalls are the maximum number of concurrent calls that can
     *        be made against <code>caller</code> simultaneously.
     *
     * @throws IllegalArgumentException if <code>maxCalls</code> is less than 1.
     */
    public SemaphoreConcurrentCaller(final int maxCalls) {
        this(createSemaphore(maxCalls));
    }
}
