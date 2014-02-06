package es.upm.fi.dia.oeg.map4rdf.share.webnmasuno;

import java.util.ArrayList;

import es.upm.fi.dia.oeg.map4rdf.share.GeoResource;
import es.upm.fi.dia.oeg.map4rdf.share.Geometry;

/**
 * 
 * @author Daniel Garijo
 *
 */
public class WebNMasUnoResourceContainer extends GeoResource{
	private ArrayList<WebNMasUnoResource> resources;
	
	public WebNMasUnoResourceContainer(){
            resources = new ArrayList<WebNMasUnoResource>();
	}
	
	public WebNMasUnoResourceContainer(String uri, Geometry geometry) {
            super(uri, geometry);
            resources = new ArrayList<WebNMasUnoResource>();
	}

        public void addWebNMasUnoResource(WebNMasUnoResource g){
            resources.add(g);
        }

        public ArrayList<WebNMasUnoResource> getWebNMasUnoResources (){
            return resources;
        }
	
}
