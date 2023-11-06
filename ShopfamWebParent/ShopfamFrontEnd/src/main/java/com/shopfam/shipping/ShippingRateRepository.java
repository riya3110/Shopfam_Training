package com.shopfam.shipping;

import org.springframework.data.repository.CrudRepository;

import com.shopfam.common.entity.Country;
import com.shopfam.common.entity.ShippingRate;

public interface ShippingRateRepository extends CrudRepository<ShippingRate , Integer> {
	
	public ShippingRate findByCountryAndState(Country country , String state);

}
