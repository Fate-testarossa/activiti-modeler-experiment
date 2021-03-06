/*******************************************************************************
 * Signavio Core Components
 * Copyright (C) 2012  Signavio GmbH
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package com.signavio.warehouse.business.util.jpdl4;

import java.io.StringWriter;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.NamedNodeMap;

public class Task extends Node {

    private String assignee;
    private String candidateGroups;
    private String candidateUsers;
    private String swimlane;

    public Task(JSONObject task) {

        this.name = JsonToJpdl.getAttribute(task, "name");
        this.assignee = JsonToJpdl.getAttribute(task, "assignee");
        this.candidateGroups = JsonToJpdl.getAttribute(task,
                "candidate-groups");
        this.candidateUsers = JsonToJpdl.getAttribute(task,
                "candidate-users");
        this.swimlane = JsonToJpdl.getAttribute(task, "swimlane");
        this.bounds = JsonToJpdl.getBounds(task);
        this.outgoings = JsonToJpdl.getOutgoings(task);

    }

    public Task(org.w3c.dom.Node task) {
        this.uuid = "oryx_" + UUID.randomUUID().toString();
        NamedNodeMap attributes = task.getAttributes();
        this.name = JpdlToJson.getAttribute(attributes, "name");
        this.assignee = JpdlToJson.getAttribute(attributes, "assignee");
        this.candidateGroups = JpdlToJson.getAttribute(attributes, "candidate-groups");
        this.candidateUsers = JpdlToJson.getAttribute(attributes, "candidate-users");
        this.swimlane = JpdlToJson.getAttribute(attributes, "swimlane");

        this.bounds = JpdlToJson.getBounds(attributes.getNamedItem("g"));
    }

    public String getSwimlane() {
        return swimlane;
    }

    public void setSwimlane(String swimlane) {
        this.swimlane = swimlane;
    }

    public String getCandidateGroups() {
        return candidateGroups;
    }

    public void setCandidateGroups(String candidateGroups) {
        this.candidateGroups = candidateGroups;
    }

    public String getCandidateUsers() {
        return candidateUsers;
    }

    public void setCandidateUsers(String candidateUsers) {
        this.candidateUsers = candidateUsers;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    @Override
    public String toJpdl() throws InvalidModelException {
        StringWriter jpdl = new StringWriter();
        jpdl.write("  <task");

        jpdl.write(JsonToJpdl.transformAttribute("name", name));
        if (assignee != null && assignee.length() > 0)
            jpdl.write(JsonToJpdl.transformAttribute("assignee", assignee));
        if (candidateGroups != null && candidateGroups.length() > 0) {
            jpdl.write(JsonToJpdl.transformAttribute("candidate-groups",
                    candidateGroups));
        }
        if (candidateUsers != null && candidateUsers.length() > 0) {
            jpdl.write(JsonToJpdl.transformAttribute("candidate-users",
                    candidateUsers));
        }
        if (swimlane != null) {
            jpdl.write(JsonToJpdl.transformAttribute("swimlane", swimlane));
        }

        if (bounds != null) {
            jpdl.write(bounds.toJpdl());
        } else {
            throw new InvalidModelException("Invalid Task. Bounds is missing.");
        }

        if (outgoings.size() > 0) {
            jpdl.write(" >\n");
            for (Transition t : outgoings) {
                jpdl.write(t.toJpdl());
            }
            jpdl.write("  </task>\n\n");
        } else {
            jpdl.write(" />\n\n");
        }

        return jpdl.toString();
    }

    @Override
    public JSONObject toJson() throws JSONException {
        JSONObject stencil = new JSONObject();
        stencil.put("id", "Task");

        JSONArray outgoing = JpdlToJson.getTransitions(outgoings);

        JSONObject properties = new JSONObject();
        properties.put("bgcolor", "#ffffcc");
        if (name != null)
            properties.put("name", name);
        if (assignee != null)
            properties.put("assignee", assignee);
        if (candidateGroups != null)
            properties.put("candidate-groups", candidateGroups);
        if (candidateUsers != null)
            properties.put("candidate-users", candidateUsers);
        if (swimlane != null)
            properties.put("swimlane", swimlane);

        JSONArray childShapes = new JSONArray();

        return JpdlToJson.createJsonObject(uuid, stencil, outgoing, properties,
                childShapes, bounds.toJson());
    }

}
