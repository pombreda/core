package org.flossware.reflect.caller.concurrent;

import java.util.logging.Level;
import org.flossware.reflect.call.Call;
import org.flossware.reflect.call.CallStrategy;
import org.flossware.reflect.caller.AbstractCaller;

/**
 *
 * Abstract base class for concurrent calls.
 *
 * @author sfloess
 *
 */
public abstract class AbstractConcurrentCaller<V> extends AbstractCaller<V> implements ConcurrentCaller<V> {

    /**
     * Perform a "lock" - whatever lock means.
     *
     * @param call is the call being made when requesting the lock.
     */
    protected abstract void lock(Call<V> call);

    /**
     * Perform an "unlock" - whatever unlock means.
     *
     * @param call is the call being made when requesting the unlock.
     */
    protected abstract void unlock(Call<V> call);

    /**
     * Sets the strategy to utilize when executing our call.
     *
     * @param callStrategy
     */
    protected AbstractConcurrentCaller(final CallStrategy<V> callStrategy) {
        super(callStrategy);
    }

    /**
     * Default constructor.
     */
    protected AbstractConcurrentCaller() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object executeCall(final Call<V> call) {
        getLogger().log(Level.FINEST, "Calling [{0}]", call);

        lock(call);

        try {
            return super.executeCall(call);
        } finally {
            unlock(call);
        }
    }
}
