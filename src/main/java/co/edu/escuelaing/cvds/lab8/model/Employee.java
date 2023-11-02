package co.edu.escuelaing.cvds.lab8.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
public class Employee {
    @Id
    @Column(name = "EMPLOYEE_ID")
    public Long employeeId;

    @Column(name = "FIRST_NAME")
    public String firstName;

    @Column(name = "LAST_NAME")
    public String lastName;

    @Column(name = "ROLE")
    public String role;

    @Column(name = "SALARY")
    public Double salary;

    @Column(name = "SEX")
    public String sex;

    @Column(name = "COMPANY")
    public String company;

    public Employee() {
    }

    public Employee(Long employeeId, String firstName, String lastName, String role, Double salary, String sex, String company) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.salary = salary;
        this.sex = sex;
        this.company = company;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

 

    @Override
    public java.lang.String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role='" + role + '\'' +
                ", salary=" + salary +
                ", sex='" + sex + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}