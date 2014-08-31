package org.flossware.reflect.call.decorator;

import org.flossware.patterns.decorator.Decorate;
import org.flossware.reflect.call.Call;

/**
 *
 * Interface to decorate calls. Implementations should embellish the call.
 *
 * @author sfloess
 *
 */
public interface DecorateCall<V> extends Decorate<Call<V>> {
}
