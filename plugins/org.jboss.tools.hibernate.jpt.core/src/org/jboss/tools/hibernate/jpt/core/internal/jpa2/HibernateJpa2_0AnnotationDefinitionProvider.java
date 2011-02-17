/*******************************************************************************
  * Copyright (c) 2008-2009 Red Hat, Inc.
  * Distributed under license by Red Hat, Inc. All rights reserved.
  * This program is made available under the terms of the
  * Eclipse Public License v1.0 which accompanies this distribution,
  * and is available at http://www.eclipse.org/legal/epl-v10.html
  *
  * Contributor:
  *     Red Hat, Inc. - initial API and implementation
  ******************************************************************************/
package org.jboss.tools.hibernate.jpt.core.internal.jpa2;

import java.util.List;

import org.eclipse.jpt.core.JpaAnnotationDefinitionProvider;
import org.eclipse.jpt.core.internal.AbstractJpaAnnotationDefintionProvider;
import org.eclipse.jpt.core.resource.java.AnnotationDefinition;
import org.jboss.tools.hibernate.jpt.core.internal.resource.java.DiscriminatorFormulaAnnotationImpl.DiscriminatorFormulaAnnotationDefinition;
import org.jboss.tools.hibernate.jpt.core.internal.resource.java.ForeignKeyAnnotationImpl.ForeignKeyAnnotationDefinition;
import org.jboss.tools.hibernate.jpt.core.internal.resource.java.GeneratedAnnotationImpl.GeneratedAnnotationDefinition;
import org.jboss.tools.hibernate.jpt.core.internal.resource.java.GenericGeneratorAnnotationImpl.GenericGeneratorAnnotationDefinition;
import org.jboss.tools.hibernate.jpt.core.internal.resource.java.HibernateNamedNativeQueriesAnnotationDefinition;
import org.jboss.tools.hibernate.jpt.core.internal.resource.java.HibernateNamedNativeQueryAnnotationDefinition;
import org.jboss.tools.hibernate.jpt.core.internal.resource.java.HibernateNamedQueriesAnnotationDefinition;
import org.jboss.tools.hibernate.jpt.core.internal.resource.java.HibernateNamedQueryAnnotationDefinition;
import org.jboss.tools.hibernate.jpt.core.internal.resource.java.IndexAnnotationImpl.IndexAnnotationDefinition;
import org.jboss.tools.hibernate.jpt.core.internal.resource.java.SourceGenericGeneratorsAnnotation.GenericGeneratorsAnnotationDefinition;
import org.jboss.tools.hibernate.jpt.core.internal.resource.java.TypeAnnotationImpl.TypeAnnotationDefinition;

/**
 * @author Dmitry Geraskov
 *
 */
public class HibernateJpa2_0AnnotationDefinitionProvider extends AbstractJpaAnnotationDefintionProvider
{	
	// singleton
	private static final JpaAnnotationDefinitionProvider INSTANCE = new HibernateJpa2_0AnnotationDefinitionProvider();

	/**
	 * Return the singleton.
	 */
	public static JpaAnnotationDefinitionProvider instance() {
		return INSTANCE;
	}

	/**
	 * Ensure single instance.
	 */
	private HibernateJpa2_0AnnotationDefinitionProvider() {
		super();
	}

	@Override
	protected void addTypeAnnotationDefinitionsTo(List<AnnotationDefinition> definitions) {
		definitions.add(GenericGeneratorAnnotationDefinition.instance());
		definitions.add(GenericGeneratorsAnnotationDefinition.instance());
		definitions.add(HibernateNamedQueryAnnotationDefinition.instance());
		definitions.add(HibernateNamedQueriesAnnotationDefinition.instance());
		definitions.add(HibernateNamedNativeQueryAnnotationDefinition.instance());
		definitions.add(HibernateNamedNativeQueriesAnnotationDefinition.instance());
		definitions.add(DiscriminatorFormulaAnnotationDefinition.instance());
		definitions.add(ForeignKeyAnnotationDefinition.instance());
	}
	
	@Override
	protected void addAttributeAnnotationDefinitionsTo(List<AnnotationDefinition> definitions) {
		definitions.add(GenericGeneratorAnnotationDefinition.instance());
		definitions.add(GeneratedAnnotationDefinition.instance());
		definitions.add(IndexAnnotationDefinition.instance());
		definitions.add(TypeAnnotationDefinition.instance());
		definitions.add(ForeignKeyAnnotationDefinition.instance());
	}
}
