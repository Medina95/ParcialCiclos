package co.edu.escuelaing.cvds.lab8.repository;

import co.edu.escuelaing.cvds.lab8.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
