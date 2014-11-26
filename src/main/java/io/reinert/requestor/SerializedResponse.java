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

/**
 * Represents a response with its payload raw.
 *
 * @author Danilo Reinert
 */
public class SerializedResponse implements Response {

    private final com.google.gwt.http.client.Response delegate;
    private final Headers headers;

    public SerializedResponse(com.google.gwt.http.client.Response originalResponse) {
        this.delegate = originalResponse;
        this.headers = new Headers(delegate.getHeaders());
    }

    @Override
    public String getHeader(String header) {
        return delegate.getHeader(header);
    }

    @Override
    public String getContentType() {
        String contentType = delegate.getHeader("Content-Type");
        if (contentType == null) // try lower case
            contentType = delegate.getHeader("content-type");
        return contentType;
    }

    @Override
    public Headers getHeaders() {
        return headers;
    }

    @Override
    public int getStatusCode() {
        return delegate.getStatusCode();
    }

    @Override
    public String getStatusText() {
        return delegate.getStatusText();
    }

    @Override
    public Payload getPayload() {
        final String type = delegate.getType();
        return type.isEmpty() || type.equalsIgnoreCase("text") ?
                new Payload(delegate.getText()) : new Payload(delegate.getData());
    }
}
