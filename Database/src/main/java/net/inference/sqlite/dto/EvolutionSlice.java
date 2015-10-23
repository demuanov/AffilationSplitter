package net.inference.sqlite.dto;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import net.inference.database.dto.IEvolution;
import net.inference.database.dto.IEvolutionSlice;

/**
 * Date: 2/1/2015
 * Time: 5:53 PM
 *
 * @author xanderblinov
 */
@DatabaseTable(tableName = IEvolutionSlice.TABLE_NAME)
public class EvolutionSlice implements IEvolutionSlice
{     
	
	@DatabaseField(columnName = Column.id, generatedId = true)
	private int mId;
	@DatabaseField(columnName = Column.year)
	private String mYear;
	@DatabaseField(columnName = Column.time)
	private String mTime;

    @DatabaseField(foreign = true, columnName = Column.evolution_id)
    private Evolution evolution;

    public EvolutionSlice() {
        // for ormlite
    }

    public EvolutionSlice(String mYear, String mTime, Evolution evolution) {
        this.mYear = mYear;
        this.mTime = mTime;
        this.evolution = evolution;
    }

    public EvolutionSlice(Evolution evolution) {
        this.evolution = evolution;
    }

    public long getId()
	{
		return mId;
	}

	public String getYear()
	{
		return mYear;
	}

	public void setYear(final String year)
	{
		mYear = year;
	}

	public String getTime()
	{
		return mTime;
	}

	public void setTime(final String time)
	{
		mTime = time;
	}


    @Override
    public IEvolution getEvolution() {
        return evolution;
    }

    @Override
    public void setEvolution(IEvolution evolution) {
        this.evolution = (Evolution) evolution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EvolutionSlice that = (EvolutionSlice) o;

        if (mTime != null ? !mTime.equals(that.mTime) : that.mTime != null) return false;
        if (mYear != null ? !mYear.equals(that.mYear) : that.mYear != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = mYear != null ? mYear.hashCode() : 0;
        result = 31 * result + (mTime != null ? mTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EvolutionSlice{" +
                "mId=" + mId +
                ", mYear='" + mYear + '\'' +
                ", mTime='" + mTime + '\'' +
                ", evolution=" + evolution +
                '}';
    }
}
