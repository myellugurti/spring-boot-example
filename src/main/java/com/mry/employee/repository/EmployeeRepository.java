package com.mry.employee.repository;

import com.mry.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Get employee by id
    Employee findById(long id);

}
