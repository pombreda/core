package org.flossware.wsimport.caller.factory;

import javax.xml.ws.Service;
import org.flossware.common.IntegrityUtil;
import org.flossware.reflect.caller.Caller;
import org.flossware.reflect.caller.DefaultCaller;
import org.flossware.wsimport.caller.factory.CallerFactory;

/**
 *
 * Null implementation of a caller factory. By null, it simply returns the
 * caller provided at construct time.
 *
 * @author sfloess
 *
 */
public class NullCallerFactory<V> implements CallerFactory<V> {

    /**
     * The caller we'll use when create() is called.
     */
    private final Caller<V> caller;

    /**
     * Set the caller that will be return when create() is called.
     *
     * @param caller the object to return when create() is called.
     *
     * @throws IllegalArgumentException if caller is null.
     */
    public NullCallerFactory(final Caller<V> caller) {
        IntegrityUtil.ensure(caller, "Caller cannot be null!");

        this.caller = caller;
    }

    /**
     * Default constructor. Will use a default caller.
     */
    public NullCallerFactory() {
        this(new DefaultCaller<V>());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <P> Caller<V> create(final Service service, final Class<P> portType) {
        return caller;
    }
}
