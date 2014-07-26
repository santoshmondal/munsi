package com.munsi.dao;

import java.util.List;

import com.munsi.pojo.master.Beat;

public interface BeatDao {

	public Boolean create(Beat beat);

	public Boolean update(Beat beat);

	public Boolean delete(String _id);

	/**
	 * Get Beat instance, Beat instance does not load references instance,
	 * 
	 * @param _id
	 *            BeatDao id to be loaded
	 * @return instance of Beat
	 */
	public Beat get(String _id);

	public Beat get(String _id, Boolean withReferences);

	/**
	 * Get list of Beat instance, Beat instance does not load references
	 * instance,
	 * 
	 * @return ArraList of Beat
	 */
	public List<Beat> getAll();

	public List<Beat> getAll(Boolean withReferences);
	
	public List<Beat> getBeatListByArea(String area_id);

	public List<Beat> getBeatListByArea(String area_id, Boolean withReferences);

}
