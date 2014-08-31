package org.flossware.reflect.call.layering.processor.nil;

import java.util.logging.Level;
import org.flossware.reflect.call.Call;
import org.flossware.reflect.call.layering.processor.AbstractProcessor;
import org.flossware.reflect.call.layering.processor.PostCallProcessor;

/**
 *
 * Null Object design pattern for PostCallProcessor.
 *
 * @author sfloess
 *
 */
public final class NullPostCallProcessor<V> extends AbstractProcessor implements PostCallProcessor<V> {

    /**
     * Default constructor.
     */
    public NullPostCallProcessor() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void callFailed(final Call<V> call, final Throwable failure) {
        getLogger().log(Level.FINEST, "Call failed.  Call [{0}] Failure [{1}]", new Object[] {call, failure});
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void callSucceeded(final Call<V> call) {
        getLogger().log(Level.FINEST, "Call succeeded.  Call [{0}]", call);
    }
}
