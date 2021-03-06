/*******************************************************************************
 * Copyright (c) 2007 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat, Inc. - initial API and implementation
 ******************************************************************************/ 
package org.jboss.tools.hibernate.ui.xml.editor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.jboss.tools.common.meta.XAttribute;
import org.jboss.tools.common.meta.constraint.XAttributeConstraint;
import org.jboss.tools.common.meta.constraint.impl.XAttributeConstraintAList;
import org.jboss.tools.common.model.XModelObjectConstants;
import org.jboss.tools.common.model.loaders.impl.PropertiesLoader;
import org.jboss.tools.common.model.ui.attribute.AttributeContentProposalProviderFactory;
import org.jboss.tools.common.model.ui.attribute.adapter.JavaClassContentAssistProvider;
import org.jboss.tools.common.model.ui.attribute.adapter.PropertiesContentProposalProvider;
import org.jboss.tools.common.model.util.EclipseResourceUtil;
import org.jboss.tools.hibernate.xml.model.impl.HibConfigComplexPropertyImpl;

/**
 * 
 * @author Viacheslav Kabanovich
 *
 */
public class HibernatePropertiesContentProposalProvider extends PropertiesContentProposalProvider {

	public HibernatePropertiesContentProposalProvider() {}

	public IContentProposal[] getProposals(String contents, int position) {
		Map<String, XAttribute> attributes = HibernatePropertiesContentAssistProcessor.getAttributes(object);
		
		List<IContentProposal> result = new ArrayList<IContentProposal>();

		if(isNameAttribute()) {
			String[] ps = attributes.keySet().toArray(new String[0]);
			Set<String> unique = new HashSet<String>();
			String prefix = contents.substring(0, position);
			for (int i = 0; i < ps.length; i++) {
				if(ps[i].startsWith(prefix)) {
					int dot = ps[i].indexOf('.', prefix.length());
					String prop = (dot < 0) ? ps[i] : ps[i].substring(0, dot + 1);
					if(unique.contains(prop)) continue;
					unique.add(prop);
					IContentProposal cp = AttributeContentProposalProviderFactory.makeContentProposal(prop, prop, HibernatePropertiesContentAssistProcessor.getDescription(prop));
					result.add(cp);
				}
			}			
		} else {
			String valuePrefix = contents.substring(0, position);
			String propertyName = getPropertyName();
			if(attributes.containsKey(propertyName)) {
				XAttribute attr = attributes.get(propertyName);
				if(attr == null) {
					return new IContentProposal[0];
				}
				XAttributeConstraint c = attr.getConstraint();
				if(c instanceof XAttributeConstraintAList) {
					String[] vs = ((XAttributeConstraintAList)c).getValues();
					for (int i = 0; i < vs.length; i++) {
						if(vs[i].length() == 0) continue;
						if(vs[i].startsWith(valuePrefix)) {
							IContentProposal cp = AttributeContentProposalProviderFactory.makeContentProposal(vs[i], vs[i], null);
							result.add(cp);
						}
					}
				} else if("AccessibleJava".equals(attr.getEditor().getName())) { //$NON-NLS-1$
					result.addAll(getJavaTypeContentProposals(contents, position));
				} else {
					//TODO
				}
			}
		}

		return result.toArray(new IContentProposal[0]);
	}

	protected boolean isPropertyEntity(String entity) {
		return super.isPropertyEntity(entity) || HibConfigComplexPropertyImpl.ENT_PROPERTY.equals(entity);
	}

}
