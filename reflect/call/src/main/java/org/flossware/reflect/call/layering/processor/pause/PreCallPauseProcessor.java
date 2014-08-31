package org.flossware.reflect.call.layering.processor.pause;

import org.flossware.reflect.call.Call;
import org.flossware.reflect.call.layering.processor.PreCallProcessor;

/**
 *
 * Allows a pause during pre processing.
 *
 * @author sfloess
 *
 */
public class PreCallPauseProcessor<V> extends AbstractPauseProcessor implements PreCallProcessor<V> {

    /**
     * Set the pause time.
     *
     * @param pause is the pause time.
     *
     * @throws IllegalArgumentException if pause is < 1.
     */
    public PreCallPauseProcessor(final long pause) {
        super(pause);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Call<V> prepareToCall(final Call<V> call) throws Exception {
        doPause();

        return call;
    }
}
