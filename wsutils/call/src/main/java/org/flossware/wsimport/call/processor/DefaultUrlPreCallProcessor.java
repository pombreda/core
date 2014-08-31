package org.flossware.wsimport.call.processor;

import org.flossware.reflect.call.Call;

/**
 *
 * Sets urls on objects.
 *
 * @author sfloess
 *
 */
public class DefaultUrlPreCallProcessor<V> extends AbstractUrlPreCallProcessor<V> {

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
    public DefaultUrlPreCallProcessor(final String url) {
        this.url = url;
    }
}
