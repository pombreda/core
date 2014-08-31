package org.flossware.reflect.caller.method.decorator;

import org.flossware.reflect.caller.method.CallerInvocationHandler;
import java.lang.reflect.InvocationHandler;
import org.flossware.reflect.caller.Caller;

/**
 *
 * Default implementation of a decorator.
 *
 * @author sfloess
 *
 */
public class DefaultMethodDecorator<V> extends AbstractMethodDecorator<V> {
    /**
     * Creates an invocation handler.
     *
     * @param decoratee is the object for whom we will add decoration.
     */
    @Override
    protected InvocationHandler createInvocationHandler(final Object decoratee, Caller<V> caller) {
        return new CallerInvocationHandler(decoratee, caller);
    }
}
