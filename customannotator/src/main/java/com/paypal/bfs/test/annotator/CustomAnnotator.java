package com.paypal.bfs.test.annotator;


import com.fasterxml.jackson.databind.JsonNode;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;
import org.jsonschema2pojo.AbstractAnnotator;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

public class CustomAnnotator extends AbstractAnnotator {

    @Override
    public void propertyInclusion(JDefinedClass clazz, JsonNode schema) {
      
        // Note: does not have to be the propertyName, could be the field or propertyNode that is verified.
        System.out.println(clazz._package());
        if (clazz._package().name().contains
        		("com.paypal.bfs.test.bookingserv.api.model")) {
            clazz.annotate(Entity.class);
        }
    }
    @Override
    public void propertyField(JFieldVar field, JDefinedClass clazz, String propertyName, JsonNode propertyNode) {
        super.propertyField(field, clazz, propertyName, propertyNode);

        // Note: does not have to be the propertyName, could be the field or propertyNode that is verified.
        if (propertyName.equals("address")) {
            field.annotate(OneToOne.class).param("cascade",CascadeType.ALL);
        }
        if (propertyName.contains("id")) {
            field.annotate(Id.class);
            field.annotate(GeneratedValue.class).param("strategy", GenerationType.IDENTITY);
        }
        
        
    }
}
