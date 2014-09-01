package org.flossware.reflect.caller.layering.processor;

import org.flossware.reflect.call.Call;
import org.flossware.reflect.call.CallStrategy;
import org.flossware.reflect.call.layering.processor.PostCallProcessor;
import org.flossware.reflect.call.layering.processor.PreCallProcessor;
import org.flossware.reflect.caller.layering.AbstractLayeringCaller;

/**
 *
 * Abstracts out pre and post call processing to the helper implementations
 * PreCallProcessor and PostCallProcessor.
 *
 * @author sfloess
 *
 */
public abstract class AbstractLayeringProcessorCaller<V> extends AbstractLayeringCaller<V> {

    /**
     * Set up the semaphore to use.
     *
     * @param callStrategy is the strategy to use when performing our call.
     */
    protected AbstractLayeringProcessorCaller(final CallStrategy<V> callStrategy) {
        super(callStrategy);
    }

    /**
     * Default constructor.
     */
    protected AbstractLayeringProcessorCaller() {

    }

    /**
     * Return the object who will be called before any method is called.
     *
     * @return the object who will be called before any method is called.
     */
    protected abstract PreCallProcessor<V> getPreCallProcessor();

    /**
     * Return the object who will be called after a method succeeds or fails.
     *
     * @return the object who will be called after a method succeeds or fails.
     */
    protected abstract PostCallProcessor<V> getPostCallProcessor();

    /**
     * {@inheritDoc}
     */
    @Override
    protected Call<V> prepareToCall(final Call<V> call) {
        return getPreCallProcessor().prepareToCall(call);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void callSucceeded(final Call<V> call) {
        getPostCallProcessor().callSucceeded(call);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void callFailed(final Call<V> call, final Throwable failure) {
        getPostCallProcessor().callFailed(call, failure);
    }
}
