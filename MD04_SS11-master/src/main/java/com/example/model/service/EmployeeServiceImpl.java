package com.example.model.service;

import com.example.model.dao.EmployeeDAO;
import com.example.model.entity.Employee;

import java.util.List;

public class EmployeeServiceImpl implements IEmployeeService{
    EmployeeDAO employeeDAO = new EmployeeDAO();
    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    public boolean save(Employee employee) {
        return employeeDAO.save(employee);
    }

    @Override
    public Employee findById(Integer id) {
        return employeeDAO.findById(id);
    }

    @Override
    public boolean update(Employee employee, Integer id) {
        return employeeDAO.update(employee,id);
    }

    @Override
    public boolean delete(Integer id) {
        return employeeDAO.delete(id);
    }
    public List<Employee> getSortedEmployeetList() {
        return employeeDAO.getSortedEmployeetList();
    }
    public List<Employee> searchName(String searchTerm) {
        return employeeDAO.searchName(searchTerm);
    }
}
