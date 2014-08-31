package org.flossware.reflect.call.layering.processor.nil;

import java.util.logging.Level;
import org.flossware.reflect.call.Call;
import org.flossware.reflect.call.layering.processor.AbstractProcessor;
import org.flossware.reflect.call.layering.processor.PreCallProcessor;

/**
 *
 * Null Object design pattern for a PreCallProcessor.
 *
 * @author sfloess
 *
 */
public final class NullPreCallProcessor<V> extends AbstractProcessor implements PreCallProcessor<V> {

    /**
     * Default constructor.
     */
    public NullPreCallProcessor() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Call<V> prepareToCall(final Call<V> call) {
        getLogger().log(Level.FINEST, "Preparing to call [{0}]", call);

        return call;
    }
}
