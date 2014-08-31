package org.flossware.reflect.caller.retry;

import java.util.logging.Level;
import org.flossware.reflect.call.Call;
import org.flossware.reflect.call.CallStrategy;
import org.flossware.reflect.caller.AbstractCaller;
import org.flossware.reflect.caller.retry.RetryCaller;

/**
 *
 * Provides layering functionality utilizing pre/method/post functionality.
 * Enables lifecycle management to calling a method.  By lifecycle,
 * we can provide functionality for pre call, and post call processing
 * of a method.
 *
 * @author sfloess
 *
 */
public abstract class AbstractRetryCaller<V> extends AbstractCaller<V> implements RetryCaller<V> {
    /**
     * Return our maximum number of retries.
     *
     * @return the maximum number of retries.
     */
    protected abstract int getMaxRetries();

    /**
     * Determines if we are done calling/retrying.  We are done if the
     * call was successful OR we've called it the maximum number of
     * retries provided.
     *
     * @param retryCount total number of times a retry has been executed.
     *
     * @return true if we are done.
     */
    protected boolean isRetryExhausted(final int retryCount) {
        return retryCount >= getMaxRetries();
    }

    /**
     * Called when a failure arises - this determines if we should continue
     * retrying.
     *
     * @param call is the call made when a failure arose.
     * @param failure is the exception that arose when calling.
     *
     * @return true if we should continue calling or false if not.
     *
     * @throws Exception if a problem arise determining if we should continue processing.
     */
    protected abstract boolean isToRetryCall(final Call<V> call, final Exception failure) throws Exception;

    /**
     * Set the strategy to use when performing our call.
     *
     * @param callStrategy is the strategy to use when performing our call.
     */
    protected AbstractRetryCaller(final CallStrategy<V> callStrategy) {
        super(callStrategy);
    }

    /**
     * Default constructor.
     */
    protected AbstractRetryCaller() {
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public final Object executeCall(final Call<V> call) throws Throwable {
        getLogger().log(Level.FINEST, "About to execute call [{0}]", call);

        int totalRetries = 0;

        Exception failure = null;

        // While we haven't exhuasted all our retries...
        while (!isRetryExhausted(totalRetries)) {
            try {
                return super.executeCall(call);
            }

            catch (final Exception exception) {
                getLogger().log(Level.WARNING, "Trouble executing method!", exception);

                // We may be able to recover from some exceptions.
                if (! isToRetryCall(call, exception)) {
                    throw exception;
                }

                failure = exception;
            }
        }

        getLogger().log(Level.WARNING, "Maximum number of retries [{0}] exhausted - raising exception!", getMaxRetries());

        throw failure;
    }
}
