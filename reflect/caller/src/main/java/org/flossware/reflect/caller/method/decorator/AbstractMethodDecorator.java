package org.flossware.reflect.caller.method.decorator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.logging.Logger;
import org.flossware.reflect.caller.Caller;

/**
 *
 * Abstract base class for creating a decorator of interfaces.
 *
 * @author sfloess
 *
 */
public abstract class AbstractMethodDecorator<V> implements MethodDecorator<V> {

    /**
     * Our logger.
     */
    private final Logger logger;

    /**
     * Return the logger.
     *
     * @return the logger.
     */
    protected final Logger getLogger() {
        return logger;
    }

    /**
     * Creates an invocation handler.
     *
     * @param decoratee is the object for whom we will add decoration.
     * @param caller    is the object who will perform the actual call.
     */
    protected abstract InvocationHandler createInvocationHandler(final Object decoratee, Caller<V> caller);

    /**
     * Default constructor.
     */
    protected AbstractMethodDecorator() {
        this.logger = Logger.getLogger(getClass().getName());
    }

    /**
     * @{@inheritDoc}
     */
    @Override
    public <T> T decorateMethods(T decoratee, final Caller<V> caller) {
        return (T) (Proxy.newProxyInstance(getClass().getClassLoader(), decoratee.getClass().getInterfaces(), createInvocationHandler(decoratee, caller)));
    }
}
