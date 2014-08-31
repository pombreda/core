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

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.flossware.wsimport.service.WebService;

/**
 * Abstract base class for web service invocations.
 *
 * @author Scot P. Floess
 */
public abstract class AbstractWebServiceInvocationDecorator<P> extends AbstractWebServiceDecorator<P> implements InvocationHandler {

    /**
     * Returns the handler to use. Subclasses can decide (if appropriate), what
     * port invocation handlers can service this call.
     *
     * @param proxy  the proxy for whom we need a handler.
     * @param method the method being called on the proxy.
     * @param args   the arguments to the method.
     *
     * @return the handler to use.
     */
    protected abstract PortInvocationHandler getHandler(Object proxy, Method method, Object[] args);

    /**
     * Set the decoratee who can create ports.
     *
     * @param decoratee
     */
    public AbstractWebServiceInvocationDecorator(final WebService<P> decoratee) {
        super(decoratee);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public P getPort() {
        return (P) (Proxy.newProxyInstance(getClass().getClassLoader(), getDecoratee().getPort().getClass().getInterfaces(), this));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        return getHandler(proxy, method, args).invoke(getDecoratee(), proxy, method, args);
    }
}
