package org.flossware.reflect.caller.decorator;

import org.flossware.reflect.caller.AbstractCaller;
import org.flossware.reflect.call.Call;

/**
 *
 * Abstract base class for using a caller to execute a call.
 *
 */
public abstract class AbstractCallerDecorator<V> extends AbstractCaller<V> implements CallerDecorator<V> {

    /**
     * Default constructor.
     */
    protected AbstractCallerDecorator() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object performCall(final Call<V> call) throws Throwable {
        return getDecoratee().executeCall(getCallDecorator().decorate(call));
    }
}
