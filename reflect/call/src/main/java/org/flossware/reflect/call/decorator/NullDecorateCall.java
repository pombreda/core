package org.flossware.reflect.call.decorator;

import org.flossware.reflect.call.Call;


/**
 *
 * Null implementation of a decorate call.
 *
 */
public class NullDecorateCall<V> extends AbstractDecorateCall<V> {
    /**
     * Default constructor.
     */
    public NullDecorateCall() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Call<V> decorate(final Call<V> call) {
        return call;
    }
}
