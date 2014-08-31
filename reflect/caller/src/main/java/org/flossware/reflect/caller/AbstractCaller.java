
package org.flossware.reflect.caller;

import java.util.logging.Logger;
import org.flossware.common.IntegrityUtil;
import org.flossware.reflect.call.Call;
import org.flossware.reflect.call.Call;
import org.flossware.reflect.call.CallStrategy;
import org.flossware.reflect.call.CallStrategy;
import org.flossware.reflect.caller.Caller;


/**
 *
 * Abstract base class for callers.
 *
 * @param <V> a value one can assign to the call.
 *
 */
public abstract class AbstractCaller<V> implements Caller<V>, CallStrategy<V> {
    /**
     * Our logger.
     */
    private final Logger logger;

    /**
     * Our call strategy - it decides how to make calls.
     */
    private final CallStrategy<V> callStrategy;

    /**
     * Set the call strategy to use.
     *
     * @param callStrategy is the call strategy to use.
     *
     * @throws IllegalArgumentException if <code>callStrategy</code> is null.
     */
    protected AbstractCaller(final CallStrategy<V> callStrategy) {
        this.logger       = Logger.getLogger(getClass().getName());
        this.callStrategy = IntegrityUtil.ensure(callStrategy, "Cannot have a null call strategy");
    }

    /**
     * Default constructor.
     */
    protected AbstractCaller() {
        this.logger       = Logger.getLogger(getClass().getName());
        this.callStrategy = this;
    }

    /**
     * Return the logger.
     *
     * @return the logger.
     */
    protected final Logger getLogger() {
        return logger;
    }

     /**
     * Return the call strategy used by the caller.
     *
     * @return the call strategy used by the caller.
     */
    protected CallStrategy<V> getCallStrategy() {
        return callStrategy;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object performCall(final Call<V> call) throws Throwable {
        return call.execute();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object executeCall(final Call<V> call) throws Throwable {
        return getCallStrategy().performCall(call);
    }
}
