package net.inference.sqlite.dto;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import net.inference.database.dto.ITerm;

/**
 * Created by Мария on 03.11.15.
 */

@DatabaseTable(tableName = ITerm.TABLE_NAME)
public class Term implements ITerm
{

	@DatabaseField(columnName = Column.id, generatedId = true)
	private int mId;
	@DatabaseField(columnName = Column.value)
	private String mValue;
	@DatabaseField(columnName = Column.type)
	private String mType;

	public Term()
	{
		// ORMLite needs a no-arg constructor
	}


	public Term(String value, String type)
	{
		mValue = value;
		mType = type;
	}

	public  int getId(){ return mId; }

	public void setValue(final String value){ mValue = value; }

	public String getValue(){ return mValue; }

	public void setType(final String type){ mType = type; }

	public String getType(){ return mType; }



}
