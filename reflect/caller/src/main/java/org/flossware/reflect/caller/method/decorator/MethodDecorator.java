package org.flossware.reflect.caller.method.decorator;

import org.flossware.reflect.caller.Caller;

/**
 *
 * Defines the API to decorate methods on an object.
 *
 * @author sfloess
 *
 */
public interface MethodDecorator<V> {
    /**
     * Embellish calls on <code>decoratee</code>.
     *
     * @param <T> the type of object to decorateMethods behavior.
     *
     * @param decoratee the object to add behavior to.
     * @param caller the object who will perform calling and add decoration.
     *
     * @return a decorated implementation to supplement behavior on calls.
     */
    public <T> T decorateMethods(T decoratee, Caller<V> caller);
}
