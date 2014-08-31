package org.flossware.reflect.caller.layering;

import java.util.logging.Level;

import org.flossware.reflect.call.Call;
import org.flossware.reflect.call.CallStrategy;
import org.flossware.reflect.caller.AbstractCaller;
import org.flossware.reflect.caller.layering.LayeringCaller;


/**
 *
 * Provides layering functionality utilizing pre/method/post functionality.
 * Enables lifecycle management to calling a method.  By lifecycle,
 * we can provide functionality for pre call, and post call processing
 * of a method.
 *
 * @author sfloess
 *
 */
public abstract class AbstractLayeringCaller<V> extends AbstractCaller<V> implements LayeringCaller<V> {
    /**
     * Denotes a call is about to be made.  Allows implementations to change
     * the call if need be.
     *
     * @param call the call that will be made.
     *
     * @return a new representation of <code>call</code> or
     *         <code>call</code> itself (if no changes).
     *
     * @throws Exception raised if the call cannot be made.
     */
    protected abstract Call<V> prepareToCall(Call<V> call) throws Exception;

    /**
     * Denotes a call passed.
     *
     * @param call the call made.
     *
     * @throws Exception if any problem arises in dealing with the successful call.
     */
    protected abstract void callSucceeded(Call<V> call) throws Exception;

    /**
     * Denotes a call failed.
     *
     * @param call the call made.
     * @param failure the failure raised when calling.
     *
     * @throws Exception if any problem arise handling the failed call.
     */
    protected abstract void callFailed(Call<V> call, Throwable failure) throws Exception;

    /**
     * Set the strategy to use when performing our call.
     *
     * @param callStrategy is the strategy to use when performing our call.
     */
    protected AbstractLayeringCaller(final CallStrategy<V> callStrategy) {
        super(callStrategy);
    }

    /**
     * Default constructor.
     */
    protected AbstractLayeringCaller() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object executeCall(final Call<V> call) throws Throwable {
        getLogger().log(Level.FINEST, "Calling [{0}]", call);

        Object retVal = null;

        final Call<V> theActualCall = prepareToCall(call);

        try {
            retVal = super.executeCall(theActualCall);
        } catch (final Throwable throwable) {
            callFailed(theActualCall, throwable);

            throw throwable;
        }

        callSucceeded(theActualCall);

        return retVal;
    }
}
