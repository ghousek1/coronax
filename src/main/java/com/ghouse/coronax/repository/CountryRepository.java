package com.ghouse.coronax.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ghouse.coronax.model.Country;

public interface CountryRepository extends JpaRepository<Country,String> {

}
