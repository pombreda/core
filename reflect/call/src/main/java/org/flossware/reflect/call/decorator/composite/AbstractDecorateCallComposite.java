package org.flossware.reflect.call.decorator.composite;

import java.util.logging.Level;
import org.flossware.reflect.call.Call;
import org.flossware.reflect.call.decorator.AbstractDecorateCall;
import org.flossware.reflect.call.decorator.DecorateCall;

/**
 *
 * Abstract base class for composite (collections) of decorate calls.
 *
 */
public abstract class AbstractDecorateCallComposite<V> extends AbstractDecorateCall<V> {

    /**
     * Return an iterator of CallDecorator's.
     *
     * @return an iterator of CallDecorator's.
     */
    protected abstract Iterable<DecorateCall<V>> iterator();

    /**
     * {@inheritDoc}
     */
    @Override
    public Call<V> decorate(final Call<V> call) {
        Call retVal = call;

        getLogger().log(Level.FINEST, "Decorating call [{0}]", call);

        for (final DecorateCall<V> decorateCall : iterator()) {
            retVal = decorateCall.decorate(retVal);

            getLogger().log(Level.FINEST, "Decorated to [{0}]", retVal);
        }

        return retVal;
    }

    /**
     * Default constructor.
     */
    protected AbstractDecorateCallComposite() {
    }
}
