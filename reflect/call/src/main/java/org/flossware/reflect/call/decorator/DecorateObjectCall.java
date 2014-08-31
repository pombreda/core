package org.flossware.reflect.call.decorator;

import org.flossware.common.IntegrityUtil;
import org.flossware.reflect.call.Call;

/**
 *
 * Allows one to force what object will be used when decorate is called.
 *
 * @author sfloess
 *
 */
public class DecorateObjectCall<V> extends AbstractDecorateCall<V> implements DecorateCall<V> {

    /**
     * The object to be used when decorate is called.
     */
    private final Object object;

    /**
     * Return the object to be used when decorate is called.
     */
    protected Object getObject() {
        return object;
    }

    /**
     * This constructor sets the object to use when we decorate call.
     */
    public DecorateObjectCall(final Object object) {
        IntegrityUtil.ensure(object, "Object cannot be null!");

        this.object = object;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Call<V> decorate(Call<V> call) {
        return call.copy(getObject());
    }
}
