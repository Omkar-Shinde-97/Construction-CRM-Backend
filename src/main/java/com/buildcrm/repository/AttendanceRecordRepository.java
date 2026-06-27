package com.buildcrm.repository;

import com.buildcrm.entity.AttendanceRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecordEntity, UUID> {
    Optional<AttendanceRecordEntity> findByIdAndDeletedFalse(UUID id);
    List<AttendanceRecordEntity> findByEmployeeIdAndAttendanceDateBetweenAndDeletedFalse(UUID employeeId, LocalDate from, LocalDate to);
}
