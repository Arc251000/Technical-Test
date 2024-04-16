package mswaste_manager.repository;

import mswaste_manager.model.entity.WasteCenterAuthorizationEntity;
import mswaste_manager.model.entity.WasteManagerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface WasteCenterAuthorizationJPARepository extends JpaRepository<WasteCenterAuthorizationEntity, Serializable> {

}
