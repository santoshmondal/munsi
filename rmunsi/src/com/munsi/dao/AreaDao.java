package com.munsi.dao;

import java.util.List;

import com.munsi.pojo.master.Area;

public interface AreaDao {

	public Boolean create(Area area);

	public Boolean update(Area area);

	public Boolean delete(String _id);

	/**
	 * Get Area instance, Area instance does not load references instance,
	 * 
	 * @param _id
	 *            AreaDao id to be loaded
	 * @return instance of Area
	 */
	public Area get(String _id);

	/**
	 * Get list of Area instance, Area instance does not load references
	 * instance,
	 * 
	 * @return ArraList of Area
	 */
	public List<Area> getAll();


}
