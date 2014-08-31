package org.flossware.reflect.call.layering.processor;

import org.flossware.reflect.call.Call;

/**
 *
 * Interface defines the API for processing after a call is made to an object.
 *
 * @author sfloess
 *
 */
public interface PostCallProcessor<V> {
    /**
     * Denotes a call passed.
     *
     * @param call the call made.
     *
     * @throws Exception if any problem arises in dealing with the successful call.
     */
    void callSucceeded(Call<V> call) throws Exception;

    /**
     * Denotes a call failed.
     *
     * @param call the call made.
     * @param failure the failure raised when calling.
     *
     * @throws Exception if any problem arise handling the failed call.
     */
    void callFailed(Call<V> call, Throwable failure) throws Exception;
}
