package org.flossware.reflect.caller.method;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.logging.Logger;
import org.flossware.common.IntegrityUtil;
import org.flossware.reflect.call.Call;
import org.flossware.reflect.caller.Caller;

/**
 *
 * Abstract base class for calling methods on an object.  Will package up calls to
 * derived classes (via the Call object).  This object can also have arbitrary data
 * assigned to it (via its "value" member/method).
 *
 * @author sfloess
 *
 */
public abstract class AbstractCallerInvocationHandler<V> implements InvocationHandler {
    /**
     * Our logger.
     */
    private final Logger logger;

    /**
     * The object for whom we decorate our calls.
     */
    private final Object object;

    /**
     * Return the logger.
     *
     * @return the logger.
     */
    protected final Logger getLogger() {
        return logger;
    }

    /**
     * Return the object.
     *
     * @return the object.
     */
    protected Object getObject() {
        return object;
    }

    /**
     * Return the object who will make the method call.
     *
     * @return the object who will make the method call.
     */
    protected abstract Caller<V> getCaller();

    /**
     * Set the object for whom we perform calls.
     *
     * @param object The object for whom we perform calls.
     *
     * @throws IllegalArgumentException if object is null.
     */
    public AbstractCallerInvocationHandler(final Object object) {
        this.logger = Logger.getLogger(getClass().getName());
        this.object = IntegrityUtil.ensure(object, "Bad object!");
    }


    /**
     * @{@inheritDoc}
     */
    @Override
    public final Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        final Call<V> call = new Call(getObject(), method, args);

        return getCaller().executeCall(call);
    }
}
