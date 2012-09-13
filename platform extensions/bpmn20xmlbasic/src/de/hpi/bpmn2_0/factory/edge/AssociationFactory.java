/**
 * Copyright (c) 2009
 * Philipp Giese, Sven Wagner-Boysen
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
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package de.hpi.bpmn2_0.factory.edge;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.oryxeditor.server.diagram.generic.GenericEdge;
import org.oryxeditor.server.diagram.generic.GenericShape;

import de.hpi.bpmn2_0.annotations.StencilId;
import de.hpi.bpmn2_0.exceptions.BpmnConverterException;
import de.hpi.bpmn2_0.factory.AbstractEdgesFactory;
import de.hpi.bpmn2_0.model.FormalExpression;
import de.hpi.bpmn2_0.model.connector.Association;
import de.hpi.bpmn2_0.model.connector.AssociationDirection;
import de.hpi.bpmn2_0.model.connector.DataAssociation;
import de.hpi.bpmn2_0.model.connector.DataInputAssociation;
import de.hpi.bpmn2_0.model.connector.DataOutputAssociation;
import de.hpi.bpmn2_0.model.connector.Edge;
import de.hpi.bpmn2_0.model.misc.Assignment;
import de.hpi.diagram.SignavioUUID;

/**
 * Factory that creates association elements
 * 
 * @author Philipp Giese
 * @author Sven Wagner-Boysen
 * 
 */
@StencilId( { "Association_Undirected", "Association_Unidirectional",
  "Association_Bidirectional" })
public class AssociationFactory extends AbstractEdgesFactory {

 private enum AssociationType {
  DATA_INPUT, DATA_OUTPUT, DATA, ASSOCIATION
 }

 /*
  * (non-Javadoc)
  * 
  * @seede.hpi.bpmn2_0.factory.AbstractBpmnFactory#createProcessElement(org.
  * oryxeditor.server.diagram.Shape)
  */
 // @Override
 protected Edge createProcessElement(GenericShape shape)
   throws BpmnConverterException {
  AssociationType associationType = this.determineAssociationType((GenericEdge)shape);
  if (associationType.equals(AssociationType.ASSOCIATION)) {
   Association association = new Association();
   association.setId(shape.getResourceId());
   association.setName(shape.getProperty("text"));
   association.setAssociationDirection(this
     .getAssociationDirectionFromShape(shape));
   return association;
  }

  /* Handle data associations */
  DataAssociation dataAssociation = null;
  if (associationType.equals(AssociationType.DATA_INPUT))
   dataAssociation = new DataInputAssociation();
  else if (associationType.equals(AssociationType.DATA_OUTPUT))
   dataAssociation = new DataOutputAssociation();
  else
   dataAssociation = new DataAssociation();

  /* Set common attributes */
  dataAssociation.setId(shape.getResourceId());
  dataAssociation.setName(shape.getProperty("name"));

  /* Set data association specific attributes */
  this.setDataAssociationAttributes(dataAssociation, shape);

  return dataAssociation;
 }

 /**
  * Returns the {@link AssociationDirection} for a given association shape.
  * 
  * @param shape
  *            The association resource shape.
  * @return The {@link AssociationDirection}
  */
 private AssociationDirection getAssociationDirectionFromShape(GenericShape shape) {
  if (shape.getStencilId().equals("Association_Undirected"))
   return AssociationDirection.NONE;
  else if (shape.getStencilId().equals("Association_Unidirectional"))
   return AssociationDirection.ONE;
  else if (shape.getStencilId().equals("Association_Bidirectional"))
   return AssociationDirection.BOTH;
  else
   return null;
 }

 /**
  * Determines whether the association is a data association or not.
  * 
  * <ul>
  * <li>DATA_INPUT: 'ID': Association_Unidirectional, 'source': data object,
  * 'target' activity</li>
  * <li>DATA_OUTPUT: 'ID': Association_Unidirectional, 'source': activity,
  * 'target' data object</li>
  * <li>DATA: 'ID': Bidirectional/Association_Undirected, 'source':
  * MessageFlow/SequenceFlow, 'target' data object/Message</li>
  * <li>ASSOCIATION: otherwise</li>
  * </ul>
  * 
  * 
  * 
  * @param shape
  *            The association resource shape
  */
 private AssociationType determineAssociationType(GenericEdge<?,?> shape) {
  String stencilId = shape.getStencilId();
  /* Retrieve source and target stencil ids */
  String targetId = (shape.getTarget() != null ? shape.getTarget()
    .getStencilId() : "");
  ArrayList<String> sourceIds = new ArrayList<String>();
  for (GenericShape sourceShape : shape.getIncomingsReadOnly()) {
   sourceIds.add(sourceShape.getStencilId());
  }

  /* Determine the appropriate association type */
  if (stencilId.equals("Association_Bidirectional"))
//   return AssociationType.DATA;
   return AssociationType.ASSOCIATION;
  else if (stencilId.equals("Association_Unidirectional")
    && (targetId.equals("DataObject") || targetId
      .equals("DataStore")))
   return AssociationType.DATA_OUTPUT;
  else if (stencilId.equals("Association_Unidirectional")
    && (sourceIds.contains("DataObject") || sourceIds
      .contains("DataStore")))
   return AssociationType.DATA_INPUT;
  else if (stencilId.equals("Association_Undirectional")
    /*&& ((sourceIds.contains("DataObject") || sourceIds
      .contains("DataStore")) && targetId
      .equals("SequenceFlow"))
    || (sourceIds.contains("SequenceFlow") && (targetId
      .equals("DataStore") || targetId.equals("DataObject")))*/)
//   return AssociationType.DATA;
   return AssociationType.ASSOCIATION;
  else
   return AssociationType.ASSOCIATION;
 }

 /**
  * Processes the data association attributes transformation and assignments.
  * 
  * @param dataAssociation
  * @param shape
  */
 private void setDataAssociationAttributes(DataAssociation dataAssociation,
   GenericShape shape) {
  /* Handle assignment property */
  String assignment = shape.getProperty("assignments");
  if (assignment != null && !(assignment.length() == 0)) {
   try {
    JSONObject assignmentJson = new JSONObject(assignment);
    JSONArray items = assignmentJson.getJSONArray("items");

    /* Handle each assignment expression */
    for (int i = 0; i < items.length(); i++) {
     JSONObject assignmentObject = items.getJSONObject(i);

     Assignment dataAssignment = new Assignment();
     dataAssignment.setId(SignavioUUID.generate());
     dataAssignment.setTo(assignmentObject.getString("to"));
     dataAssignment.setFrom(assignmentObject.getString("from"));
     dataAssignment.setLanguage(assignmentObject
       .getString("language"));

     dataAssociation.getAssignment().add(dataAssignment);
    }

   } catch (Exception e) {
    /* In case of an error, ignore the assignment attribute. */
   }
  }

  /* Handle transformation property */
  String transformation = shape.getProperty("transformation");
  if (transformation != null && !(transformation.length() == 0))
   dataAssociation.setTransformation(new FormalExpression(
     transformation));
 }

}
