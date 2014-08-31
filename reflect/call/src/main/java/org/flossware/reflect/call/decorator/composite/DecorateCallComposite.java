package org.flossware.reflect.call.decorator.composite;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.flossware.common.IntegrityUtil;
import org.flossware.reflect.call.decorator.DecorateCall;

/**
 *
 * Represents a composite (collection) of decorate calls.
 *
 */
public class DecorateCallComposite<V> extends AbstractDecorateCallComposite<V> {

    /**
     * The list of decorate calls.
     */
    private final List<DecorateCall<V>> decorateCallList;

    /**
     * Return the decorate call list.
     *
     * @return the decorate call list.
     */
    protected List<DecorateCall<V>> getDecorateCallList() {
        return decorateCallList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Iterable<DecorateCall<V>> iterator() {
        return getDecorateCallList();
    }

    /**
     * This constructor sets the decorate call list.
     *
     * @param decorateCallList the list of decorate calls.
     *
     * @throws IllegalArgumentException if decorateCallList is null or has less
     *                                  than 1 element.
     */
    public DecorateCallComposite(final List<DecorateCall<V>> decorateCallList) {
        IntegrityUtil.ensure(decorateCallList, 1);

        this.decorateCallList = Collections.unmodifiableList(decorateCallList);
    }

    /**
     * This constructor sets up our collection of decorate calls.
     *
     * @throws IllegalArgumentException if decorateCallArray has less than 1
     *                                  element.
     */
    public DecorateCallComposite(final DecorateCall<V>[] decorateCallArray) {
        this(Arrays.asList(decorateCallArray));
    }
}
