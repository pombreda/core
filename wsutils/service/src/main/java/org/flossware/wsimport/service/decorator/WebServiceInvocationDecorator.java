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
package org.flossware.wsimport.service.decorator;

import java.lang.reflect.Method;
import org.flossware.common.IntegrityUtil;
import org.flossware.wsimport.service.WebService;

/**
 * A web service invocation decorator. The assumption here is this class uses
 * one port invocation handler for all calls.
 *
 * @author Scot P. Floess
 */
public class WebServiceInvocationDecorator<P> extends AbstractWebServiceInvocationDecorator<P> {

    /**
     * Executes calls on a web service port.
     */
    private final PortInvocationHandler handler;

    /**
     * {@inheritDoc}
     */
    @Override
    protected PortInvocationHandler getHandler(final Object proxy, final Method method, final Object[] args) {
        return handler;
    }

    /**
     * Set the decoratee who can create ports and the handler who will execute
     * calls against the port.
     *
     * @param decoratee
     * @param handler
     */
    public WebServiceInvocationDecorator(final WebService<P> decoratee, final PortInvocationHandler handler) {
        super(decoratee);

        this.handler = IntegrityUtil.ensure(handler, "Cannot have a null invocation handler!");
    }
}
