package org.flossware.wsimport.call.decorator;

import org.flossware.reflect.call.Call;
import org.flossware.reflect.call.decorator.DecorateCall;
import org.flossware.wsimport.call.AbstractUrlCall;

/**
 *
 * Sets urls on objects.
 *
 * @author sfloess
 *
 */
public abstract class AbstractUrlDecorateCall<V> extends AbstractUrlCall<V> implements DecorateCall<V> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Call<V> decorate(Call<V> call) {
        return modifyCall(call);
    }
}
