package com.buildcrm.repository;

import com.buildcrm.entity.EmployeeEntity;
import com.buildcrm.entity.ProjectEntity;
import com.buildcrm.enums.ProjectCategory;
import com.buildcrm.enums.ProjectStatus;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProjectRepository extends JpaRepository<ProjectEntity, UUID>, JpaSpecificationExecutor<ProjectEntity> {
     @EntityGraph(attributePaths = {"projectManager"})
    Optional<ProjectEntity> findByIdAndDeletedFalse(UUID id);
    
    Optional<ProjectEntity> findByProjectCodeAndDeletedFalse(String projectCode);
    
    List<ProjectEntity> findByStatusAndDeletedFalse(ProjectStatus status);
    
    List<ProjectEntity> findByCategoryAndDeletedFalse(ProjectCategory category);


    @Query("""
        SELECT DISTINCT t
        FROM ProjectEntity p
        JOIN p.team t
        WHERE p.id = :id AND p.deleted = false
    """)
    List<EmployeeEntity> findTeamByProjectId(@Param("id") UUID id);

//   @Query("""
//         SELECT DISTINCT p
//         FROM ProjectEntity p
//         LEFT JOIN FETCH p.team t
//         LEFT JOIN FETCH p.inventories i
//         LEFT JOIN FETCH p.projectManager pm
//         WHERE p.id = :id
//         AND p.deleted = false
//     """)
//     Optional<ProjectEntity> findByIdAndDeletedFalse(UUID id);
}
