package org.flossware.reflect.call.retry;

import org.flossware.reflect.call.Call;

/**
 *
 * Interface defines the API for denoting if a call should be retried or not.
 *
 * @author sfloess
 *
 */
public interface RetryCallFilter<V> {

    /**
     * Return true if we should retry the call, or false if not.
     *
     * @param call    is the call to execute.
     * @param failure is the failure that arose when calling to an object.
     *
     * @return true if we should retry or false if not.
     */
    boolean isToRetryCall(Call<V> call, Throwable failure);
}
