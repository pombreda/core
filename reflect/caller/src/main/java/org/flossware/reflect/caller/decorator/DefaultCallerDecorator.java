package org.flossware.reflect.caller.decorator;

import org.flossware.common.IntegrityUtil;
import org.flossware.reflect.call.decorator.DecorateCall;
import org.flossware.reflect.call.decorator.NullDecorateCall;
import org.flossware.reflect.caller.Caller;

/**
 *
 * Default implementation ofa call strategy caller.
 *
 */
public abstract class DefaultCallerDecorator<V> extends AbstractCallerDecorator<V> {

    /**
     * Will decorate the actual calls.
     */
    private final DecorateCall<V> callDecorator;

    /**
     * Will perform the actual call.
     */
    private final Caller<V> caller;

    /**
     * This constructor sets the call decorator and the caller.
     */
    public DefaultCallerDecorator(final DecorateCall<V> callDecorator, final Caller<V> caller) {
        this.callDecorator = IntegrityUtil.ensure(callDecorator, "Call decorator cannot be null!");
        this.caller = IntegrityUtil.ensure(caller, "Caller cannot be null!");
    }

    /**
     * Default constructor.
     */
    public DefaultCallerDecorator(final Caller<V> caller) {
        this(new NullDecorateCall<V>(), caller);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DecorateCall<V> getCallDecorator() {
        return callDecorator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Caller<V> getDecoratee() {
        return caller;
    }
}
