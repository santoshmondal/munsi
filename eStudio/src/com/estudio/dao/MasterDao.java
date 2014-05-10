package com.estudio.dao;

import java.util.List;
import java.util.Map;

import com.estudio.pojo.master.Master;

public interface MasterDao {

	public Boolean create(Master customer);

	public Boolean update(Master customer);

	public Boolean delete(String _id);

	public Master get(String _id);

	public List<Master> getAll();

	public List<Master> getAllByField(Map<String, String> map);
}
