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
import org.flossware.wsimport.service.WebService;

/**
 * Very much like an InvocationHandler. However, additionally, the web service
 * to be called is being presented.
 *
 * @author Scot P. Floess
 */
public interface PortInvocationHandler {

    /**
     * Called when we should invoke a method on the port of a web service.
     *
     * @param webService the web service whose port we will call.
     * @param proxy      the proxy instance that the method was invoked on
     * @param method     the method to call.
     * @param args       the arguments to present to the method.
     *
     * @return the value returned from the method call.
     *
     * @throws Throwable the exception thrown from invocation on the proxy.
     */
    Object invoke(WebService<?> webService, Object proxy, Method method, Object[] args) throws Throwable;
}
