package com.zensar.olx.db;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zensar.olx.bean.AdvertisementPost;

public interface AdvertisementPostDAO extends JpaRepository<AdvertisementPost,Integer>{

}
