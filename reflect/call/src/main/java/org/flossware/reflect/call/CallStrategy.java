package org.flossware.reflect.call;

/**
 *
 * Defines a strategy when performing a call. Decoupling the actual call from
 * the caller means we can either call directly or implement a decorator style
 * call.
 *
 * @author sfloess
 *
 */
public interface CallStrategy<V> {

    /**
     * Will execute the call.
     *
     * @param call the call to make.
     *
     * @return the result from the call.
     */
    Object performCall(Call<V> call);
}
