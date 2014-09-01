package org.flossware.reflect.caller.concurrent;

import java.util.concurrent.Semaphore;
import org.flossware.reflect.call.Call;
import org.flossware.reflect.call.CallStrategy;

/**
 *
 * Limits concurrent access using a semaphore.
 *
 */
public abstract class AbstractSempahoreConcurrentCaller<V> extends AbstractConcurrentCaller<V> {

    /**
     * Return our semaphore...
     *
     * @param call is the call being executed.
     *
     * @return the semaphore.
     */
    protected abstract Semaphore getSemaphore(Call<V> call);

    /**
     * {@inheritDoc}
     */
    @Override
    protected void lock(Call<V> call) {
        try {
            getSemaphore(call).acquire();
        } catch (final RuntimeException runtimeException) {
            throw runtimeException;
        } catch (final Throwable throwable) {
            throw new LockException(throwable);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void unlock(Call<V> call) {
        getSemaphore(call).release();
    }

    /**
     * Default constructor.
     *
     * @param callStrategy dictates how to actually make call.
     */
    protected AbstractSempahoreConcurrentCaller(final CallStrategy<V> callStrategy) {
        super(callStrategy);
    }

    /**
     * Default constructor simply uses a default caller as the object to
     * decorate.
     */
    protected AbstractSempahoreConcurrentCaller() {
    }
}
