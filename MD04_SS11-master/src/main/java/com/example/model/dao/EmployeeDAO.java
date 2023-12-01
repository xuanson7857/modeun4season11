package com.example.model.dao;

import com.example.model.entity.Employee;
import com.example.utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class EmployeeDAO implements IEmployeeDAO {
    @Override
    public List<Employee> findAll() {
        Connection connection = null;
        List<Employee> EmployeeList = new ArrayList<>();

        try {
            connection = ConnectionDB.openConnection();
            CallableStatement callableStatement = connection.prepareCall("{CALL FINDALL()}");
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setPhone(resultSet.getInt("phone"));
                employee.setAddress(resultSet.getString("address"));
                employee.setBirthday(resultSet.getDate("birthday"));
                employee.setSex(resultSet.getBoolean("sex"));
                employee.setSalary(resultSet.getFloat("salary"));
                EmployeeList.add(employee);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return EmployeeList;
    }

    @Override
    public boolean save(Employee employee) {
        Connection connection = ConnectionDB.openConnection();
        Employee employee1 = new Employee();
        employee1.setId(employee.getId());
        employee1.setName(employee.getName());
        employee1.setPhone(employee.getPhone());
        employee1.setAddress(employee.getAddress());
        employee1.setBirthday(employee.getBirthday());
        employee1.setSex(employee.isSex());
        employee1.setSalary(employee.getSalary());
        try {
            CallableStatement callableStatement = connection.prepareCall("{call ADD_EMPLOYEE(?,?,?,?,?,?)}");
            callableStatement.setString(1, employee1.getName());
            callableStatement.setInt(2, employee1.getPhone());
            callableStatement.setString(3, employee1.getAddress());
            java.util.Date utilDate = employee.getBirthday();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            callableStatement.setDate(4, sqlDate);
            callableStatement.setBoolean(5, employee1.isSex());
            callableStatement.setFloat(6, employee1.getSalary());
            int check = callableStatement.executeUpdate();
            if (check > 1) {
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            ConnectionDB.closeConnection(connection);
        }
        return false;
    }

    @Override
    public Employee findById(Integer id) {

        Employee employee = new Employee();
        Connection connection = ConnectionDB.openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall("{call FIND_BY_ID(?)}");
            callableStatement.setInt(1, id);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setAddress(resultSet.getString("address"));
                employee.setPhone(resultSet.getInt("phone"));
                employee.setBirthday(resultSet.getDate("birthday"));
                employee.setSex(resultSet.getBoolean("sex"));
                employee.setSalary(resultSet.getInt("salary"));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return employee;
    }

    @Override
    public boolean update(Employee employee, Integer id) {
        Connection connection = ConnectionDB.openConnection();

        try {
            CallableStatement callableStatement = connection.prepareCall("{call UPDATE_EMPLOYEE(?, ? , ?, ?, ?, ?, ?)}");
            callableStatement.setInt(1, id);
            callableStatement.setString(2, employee.getName());
            callableStatement.setInt(3, employee.getPhone());
            callableStatement.setString(4, employee.getAddress());
            java.util.Date utilDate = employee.getBirthday();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            callableStatement.setDate(5, sqlDate);
            callableStatement.setBoolean(6, employee.isSex());
            callableStatement.setFloat(7, employee.getSalary());
            int check = callableStatement.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        Connection connection = ConnectionDB.openConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("{call DELETE_EMPLOYEE(?)}");
            callableStatement.setInt(1, id);
            int check = callableStatement.executeUpdate();
            if (check > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(connection);
        }

        return false;
    }
    public List<Employee> getSortedEmployeetList() {
        List<Employee> sortedStudentList = findAll();
        Collections.sort(sortedStudentList, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        System.out.println(sortedStudentList);
        return sortedStudentList;
    }
    public List<Employee> searchName(String searchTerm) {
        List<Employee> studentList = new ArrayList<>();
        Connection connection = ConnectionDB.openConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("{call FIND_BY_NAME(?)}");
            callableStatement.setString(1, searchTerm);
            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setName(resultSet.getString("name"));
                employee.setPhone(resultSet.getInt("phone"));
                employee.setAddress(resultSet.getString("address"));
                employee.setBirthday(resultSet.getDate("Birthday"));
                employee.setSex(resultSet.getBoolean("sex"));
                employee.setSalary(resultSet.getFloat("salary"));
                studentList.add(employee);
            }
        } catch (SQLException e) {
        } finally {
            ConnectionDB.closeConnection(connection);
        }
        return studentList;
    }
}
