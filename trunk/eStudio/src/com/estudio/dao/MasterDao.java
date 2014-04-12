package com.estudio.dao;

import java.util.List;

import com.estudio.pojo.Master;

public interface MasterDao {

	public Boolean create(Master customer);

	public Boolean update(Master customer);

	public Boolean delete(String _id);

	public Master get(String _id);

	public List<Master> getAll();

}
