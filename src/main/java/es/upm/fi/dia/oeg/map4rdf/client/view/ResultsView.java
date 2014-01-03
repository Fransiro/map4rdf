/**
 * Copyright (c) 2011 Ontology Engineering Group, 
 * Departamento de Inteligencia Artificial,
 * Facultad de Informetica, Universidad 
 * Politecnica de Madrid, Spain
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package es.upm.fi.dia.oeg.map4rdf.client.view;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import es.upm.fi.dia.oeg.map4rdf.client.presenter.ResultsPresenter;
import es.upm.fi.dia.oeg.map4rdf.client.resource.BrowserMessages;

/**
 * @author Alexander De Leon
 */
public class ResultsView extends Composite implements ResultsPresenter.Display {

	private final FlowPanel table;
	private TabLayoutPanel tabs;
	@Inject
	public ResultsView(BrowserMessages browserMessages) {
		table = new FlowPanel();
		initWidget(createUi(browserMessages.facets()));
	}

	private Widget createUi(String mainHeader) {
		ScrollPanel scrollPanel = new ScrollPanel(table);
		tabs= new TabLayoutPanel(22, Unit.PX);
		tabs.add(scrollPanel, mainHeader);
		return tabs;
	}

	@Override
	public void addResourceLink(String name, String uri) {
		Anchor a = new Anchor(name, uri);
		a.setTarget("_blank");
		FlowPanel anchorContainer = new FlowPanel();
		anchorContainer.add(a);
		table.add(anchorContainer);

	}

	@Override
	public void clear() {
		table.clear();
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void addWidget(Widget widget, String name) {
		
		tabs.add(widget, name);
	}

	@Override
	public void removeWidget(Widget widget) {
		
		if(tabs.getWidgetIndex(widget)!=-1){
			tabs.remove(widget);
		}
	}

	@Override
	public void doSelectedWidget(Widget widget) {
		
		if(tabs.getWidgetIndex(widget)!=-1){
			tabs.selectTab(widget);
		}
	}

}
