package org.flossware.reflect.call.layering.processor.pause;

import org.flossware.reflect.call.Call;
import org.flossware.reflect.call.layering.processor.PostCallProcessor;

/**
 *
 * Allows a pause during post processing.
 *
 * @author sfloess
 *
 */
public class PostCallPauseProcessor<V> extends AbstractPauseProcessor implements PostCallProcessor<V> {

    /**
     * Set the pause time.
     *
     * @param pause is the pause time.
     *
     * @throws IllegalArgumentException if pause is < 1.
     */
    public PostCallPauseProcessor(final long pause) {
        super(pause);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void callFailed(final Call<V> call, final Throwable failure) {
        doPause();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void callSucceeded(final Call<V> call) {
        doPause();
    }
}
