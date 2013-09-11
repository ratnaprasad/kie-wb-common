/*
 * Copyright 2013 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.kie.workbench.common.screens.explorer.client;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.github.gwtbootstrap.client.ui.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import org.kie.workbench.common.screens.explorer.client.widgets.business.BusinessViewWidget;
import org.kie.workbench.common.screens.explorer.client.widgets.technical.TechnicalViewWidget;
import org.uberfire.client.common.BusyPopup;

/**
 * The Explorer's view implementation
 */
@ApplicationScoped
public class ExplorerViewImpl extends Composite implements ExplorerView {

    interface ExplorerViewImplBinder
            extends
            UiBinder<Widget, ExplorerViewImpl> {

    }

    private static ExplorerViewImplBinder uiBinder = GWT.create( ExplorerViewImplBinder.class );

    @UiField
    Button btnBusinessView;

    @UiField
    Button btnTechnicalView;

    @UiField
    Button btnRefresh;

    @Inject
    private BusinessViewWidget businessView;

    @Inject
    private TechnicalViewWidget technicalView;

    private ExplorerPresenter presenter;

    @PostConstruct
    public void init() {
        //Cannot create and bind UI until after injection points have been initialized
        initWidget( uiBinder.createAndBindUi( this ) );
    }

    @Override
    public void init( final ExplorerPresenterImpl presenter ) {
        this.presenter = presenter;
    }

    @UiFactory
    public BusinessViewWidget getBusinessView() {
        return this.businessView;
    }

    @UiFactory
    public TechnicalViewWidget getTechnicalView() {
        return this.technicalView;
    }

    @UiHandler("btnBusinessView")
    public void onClickBusinessViewButton( final ClickEvent event ) {
        presenter.selectBusinessView();
    }

    @UiHandler("btnTechnicalView")
    public void onClickTechnicalViewButton( final ClickEvent event ) {
        presenter.selectTechnicalView();
    }

    @UiHandler("btnRefresh")
    public void onClickRefreshButton( final ClickEvent event ) {
        presenter.refresh();
    }


    @Override
    public void showBusyIndicator( final String message ) {
        BusyPopup.showMessage( message );
    }

    @Override
    public void hideBusyIndicator() {
        BusyPopup.close();
    }

}
