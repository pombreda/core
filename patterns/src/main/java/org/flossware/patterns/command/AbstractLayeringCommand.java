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

import org.flossware.common.AbstractCommonBase;

/**
 * Abstract base class for the layering pattern.
 *
 * @author Scot P. Floess
 *
 * @param <V> the type used in layering.
 */
public abstract class AbstractLayeringCommand<V> extends AbstractCommonBase implements Command<V> {

    /**
     * Will process a failure that arose when executing the command. The fail()
     * method will be invoked and a RuntimeException raised.
     *
     * @param obj     the object that was being used when executing the command.
     * @param failure the failure that arose when we executed the command.
     * @param toRaise the RuntimeException to raise.
     *
     * @return the exception to raise.
     */
    private void processFailure(final V obj, final Throwable failure, final RuntimeException toRaise) {
        failed(obj, failure);

        throw toRaise;
    }

    /**
     * Will process a failure that arose when executing a command. The failure
     * was a RuntimeException which will be be raised.
     *
     * @param obj     the object that was being used when executing the command.
     * @param failure the failure that arose when we executed the command.
     *
     * @return the failure.
     */
    private void processFailure(final V obj, final RuntimeException failure) {
        processFailure(obj, failure, failure);
    }

    /**
     * Will process a failure that arose when executing the command. The fail()
     * method will be invoked and a RuntimeException raised.
     *
     * @param obj     the object that was being used when executing the command.
     * @param failure the failure that arose when we executed the command.
     *
     * @return a RuntimeException whose caused by is failure..
     */
    private void processFailure(final V obj, final Throwable failure) {
        processFailure(obj, failure, new RuntimeException(failure));
    }

    /**
     * Before executing the call, this is our opportunity to affect what object
     * is used for invoking.
     *
     * @param toPrepare the object to prepare for execution.
     *
     * @return a prepared object to use for executing the command.
     */
    protected abstract V prepare(V toPrepare);

    /**
     * After command execution, called to denote success from execution.
     *
     * @param obj the object used in execution that resulted in success.
     */
    protected abstract void succeeded(V obj);

    /**
     * When executing command, execution failed.
     *
     * @param obj     the object used in execution when failure arose.
     * @param failure the failure that arose.
     */
    protected abstract void failed(V obj, Throwable failure);

    /**
     * Invokes the command using obj as the parameter.
     *
     * @param obj the object to use for invocation.
     *
     * @return the result of execution.
     */
    protected abstract V invoke(V obj);

    /**
     * {@inheritDoc}
     */
    @Override
    public V execute(final V obj) {
        V retVal = null;

        try {
            retVal = invoke(prepare(obj));
        } catch (final RuntimeException failure) {
            processFailure(obj, failure);
        } catch (final Throwable failure) {
            processFailure(obj, failure);
        }

        succeeded(retVal);

        return retVal;
    }
}
