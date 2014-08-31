package org.flossware.reflect.caller.layering.processor;

import org.flossware.common.IntegrityUtil;
import org.flossware.reflect.call.CallStrategy;
import org.flossware.reflect.call.layering.processor.nil.NullPostCallProcessor;
import org.flossware.reflect.call.layering.processor.nil.NullPreCallProcessor;
import org.flossware.reflect.call.layering.processor.PostCallProcessor;
import org.flossware.reflect.call.layering.processor.PostCallProcessor;
import org.flossware.reflect.call.layering.processor.PreCallProcessor;
import org.flossware.reflect.call.layering.processor.PreCallProcessor;

/**
 *
 * Default implementation of a layering caller.
 *
 * @author sfloess
 *
 */
public class DefaultLayeringProcessorCaller<V> extends AbstractLayeringProcessorCaller<V> {
    /**
     * Will be called before a method is called.
     */
    private final PreCallProcessor<V> preCallProcessor;

    /**
     * After method calls have been made, will be called to denote success or failure.
     */
    private final PostCallProcessor<V> postCallProcessor;

    /**
     * {@inheritDoc}
     */
    @Override
    protected PreCallProcessor<V> getPreCallProcessor() {
        return preCallProcessor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PostCallProcessor<V> getPostCallProcessor() {
        return postCallProcessor;
    }

    /**
     * Set the caller for whom we will decorate.
     *
     * @param callStrategy is the strategy to use when performing our call.
     * @param preCallProcessor is called before a method is called.
     * @param postCallProcessor is called after a method is called.
     *
     * @throws IllegalArgumentException if callStrategy, preCallProcessor or postCallProcessor are null.
     */
    public DefaultLayeringProcessorCaller(final CallStrategy<V> callStrategy, final PreCallProcessor<V> preCallProcessor, final PostCallProcessor<V> postCallProcessor) {
        super(callStrategy);

        this.preCallProcessor  = IntegrityUtil.ensure(preCallProcessor,  "Bad pre-call processor!");
        this.postCallProcessor = IntegrityUtil.ensure(postCallProcessor, "Bad post-call processor!");
    }

    /**
     * Set the caller for whom we will decorate.
     *
     * @param preCallProcessor is called before a method is called.
     * @param postCallProcessor is called after a method is called.
     *
     * @throws IllegalArgumentException if preCallProcessor or postCallProcessor are null.
     */
    public DefaultLayeringProcessorCaller(final PreCallProcessor<V> preCallProcessor, final PostCallProcessor<V> postCallProcessor) {
        this.preCallProcessor  = IntegrityUtil.ensure(preCallProcessor,  "Bad pre-call processor!");
        this.postCallProcessor = IntegrityUtil.ensure(postCallProcessor, "Bad post-call processor!");
    }

    /**
     * Set the caller for whom we will decorate.
     *
     * @param callStrategy is the strategy to use when performing our call.
     * @param preCallProcessor is called before a method is called.
     *
     * @throws IllegalArgumentException if callStrategy, or preCallProcessor are null.
     */
    public DefaultLayeringProcessorCaller(final CallStrategy<V> callStrategy, final PreCallProcessor<V> preCallProcessor) {
        this(callStrategy, preCallProcessor, new NullPostCallProcessor<V>());
    }

    /**
     * Set the caller for whom we will decorate.
     *
     * @param preCallProcessor is called before a method is called.
     *
     * @throws IllegalArgumentException if preCallProcessor is null.
     */
    public DefaultLayeringProcessorCaller(final PreCallProcessor<V> preCallProcessor) {
        this(preCallProcessor, new NullPostCallProcessor<V>());
    }

    /**
     * Set the caller for whom we will decorate.
     *
     * @param callStrategy is the strategy to use when performing our call.
     * @param postCallProcessor is called after a method is called.
     *
     * @throws IllegalArgumentException if callStrategy, or postCallProcessor are null.
     */
    public DefaultLayeringProcessorCaller(final CallStrategy<V> callStrategy, final PostCallProcessor<V> postCallProcessor) {
        this(callStrategy, new NullPreCallProcessor<V>(), postCallProcessor);
    }

    /**
     * Set the caller for whom we will decorate.
     *
     * @param postCallProcessor is called after a method is called.
     *
     * @throws IllegalArgumentException if postCallProcessor is null.
     */
    public DefaultLayeringProcessorCaller(final PostCallProcessor<V> postCallProcessor) {
        this(new NullPreCallProcessor<V>(), postCallProcessor);
    }
}
