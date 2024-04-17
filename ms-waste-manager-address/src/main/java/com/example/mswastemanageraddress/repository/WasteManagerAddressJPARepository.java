package com.example.mswastemanageraddress.repository;

import com.example.mswastemanageraddress.model.entity.WasteManagerAddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
@Repository
public interface WasteManagerAddressJPARepository extends JpaRepository<WasteManagerAddressEntity, Serializable> {

}
