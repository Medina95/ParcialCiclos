package co.edu.escuelaing.cvds.lab8.service;

import co.edu.escuelaing.cvds.lab8.model.Employee;
import co.edu.escuelaing.cvds.lab8.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long employeeId) {
        return employeeRepository.getReferenceById(Long.valueOf(employeeId));
    }

    public void updateUser(Employee user) {
        employeeRepository.save(user);
    }
  
    public void deleteUser(Long userId) {
      employeeRepository.deleteById(Long.valueOf(userId));
    }
}
