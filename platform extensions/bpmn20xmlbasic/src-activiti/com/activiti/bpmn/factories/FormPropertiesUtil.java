package com.activiti.bpmn.factories;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.xml.namespace.QName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.activiti.bpmn.elements.ActivitiExtensionFormPropertyElement;
import com.activiti.bpmn.elements.ActivitiExtensionFormPropertyValueElement;

import de.hpi.bpmn2_0.model.BaseElement;

public class FormPropertiesUtil {

    public static void processFormProperties(BaseElement task, String json) {
        try {
            JSONObject modelJSON = new JSONObject(json);
            int count = modelJSON.getInt("totalCount");
            JSONArray arr = modelJSON.getJSONArray("items");
            
            SortedSet<ActivitiExtensionFormPropertyElement> formPropertiesSet = new TreeSet<ActivitiExtensionFormPropertyElement>();
            
            for (int i=0;i<count;i++) {
                ActivitiExtensionFormPropertyElement e = new ActivitiExtensionFormPropertyElement();
                JSONObject o = arr.getJSONObject(i);
                for (String propName : new String[] {"name","variable","id","required","writable","type","expression"}) {
                    Object value = null;
                    if (propName.equals("required") || propName.equals("writable")) {
                        value = Boolean.valueOf(o.getString(propName));
                    } else {
                        if (!o.isNull(propName)) {
                            value = o.getString(propName);
                        }
                    }
                    if (value!=null && !value.toString().isEmpty()) {
                        e.getExtensionAttributes().put(new QName(propName), value.toString());
                    }
                }

                String values = o.getString("typeext");
                if (values != null && !values.isEmpty() && !values.trim().isEmpty()) {
                    JSONObject valuesObj = new JSONObject(values);
                    
                    for (String name: JSONObject.getNames(valuesObj)) {
                     
                     ActivitiExtensionFormPropertyValueElement v = new ActivitiExtensionFormPropertyValueElement();
                     v.getExtensionAttributes().put(new QName("id"), name);
                     v.getExtensionAttributes().put(new QName("name"),valuesObj.getString(name));
                     e.getValues().add(v);
                     
                    }  
                }
                
                e.setPorder(o.getString("porder"));

                if (o.has("datePattern")) {
                    e.setDatePattern(o.getString("datePattern"));
                }

                formPropertiesSet.add(e);
                
            }

            for (ActivitiExtensionFormPropertyElement e: formPropertiesSet) {
                task.getOrCreateExtensionElements().add(e);
            }

            
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
    
}
