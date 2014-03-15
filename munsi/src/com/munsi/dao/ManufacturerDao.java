package com.munsi.dao;

import java.util.List;

import com.munsi.pojo.master.Manufacturer;

public interface ManufacturerDao extends BaseDao{

		public Boolean create(Manufacturer Manufacturer);
		
		public Boolean update(Manufacturer Manufacturer);
		
		public Boolean delete(String _id);
		
		/**
		 * Get Manufacturer instance, Manufacturer instance does not load references instance,  
		 * @param _id Manufacturer id to be loaded
		 * @return instance of Manufacturer
		 */
		public Manufacturer get(String _id);
		
		
		/**
		 * @param _id Manufacturer id to be loaded
		 * @param withReferences - Boolean, if true Manufacturer object load its references, if false 
		 * Manufacturer object does not load its references. (default is false)
		 * @return ArraList of Manufacturers
		 */
		public Manufacturer get(String _id, Boolean withReferences);
		
		
		/**
		 * Get list of Manufacturer instance, Manufacturer instance does not load references instance,
		 * @return ArraList of Manufacturers
		 */
		public List<Manufacturer> getAll();
		
		/**
		 * @param withReferences - Boolean, if true Manufacturer object load its references, if false 
		 * Manufacturer object does not load its references.(default is false)
		 * @return ArraList of Manufacturers
		 */
		public List<Manufacturer> getAll(Boolean withReferences);
		
		/**
		 * @return List of String array, String array contains id at 0 index, and name at 1 index
		 */
		List<String[]> getIdName();
		
		
}
