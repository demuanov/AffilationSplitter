package net.inference.database.dto;

import java.util.ArrayList;
import net.inference.sqlite.dto.Article;
import net.inference.sqlite.dto.Term;


/**
 * Date: 20-Sep-15
 * Time: 4:40 PM
 *
 * @author xanderblinov
 */
public interface IPrimitiveTerm
{
	String TABLE_NAME = "primitive_term";

	class Column
	{
		public static final String value = "value";
		public static final String id = "id";
		public static final String type = "type";
		public static final String year = "year";
		public static final String publication = "publication";
		public static final String term = "term";
	}

	String getPublication();

	void setPublication(final String publication);

	ArrayList<String> separatePrimitiveTerms();

	void setYear(int year);

	int getYear();

}

