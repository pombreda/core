package org.flossware.reflect.caller.retry;

import org.flossware.common.IntegrityUtil;
import org.flossware.reflect.call.Call;
import org.flossware.reflect.call.CallStrategy;
import org.flossware.reflect.call.retry.RetryCallFilter;

/**
 *
 * Defers determining the viability of a failure for retry to a RetryCallFilter
 * implementation.
 *
 * @author sfloess
 *
 */
public class FilteredRetryCaller<V> extends AbstractRetryCaller<V> {

    /**
     * Determines if retries can happen upon failure.
     */
    private final RetryCallFilter retryCallFilter;

    /**
     * Maximum number of retries upon failure allowed.
     */
    private final int maxRetries;

    /**
     * Return our retry call filter.
     *
     * @return our retry call filter.
     */
    protected RetryCallFilter getRetryCallFilter() {
        return retryCallFilter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected int getMaxRetries() {
        return maxRetries;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isToRetryCall(Call<V> call, Throwable failure) {
        return getRetryCallFilter().isToRetryCall(call, failure);
    }

    /**
     * This constructor sets the object who filters retries and the maximum
     * number of retries allowed upon failure.
     *
     * @param callStrategy    is the strategy to use when performing our call.
     * @param retryCallFilter the object who filters failures for retry
     *                        viability.
     * @param maxRetries      the maximum number of retries allowed.
     *
     * @throws IllegalArgumentException if callStrategy or retryCallFilter is
     *                                  null or maxRetries is less than 1.
     */
    public FilteredRetryCaller(final CallStrategy<V> callStrategy, final RetryCallFilter retryCallFilter, final int maxRetries) {
        super(callStrategy);

        this.retryCallFilter = IntegrityUtil.ensure(retryCallFilter, "Retry filter cannot be null");
        this.maxRetries = IntegrityUtil.ensure(maxRetries, 1, "Must be at least one retry defined");
    }

    /**
     * This constructor sets the object who filters retries and the maximum
     * number of retries allowed upon failure.
     *
     * @param retryCallFilter the object who filters failures for retry
     *                        viability.
     * @param maxRetries      the maximum number of retries allowed.
     *
     * @throws IllegalArgumentException if retryCallFilter is null or maxRetries
     *                                  is less than 1.
     */
    public FilteredRetryCaller(final RetryCallFilter retryCallFilter, final int maxRetries) {
        this.retryCallFilter = IntegrityUtil.ensure(retryCallFilter, "Retry filter cannot be null");
        this.maxRetries = IntegrityUtil.ensure(maxRetries, 1, "Must be at least one retry defined");
    }

    /**
     * This constructor sets the object who filters retries and the maximum
     * number of retries allowed upon failure.
     *
     * @param callStrategy    is the strategy to use when performing our call.
     * @param retryCallFilter the object who filters failures for retry
     *                        viability.
     *
     * @throws IllegalArgumentException if callStrategy or retryCallFilter is
     *                                  null or maxRetries is less than 1.
     */
    public FilteredRetryCaller(final CallStrategy<V> callStrategy, final RetryCallFilter retryCallFilter) {
        this(callStrategy, retryCallFilter, Integer.MAX_VALUE);
    }

    /**
     * This constructor sets the object who filters retries. The maximum number
     * of retries will be the maximum value for an integer (Integer.MAX_VALUE).
     *
     * @param retryCallFilter the object who filters failures for retry
     *                        viability.
     *
     * @throws IllegalArgumentException if retryCallFilter is null.
     */
    public FilteredRetryCaller(final RetryCallFilter retryCallFilter) {
        this(retryCallFilter, Integer.MAX_VALUE);
    }
}
