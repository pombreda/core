package org.flossware.reflect.call.layering.processor.composite;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import org.flossware.common.IntegrityUtil;
import org.flossware.reflect.call.Call;
import org.flossware.reflect.call.layering.processor.AbstractProcessor;
import org.flossware.reflect.call.layering.processor.PostCallProcessor;

/**
 *
 * Defines a collection of post call processors - each one will be called for
 * post call processing..
 *
 * @author sfloess
 *
 */
public class PostCallProcessorComposite<V> extends AbstractProcessor implements PostCallProcessor<V> {

    /**
     * Our collection of post call processors.
     */
    private final List<PostCallProcessor<V>> postCallProcessorList;

    /**
     * Return the list of post call processors.
     *
     * @return the list of post call processors.
     */
    protected List<PostCallProcessor<V>> getPostCallProcessList() {
        return postCallProcessorList;
    }

    /**
     * Sets a list of post call processors.
     *
     * @param postCallProcessorList the list of post call processors.
     *
     * @throws IllegalArgumentException if postCallProcessorList is null or
     *                                  empty.
     */
    public PostCallProcessorComposite(final List<PostCallProcessor<V>> postCallProcessorList) {
        IntegrityUtil.ensure(postCallProcessorList, "The list is empty or null!");

        final List<PostCallProcessor<V>> newList = new LinkedList<PostCallProcessor<V>>();

        Collections.copy(newList, postCallProcessorList);

        this.postCallProcessorList = Collections.unmodifiableList(newList);
    }

    /**
     * Sets an array of post call processors.
     *
     * @param preCallProcessors the array of post call processors.
     *
     * @throws IllegalArgumentException if postCallProcessors is null or empty.
     */
    public PostCallProcessorComposite(final PostCallProcessor<V>[] postCallProcessors) {
        this(Arrays.asList(postCallProcessors));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void callFailed(final Call<V> call, final Throwable failure) {
        for (final PostCallProcessor postCallProcessor : getPostCallProcessList()) {
            getLogger().log(Level.FINEST, "Notifying postCallProcessor [{0}] of failure [{1}]", new Object[] {postCallProcessor, failure.getMessage()});

            postCallProcessor.callFailed(call, failure);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void callSucceeded(final Call<V> call) {
        for (final PostCallProcessor postCallProcessor : getPostCallProcessList()) {
            getLogger().log(Level.FINEST, "Notifying postCallProcessor [{0}] of success", postCallProcessor);

            postCallProcessor.callSucceeded(call);
        }
    }
}
