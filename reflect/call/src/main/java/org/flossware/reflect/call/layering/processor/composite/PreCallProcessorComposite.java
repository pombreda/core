package org.flossware.reflect.call.layering.processor.composite;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import org.flossware.common.IntegrityUtil;
import org.flossware.reflect.call.Call;
import org.flossware.reflect.call.layering.processor.AbstractProcessor;
import org.flossware.reflect.call.layering.processor.PreCallProcessor;

/**
 *
 * Defines a collection of pre call processors - each one will be called for pre
 * call processing..
 *
 * @author sfloess
 *
 */
public class PreCallProcessorComposite<V> extends AbstractProcessor implements PreCallProcessor<V> {

    /**
     * Our collection of post call processors.
     */
    private final List<PreCallProcessor<V>> preCallProcessorList;

    /**
     * Return the list of pre call processors.
     *
     * @return the list of pre call processors.
     */
    protected List<PreCallProcessor<V>> getPreCallProcessList() {
        return preCallProcessorList;
    }

    /**
     * Sets a list of pre call processors.
     *
     * @param preCallProcessorList the list of post call processors.
     *
     * @throws IllegalArgumentException if preCallProcessorList is null or
     *                                  empty.
     */
    public PreCallProcessorComposite(final List<PreCallProcessor<V>> preCallProcessorList) {
        IntegrityUtil.ensure(preCallProcessorList, "The list is empty or null!");

        final List<PreCallProcessor<V>> newList = new LinkedList<PreCallProcessor<V>>();

        Collections.copy(newList, preCallProcessorList);

        this.preCallProcessorList = Collections.unmodifiableList(newList);
    }

    /**
     * Sets an array of pre call processors.
     *
     * @param preCallProcessors the array of post call processors.
     *
     * @throws IllegalArgumentException if preCallProcessors is null or empty.
     */
    public PreCallProcessorComposite(final PreCallProcessor<V>[] preCallProcessors) {
        this(Arrays.asList(preCallProcessors));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Call<V> prepareToCall(final Call<V> call) throws Exception {
        Call<V> retVal = call;

        for (final PreCallProcessor preCallProcessor : getPreCallProcessList()) {
            getLogger().log(Level.FINEST, "Notifying preCallProcessor [{0}]", preCallProcessor);

            retVal = preCallProcessor.prepareToCall(retVal);
        }

        return retVal;
    }
}
