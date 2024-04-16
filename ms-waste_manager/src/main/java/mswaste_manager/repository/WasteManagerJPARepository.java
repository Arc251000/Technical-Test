package mswaste_manager.repository;

import mswaste_manager.model.entity.WasteManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface WasteManagerJPARepository extends  JpaRepository<WasteManagerEntity, Serializable> {
}
