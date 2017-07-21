package com.runoob.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;

public class Owl {
	static List<Resource> subjects=new ArrayList<Resource>();
	static List<Property> predicates=new ArrayList<Property>();
	static List<RDFNode> objects=new ArrayList<RDFNode>();
	static OntModel model=ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
	public static List<Resource> getSubjects() {
		model.read("E://Localism.owl");		
		StmtIterator re=model.listStatements();
		while(re.hasNext()){
			Statement sons=re.next();
			subjects.add(sons.getSubject());
		}
		return subjects;
	}
	public static List<Property> getPredicates() {
		model.read("E://Localism.owl");		
		StmtIterator re=model.listStatements();
		while(re.hasNext()){
			Statement sons=re.next();
			predicates.add(sons.getPredicate());
		}
		return predicates;
	}
	public static List<RDFNode> getObjects() {
		model.read("E://Localism.owl");		
		StmtIterator re=model.listStatements();
		while(re.hasNext()){
			Statement sons=re.next();
			objects.add(sons.getObject());
		}
		return objects;
	}
	
	
}
