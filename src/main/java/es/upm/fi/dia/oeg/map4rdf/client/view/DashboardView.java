/**
 * Copyright (c) 2011 Ontology Engineering Group, 
 * Departamento de Inteligencia Artificial,
 * Facultad de Inform‡tica, Universidad 
 * PolitŽcnica de Madrid, Spain
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
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

import es.upm.fi.dia.oeg.map4rdf.client.presenter.DashboardPresenter;
import es.upm.fi.dia.oeg.map4rdf.client.resource.BrowserResources;

/**
 * @author Alexander De Leon
 */
public class DashboardView extends ResizeComposite implements DashboardPresenter.Display {

	private final SplitLayoutPanel splitPanel;
	private final LayoutPanel centerPanel;
	private final StackLayoutPanel leftPanel;
	private final LayoutPanel mapPanel;
	private final ScrollPanel mainPopupPanel;
	//private BrowserResources resources;
	@Inject
	public DashboardView(BrowserResources resources) {
		//this.resources=resources;
		mapPanel = new LayoutPanel();
		splitPanel = new SplitLayoutPanel();
		leftPanel = new StackLayoutPanel(Unit.EM);
		centerPanel = new LayoutPanel();
		mainPopupPanel = new ScrollPanel();
		initWidget(createUi(resources));
	}

	/* ---------------- Dashboard API -- */
	@Override
	public void addWestWidget(Widget widget, String header) {
		leftPanel.add(widget, header, 3);
	}

	@Override
	public Panel getMapPanel() {
		return mapPanel;
	}

	/* ----------------- Display API -- */
	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public void clear() {
		leftPanel.showWidget(0);
	}
	
	@Override
	public void closeMainPopup() {
		mainPopupPanel.clear();
		mainPopupPanel.setVisible(false);
	}

	@Override
	public void doSelectedWestWidget(Widget widget) {
		
		//if(widget isChildrenOf leftPanel)
		if(leftPanel.getWidgetIndex(widget)!=-1){
			leftPanel.showWidget(widget);
		}
	}

	@Override
	public void setMainPopup(Integer width, Integer height, Widget widget, String style) {
		width-=50;
		height-=50;
		mainPopupPanel.clear();
		mainPopupPanel.setVisible(true);
		mainPopupPanel.setWidget(widget);
		if(width==null || width<0){
			width=200;
			mainPopupPanel.setWidth("auto");
		}else{
			mainPopupPanel.setWidth(width+"px");
		}
		if(height==null || height<0){
			height=200;
			mainPopupPanel.setHeight("auto");
		}else{
			mainPopupPanel.setHeight(height+"px");
		}
		if(style==null || style.equals("")){
			DOM.setStyleAttribute(mainPopupPanel.getElement(), "zIndex", "2080");
			DOM.setStyleAttribute(mainPopupPanel.getElement(), "left", "15px");
			DOM.setStyleAttribute(mainPopupPanel.getElement(), "top", "15px");		
		}else if(style.equals("Geoprocessing")){
			DOM.setStyleAttribute(mainPopupPanel.getElement(), "zIndex", "2080");
			DOM.setStyleAttribute(mainPopupPanel.getElement(), "left", "15px");
			DOM.setStyleAttribute(mainPopupPanel.getElement(), "top", "");
			DOM.removeElementAttribute(mainPopupPanel.getElement(),"top");
			DOM.setStyleAttribute(mainPopupPanel.getElement(), "bottom", "15px");
			DOM.setStyleAttribute(widget.getElement(), "left", (int)(width*0.06)+"px");
		} else if(style.equals("Big")){
			DOM.setStyleAttribute(mainPopupPanel.getElement(), "zIndex", "2080");
			DOM.setStyleAttribute(mainPopupPanel.getElement(), "left", "15px");
			DOM.setStyleAttribute(mainPopupPanel.getElement(), "top", "15px");
			widget.setSize(width-3+"px", height-3+"px");
			/*DOM.setStyleAttribute(mainPopupPanel.getElement(), "zIndex", "2080");
			DOM.setStyleAttribute(mainPopupPanel.getElement(), "top", "");
			DOM.removeElementAttribute(mainPopupPanel.getElement(),"top");
			DOM.setStyleAttribute(mainPopupPanel.getElement(), "left", "");
			DOM.removeElementAttribute(mainPopupPanel.getElement(),"left");
			DOM.setStyleAttribute(mainPopupPanel.getElement(), "right", "");
			DOM.removeElementAttribute(mainPopupPanel.getElement(),"right");
			DOM.setStyleAttribute(mainPopupPanel.getElement(), "bottom", "");
			DOM.removeElementAttribute(mainPopupPanel.getElement(),"bottom");
			DOM.setStyleAttribute(mainPopupPanel.getElement(), "left", "50%");
			DOM.setStyleAttribute(mainPopupPanel.getElement(), "top", "50%");
			DOM.setStyleAttribute(mainPopupPanel.getElement(), "marginTop", (-(height/2)-2)+"px");
			DOM.setStyleAttribute(mainPopupPanel.getElement(), "marginLeft",(-(width/2)-2)+"px");
			DOM.setElementAttribute(mainPopupPanel.getElement(), "align", "center");*/
			/*DOM.setStyleAttribute(widget.getParent().getElement(), "width", "99%");
			DOM.setStyleAttribute(widget.getParent().getElement(), "height", "99%");*/
		}
	}
	
	/* ------------------- Helper methods -- */
	private Widget createUi(BrowserResources resources) {
		centerPanel.add(mapPanel);

		centerPanel.setWidgetTopHeight(mapPanel, 0, Unit.EM, 100, Unit.PCT);
		centerPanel.setWidgetLeftWidth(mapPanel, 0, Unit.EM, 100, Unit.PCT);
		
		splitPanel.addWest(leftPanel, 275);
		splitPanel.add(centerPanel);
		
		leftPanel.addStyleName(resources.css().leftMenu());
		
		mapPanel.add(mainPopupPanel);
		mainPopupPanel.setVisible(false);
		mainPopupPanel.setStyleName(resources.css().mainPopup());
		return splitPanel;
	}


	
}