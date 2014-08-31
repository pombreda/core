package org.flossware.reflect.caller.layering.processor;

import org.flossware.reflect.call.layering.processor.PostCallProcessor;
import org.flossware.reflect.call.layering.processor.PreCallProcessor;
import org.flossware.reflect.caller.layering.LayeringCaller;

/**
 *
 * Interface defining a layering caller.  Layering provides us a life cycle call - this includes
 * processing pre call, the call and post call.
 *
 * @author sfloess
 *
 */
public interface LayeringProcessorCaller<V> extends LayeringCaller<V> {
    /**
     * Return the pre call processor.
     *
     * @return the pre-call processor.
     */
    PreCallProcessor getPreCallProcessor();

    /**
     * Return the post call processor.
     *
     * @return the post call processor.
     */
    PostCallProcessor getPostCallProcessor();
}
