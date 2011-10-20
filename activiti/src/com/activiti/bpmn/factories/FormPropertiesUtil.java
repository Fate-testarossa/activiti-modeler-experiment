package com.activiti.bpmn.factories;

import javax.xml.namespace.QName;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.activiti.bpmn.elements.ActivitiExtensionFormPropertyElement;

import de.hpi.bpmn2_0.model.BaseElement;

public class FormPropertiesUtil {

    public static void processFormProperties(BaseElement task, String json) {
        try {
            JSONObject modelJSON = new JSONObject(json);
            int count = modelJSON.getInt("totalCount");
            JSONArray arr = modelJSON.getJSONArray("items");
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
                task.getOrCreateExtensionElements().add(e);
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
    
}