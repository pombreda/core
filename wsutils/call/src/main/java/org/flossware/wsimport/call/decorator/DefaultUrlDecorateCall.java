package org.flossware.wsimport.call.decorator;

import org.flossware.reflect.call.Call;

/**
 *
 * Sets urls on objects.
 *
 * @author sfloess
 *
 */
public class DefaultUrlDecorateCall<V> extends AbstractUrlDecorateCall<V> {

    /**
     * Our url.
     */
    private final String url;

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getUrl(final Call<V> call) {
        return url;
    }

    /**
     * This constructor sets the url.
     */
    public DefaultUrlDecorateCall(final String url) {
        this.url = url;
    }
}
