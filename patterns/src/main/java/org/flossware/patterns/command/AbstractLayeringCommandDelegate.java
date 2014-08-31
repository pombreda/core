/*
 * Copyright (C) 2014 Scot P. Floess
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.flossware.patterns.command;

import org.flossware.common.operation.Failure;
import org.flossware.common.IntegrityUtil;
import org.flossware.common.operation.Prepare;
import org.flossware.common.operation.Success;
import org.flossware.common.operation.NullFailure;
import org.flossware.common.operation.NullPrepare;
import org.flossware.common.operation.NullSuccess;

/**
 * Honors the layering command by delegating calls.
 *
 * @author Scot P. Floess
 *
 * @param <V> the type used in layering.
 */
public abstract class AbstractLayeringCommandDelegate<V> extends AbstractLayeringCommand<V> {

    /**
     * Delegate prepare calls.
     */
    private final Prepare<V> prepare;

    /**
     * Delegate success calls.
     */
    private final Success<V> success;

    /**
     * Delegate failure calls.
     */
    private final Failure<V> failure;

    /**
     * Sets the various delegates.
     *
     * @param prepare
     * @param success
     * @param failure
     *
     * @throws IllegalArgumentException if any of the delegates are null.
     */
    protected AbstractLayeringCommandDelegate(final Prepare<V> prepare, final Success<V> success, final Failure<V> failure) {
        this.prepare = IntegrityUtil.ensure(prepare, "Cannot have a null prepare delegate");
        this.success = IntegrityUtil.ensure(success, "Cannot have a null success delegate");
        this.failure = IntegrityUtil.ensure(failure, "Cannot have a null failure delegate");
    }

    /**
     * Sets the prepare delegate.
     *
     * @param prepare the prepare delegate.
     *
     * @throws IllegalArgumentException if the prepare delegate is null.
     */
    protected AbstractLayeringCommandDelegate(final Prepare<V> prepare) {
        this(prepare, DEFAULT_SUCCESS, DEFAULT_FAILURE);
    }

    /**
     * Sets the success delegate.
     *
     * @param success the success delegate.
     *
     * @throws IllegalArgumentException if the success delegate is null.
     */
    protected AbstractLayeringCommandDelegate(final Success<V> success) {
        this(DEFAULT_PREPARE, success, DEFAULT_FAILURE);
    }

    /**
     * Sets the failure delegate.
     *
     * @param failure the failure delegate.
     *
     * @throws IllegalArgumentException if the failure delegate is null.
     */
    protected AbstractLayeringCommandDelegate(final Failure<V> failure) {
        this(DEFAULT_PREPARE, DEFAULT_SUCCESS, failure);
    }

    /**
     * Return the prepare delegate.
     *
     * @return the prepare delegate.
     */
    protected Prepare<V> getPrepare() {
        return prepare;
    }

    /**
     * Return the success delegate.
     *
     * @return the success delegate.
     */
    protected Success<V> getSuccess() {
        return success;
    }

    /**
     * Return the failure delegate.
     *
     * @return the failure delegate.
     */
    protected Failure<V> getFailure() {
        return failure;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected V prepare(final V toPrepare) {
        return getPrepare().prepare(toPrepare);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void succeeded(final V obj) {
        getSuccess().succeeded(obj);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void failed(final V obj, final Throwable failure) {
        getFailure().failed(obj, failure);
    }

    /**
     * The default prepare delegate.
     */
    public static final Prepare DEFAULT_PREPARE = new NullPrepare();

    /**
     * The default success delegate.
     */
    public static final Success DEFAULT_SUCCESS = new NullSuccess();

    /**
     * The default failure delegate.
     */
    public static final Failure DEFAULT_FAILURE = new NullFailure();
}
