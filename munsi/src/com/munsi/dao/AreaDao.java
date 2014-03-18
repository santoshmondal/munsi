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
	 * @param _id
	 *            Area id to be loaded
	 * @param withReferences
	 *            - Boolean, if true Area object load its references, if false
	 *            Area object does not load its references. (default is false)
	 * @return ArraList of Area
	 */
	public Area get(String _id, Boolean withReferences);

	/**
	 * Get list of Area instance, Area instance does not load references
	 * instance,
	 * 
	 * @return ArraList of Area
	 */
	public List<Area> getAll();

	/**
	 * @param withReferences
	 *            - Boolean, if true Area object load its references, if false
	 *            Area object does not load its references.(default is false)
	 * @return ArraList of Areas
	 */
	public List<Area> getAll(Boolean withReferences);

	/**
	 * @return List of String array, String array contains id at 0 index, and
	 *         name at 1 index
	 */
	List<String[]> getIdName();

}
