package org.flossware.reflect.caller.method;

import org.flossware.common.IntegrityUtil;
import org.flossware.reflect.caller.Caller;

/**
 *
 * Uses a caller to perform the actual calls.
 *
 * @author sfloess
 *
 */
public class CallerInvocationHandler<V> extends AbstractCallerInvocationHandler<V> {
    /**
     * Performs our method calls.
     */
    private final Caller<V> caller;

    /**
     * {@inheritDoc}
     */
    @Override
    protected Caller<V> getCaller() {
        return caller;
    }

    /**
     * Set the object for whom we perform calls, the pre, call and post call processors.
     *
     * @param object The object for whom we perform calls.
     * @param caller the object who will perform method calls.
     *
     * @throws IllegalArgumentException if any of the params are null.
     */
    public CallerInvocationHandler(final Object object, final Caller<V> caller) {
        super(object);

        this.caller = IntegrityUtil.ensure(caller, "Bad caller!");
    }
}
