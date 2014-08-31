package org.flossware.wsimport.call;

import org.flossware.reflect.call.Call;
import org.flossware.wsimport.soap.SoapUtil;

/**
 *
 * Sets urls on objects.
 *
 * @author sfloess
 *
 */
public abstract class AbstractUrlCall<V> {

    /**
     * Default constructor.
     */
    protected AbstractUrlCall() {
    }

    /**
     * Returns the URL.
     *
     * @param call is the call being made when the url is requested.
     */
    protected abstract String getUrl(final Call<V> call);

    /**
     * Modify the object in <code>call</code> to have the URL set upon it.
     *
     * @param call is the call to modify.
     *
     * @return the modified call.
     */
    protected Call<V> modifyCall(final Call<V> call) {
        SoapUtil.setUrl(call.getObject(), getUrl(call));

        return call;
    }
}
