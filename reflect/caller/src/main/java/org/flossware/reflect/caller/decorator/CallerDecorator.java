package org.flossware.reflect.caller.decorator;

import org.flossware.reflect.call.decorator.DecorateCall;
import org.flossware.reflect.caller.Caller;

/**
 *
 * Denotes a call strategy that defers calling to a caller.
 *
 * @author sfloess
 *
 */
public interface CallerDecorator<V> extends Caller<V> {
    /**
     * Return the call decorator.
     *
     * @return the call decorator.
     */
    DecorateCall<V> getCallDecorator();

    /**
     * Return the caller for who, we add embellishment.
     *
     * @return the caller for whom we add embellishment.
     */
    Caller<V> getDecoratee();
}
