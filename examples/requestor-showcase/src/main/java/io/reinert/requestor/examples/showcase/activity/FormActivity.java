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
package io.reinert.requestor.examples.showcase.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import io.reinert.requestor.Headers;
import io.reinert.requestor.Requestor;
import io.reinert.requestor.SerializedResponse;
import io.reinert.requestor.examples.showcase.ui.Form;
import io.reinert.requestor.examples.showcase.util.Page;
import io.reinert.requestor.examples.showcase.util.Util;
import io.reinert.requestor.form.FormData;
import io.reinert.requestor.gdeferred.DoneCallback;
import io.reinert.requestor.gdeferred.RequestPromise;

public class FormActivity extends AbstractActivity implements Form.Handler {

    private final Form view;
    private final Requestor requestor;

    public FormActivity(Form form, Requestor requestor) {
        this.view = form;
        this.requestor = requestor;
    }

    @Override
    public void onSubmitClick(FormData formData) {
        requestor.req("http://httpbin.org/post")
                .payload(formData)
                .post(SerializedResponse.class) // retrieve the raw response
                .done(new DoneCallback<SerializedResponse>() {
                    @Override
                    public void onDone(SerializedResponse response) {
                        // the payload is parsed as string by default
                        // to change it, we can set the desired responseType in the RequestBuilder
                        final String payload = response.getPayload().isString();
                        view.setResultText(payload);
                    }
                });

    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        view.setHandler(this);
        Page.setTitle("Form");
        Page.setDescription("Submit AJAX Forms easily.");
        panel.setWidget(view);
    }

    @Override
    public void onStop() {
        view.setHandler(null);
    }
}
