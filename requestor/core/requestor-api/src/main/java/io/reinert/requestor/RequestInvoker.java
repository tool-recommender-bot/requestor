/*
 * Copyright 2014 Danilo Reinert
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.reinert.requestor;

import io.reinert.requestor.auth.Authentication;
import io.reinert.requestor.header.Header;

/**
 * A {@link RequestBuilder} capable of sending itself.
 *
 * @see RequestInvokerImpl
 *
 * @author Danilo Reinert
 */
public interface RequestInvoker extends RequestBuilder, HttpRequestInvoker {

    @Override
    RequestInvoker contentType(String mediaType);

    @Override
    RequestInvoker accept(String mediaType);

    @Override
    RequestInvoker header(String header, String value);

    @Override
    RequestInvoker header(Header header);

    @Override
    RequestInvoker auth(Authentication auth);

    @Override
    RequestInvoker timeout(int timeoutMillis);

    @Override
    RequestInvoker payload(Object object) throws IllegalArgumentException;

    @Override
    RequestInvoker responseType(ResponseType responseType);

}