package adql.db.exception;

/*
 * This file is part of ADQLLibrary.
 *
 * ADQLLibrary is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * ADQLLibrary is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with ADQLLibrary.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2019 - UDS/Centre de Données astronomiques de Strasbourg (CDS)
 */

import adql.parser.feature.LanguageFeature;
import adql.parser.grammar.ParseException;
import adql.query.ADQLObject;

/**
 * Exception thrown when an ADQL language feature is used while declared as not
 * supported.
 *
 * @author Gr&eacute;gory Mantelet (CDS)
 * @version 2.0 (07/2019)
 * @since 2.0
 */
public class UnsupportedFeatureException extends ParseException {
	private static final long serialVersionUID = 1L;

	/** Function which can not be resolved. */
	protected final ADQLObject unsupportedExpression;

	/**
	 * Build the exception with the given unsupported expression.
	 *
	 * <p>The error message will be automatically generated.</p>
	 *
	 * @param obj	[REQUIRED] The unsupported expression.
	 */
	public UnsupportedFeatureException(final ADQLObject obj) {
		this(obj, null);
	}

	/**
	 * Build the exception with the given message and the given unsupported
	 * expression.
	 *
	 * <p>
	 * 	If no message is provided, a default one will be generated by using the
	 * 	given ADQL expression.
	 * </p>
	 *
	 * @param obj		[REQUIRED] The unsupported expression.
	 * @param message	[OPTIONAL] Custom error message.
	 */
	public UnsupportedFeatureException(final ADQLObject obj, final String message) {
		super((message == null ? buildDefaultMessage(obj) : message), obj.getPosition());
		unsupportedExpression = obj;
	}

	protected static String buildDefaultMessage(final ADQLObject obj) {
		return "Unsupported ADQL feature: \"" + obj.getFeatureDescription().form + "\"" + (obj.getFeatureDescription().type == null ? "" : " (of type '" + obj.getFeatureDescription().type + "')") + "!";
	}

	/**
	 * Get the unsupported expression.
	 *
	 * @return	The unsupported expression
	 */
	public final ADQLObject getUnsupportedExpression() {
		return unsupportedExpression;
	}

	/**
	 * Get the description of the unsupported language feature.
	 *
	 * @return	The unresolved language feature.
	 */
	public final LanguageFeature getUnsupportedLanguageFeature() {
		return unsupportedExpression.getFeatureDescription();
	}

}
