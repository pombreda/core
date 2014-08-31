package org.flossware.wsimport.call.processor;

import org.flossware.reflect.call.Call;
import org.flossware.reflect.call.layering.processor.PreCallProcessor;
import org.flossware.wsimport.call.AbstractUrlCall;

/**
 *
 * Sets urls on objects.
 *
 * @author sfloess
 *
 */
public abstract class AbstractUrlPreCallProcessor<V> extends AbstractUrlCall<V> implements PreCallProcessor<V> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Call<V> prepareToCall(final Call<V> call) {
        return super.modifyCall(call);
    }
}
