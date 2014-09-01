package org.flossware.reflect.call.layering.processor;

import org.flossware.reflect.call.Call;

/**
 *
 * Interface defines the API for processing before a call is made to an object.
 *
 * @author sfloess
 *
 */
public interface PreCallProcessor<V> {

    /**
     * Denotes a call is about to be made. Allows implementations to change the
     * call if need be.
     *
     * @param call the call that will be made.
     *
     * @return a new representation of <code>call</code> or <code>call</code>
     *         itself (if no changes).
     */
    Call<V> prepareToCall(Call<V> call);
}
