package org.flossware.reflect.call;

import java.lang.reflect.Method;
import java.util.Arrays;
import org.flossware.common.IntegrityUtil;
import org.flossware.common.Stringifiable;

/**
 *
 * Represents a method call to an object.
 *
 * @param <V> a value one can assign to the call.
 *
 * @author sfloess
 *
 */
public class Call<V> implements Stringifiable {

    /**
     * The object whose method will be called.
     */
    private Object object;

    /**
     * The method to call.
     */
    private Method method;

    /**
     * The arguments to method.
     */
    private Object[] args;

    /**
     * A value one can carry along with the call.
     */
    private V value;

    /**
     * Set up the object, method and args to call.
     *
     * @param object the object whose <code>method</code> will be called.
     * @param method the method to call on <code>object</code>.
     * @param args   the arguments to <code>method</code>.
     * @param value  a value to carry along with the call.
     */
    public Call(final Object object, final Method method, final Object[] args, final V value) {
        IntegrityUtil.ensure(object, "Bad object!");
        IntegrityUtil.ensure(method, "Bad method!");
        IntegrityUtil.ensure(args, "Bad args!");

        this.object = object;
        this.method = method;
        this.args = args;
        this.value = value;
    }

    /**
     * Set up the object, method and args to call.
     *
     * @param object the object whose <code>method</code> will be called.
     * @param method the method to call on <code>object</code>.
     * @param args   the arguments to <code>method</code>.
     */
    public Call(final Object object, final Method method, final Object[] args) {
        this(object, method, args, null);
    }

    /**
     * Copy constructor.
     *
     * @param call
     */
    public Call(final Call<V> call) {
        this(call.getObject(), call.getMethod(), call.getArgs(), call.getValue());
    }

    /**
     * Creates a copy of self.
     *
     * @return a copy of self.
     */
    public Call<V> copy() {
        return new Call(getObject(), getMethod(), getArgs(), getValue());
    }

    /**
     * Creates a copy of self but changes the object being used.
     *
     * @param object is a new object to use to make method calls.
     */
    public Call<V> copy(final Object object) {
        return new Call(object, getMethod(), getArgs(), getValue());
    }

    /**
     * Return the object.
     *
     * @return the object.
     */
    public Object getObject() {
        return object;
    }

    /**
     * Set the object.
     *
     * @param object is the new object.
     */
    public void setObject(final Object object) {
        this.object = object;
    }

    /**
     * Return the method.
     *
     * @return the method.
     */
    public Method getMethod() {
        return method;
    }

    /**
     * Set the method.
     *
     * @param method is the new method.
     */
    public void setMethod(final Method method) {
        this.method = method;
    }

    /**
     * Return the arguments.
     *
     * @return the arguments.
     */
    public Object[] getArgs() {
        return args;
    }

    /**
     * Set the arguments.
     *
     * @param args are the new arguments.
     */
    public void setArgs(final Object[] args) {
        this.args = args;
    }

    /**
     * Return the value associated with the call - it is arbitrary.
     *
     * @return the value.
     */
    public V getValue() {
        return value;
    }

    /**
     * Set the value associated with the call (its an arbitrary value). It can
     * be null.
     *
     * @param value associated with the call.
     */
    public void setValue(final V value) {
        this.value = value;
    }

    /**
     * Execute the call.
     *
     * @return the result of the call.
     *
     * @throws Throwable if any problem arises making the call.
     */
    public Object execute() throws Throwable {
        return getMethod().invoke(getObject(), getArgs());
    }

    /**
     * @{@inheritDoc}
     */
    @Override
    public void toString(final StringBuilder stringBuilder, final String prefix) {
        final String myPrefix = prefix + "    ";

        stringBuilder.append(myPrefix).append("Object [").append(getObject()).append("]\n");
        stringBuilder.append(myPrefix).append("Method [").append(getMethod()).append("]\n");
        stringBuilder.append(myPrefix).append("Args   ").append(Arrays.toString(getArgs())).append('\n');
        stringBuilder.append(myPrefix).append("Value");

        if (getValue() instanceof Stringifiable) {
            stringBuilder.append(":\n");
            ((Stringifiable) getValue()).toString(stringBuilder, myPrefix + "    ");
        } else {
            stringBuilder.append("");
            stringBuilder.append("  [").append(getValue()).append(']');
        }

        stringBuilder.append(']');
    }

    /**
     * @{@inheritDoc}
     */
    @Override
    public void toString(final StringBuilder stringBuilder) {
        toString(stringBuilder, "");
    }

    /**
     * @{@inheritDoc}
     */
    @Override
    public String toString(final String prefix) {
        final StringBuilder sb = new StringBuilder();

        toString(sb, prefix);

        return sb.toString();
    }

    /**
     * @{@inheritDoc}
     */
    @Override
    public String toString() {
        return toString("");
    }
}
