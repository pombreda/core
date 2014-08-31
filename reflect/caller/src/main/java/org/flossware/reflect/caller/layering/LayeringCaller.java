package org.flossware.reflect.caller.layering;

import org.flossware.reflect.caller.Caller;

/**
 *
 * Interface defining a layering caller.  Layering provides us a life cycle call - this includes
 * processing pre call, the call and post call.
 *
 * @author sfloess
 *
 */
public interface LayeringCaller<V> extends Caller<V> {
}
