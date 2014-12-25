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
package io.reinert.requestor.examples.showcase;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.web.bindery.event.shared.EventBus;

import io.reinert.requestor.Requestor;
import io.reinert.requestor.examples.showcase.ui.Form;
import io.reinert.requestor.examples.showcase.ui.GettingStarted;
import io.reinert.requestor.examples.showcase.ui.Home;
import io.reinert.requestor.examples.showcase.ui.Requesting;

public class ShowcaseClientFactoryImpl implements ShowcaseClientFactory {

    private final EventBus eventBus = new SimpleEventBus();
    private final PlaceController placeController = new PlaceController(eventBus);
    private Requestor requestor;
    private Home home;
    private GettingStarted gettingStarted;
    private Requesting requesting;
    private Form form;

    public ShowcaseClientFactoryImpl() {
        initRequestor();
    }

    @Override
    public EventBus getEventBus() {
        return eventBus;
    }

    @Override
    public PlaceController getPlaceController() {
        return placeController;
    }

    @Override
    public Requestor getRequestor() {
        return requestor;
    }

    @Override
    public Home getHome() {
        if (home == null)
            home = new Home();
        return home;
    }

    @Override
    public GettingStarted getGettingStarted() {
        if (gettingStarted == null)
            gettingStarted = new GettingStarted();
        return gettingStarted;
    }

    @Override
    public Requesting getRequesting() {
        if (requesting == null)
            requesting = new Requesting();
        return requesting;
    }

    @Override
    public Form getForm() {
        if (form == null)
            form = new Form();
        return form;
    }

    private void initRequestor() {
        requestor = GWT.create(Requestor.class);
        requestor.setDefaultMediaType(null); // Avoid auto-setting Accept and Content-Type headers to application/json
    }
}
