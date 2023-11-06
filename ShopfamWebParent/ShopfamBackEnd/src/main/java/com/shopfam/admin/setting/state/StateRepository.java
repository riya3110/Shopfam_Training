package com.shopfam.admin.setting.state;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.shopfam.common.entity.Country;
import com.shopfam.common.entity.State;

public interface StateRepository extends CrudRepository<State , Integer>{
	
	public List<State> findByCountryOrderByNameAsc(Country country);

}
