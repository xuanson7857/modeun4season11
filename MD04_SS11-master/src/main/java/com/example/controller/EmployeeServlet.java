package com.example.controller;

import com.example.model.entity.Employee;
import com.example.model.service.EmployeeServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "EmployeeServlet", value = "/employee")
public class EmployeeServlet extends HttpServlet {
    private EmployeeServiceImpl employeeService;

    @Override
    public void init()  {
        employeeService = new EmployeeServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        int empolyeeId = 0;
        if(action == null) {
            showListEmployee(request,response);
        }else {
            switch (action) {
                case "add":
                    request.getRequestDispatcher("views/employee-add.jsp").forward(request,response);
                    break;
                case "edit":
                    empolyeeId = Integer.parseInt(request.getParameter("id"));
                    Employee employee = employeeService.findById(empolyeeId);
                    request.setAttribute("employee", employee);
                    request.getRequestDispatcher("views/employee-edit.jsp").forward(request, response);
                    break;
                case "delete":
                    empolyeeId = Integer.parseInt(request.getParameter("id"));
                    employeeService.delete(empolyeeId);
                    showListEmployee(request,response);
                    break;
                case "sort":
                    request.setAttribute("employeeList", employeeService.getSortedEmployeetList());
                    request.getRequestDispatcher("views/employee.jsp").forward(request, response);
                    break;
            }
        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int empolyeeId = 0;
        String action = request.getParameter("action");
        String employName = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String sex = request.getParameter("sex");
        String salary = request.getParameter("salary");
        Date birthday;
        Employee employee;
        if(action == null) {
            showListEmployee(request,response);
        } else {
            switch (action) {
                case "add" :
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd") ;
                    try {
                        java.util.Date date = simpleDateFormat.parse(request.getParameter("birthday"));
                        birthday = new Date(date.getTime());
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    employee =new Employee();
                    employee.setName(employName);
                    employee.setPhone(Integer.parseInt(phone));
                    employee.setAddress(address);
                    employee.setBirthday(birthday);
                    employee.setSex(Boolean.parseBoolean(sex));
                    employee.setSalary(Float.parseFloat(salary));
                    employeeService.save(employee);
                    showListEmployee(request,response);
                    break;
                case "update":
                    empolyeeId = Integer.parseInt(request.getParameter("id"));
                    try {
                       SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                        java.util.Date date = simpleDateFormat1.parse(request.getParameter("birthday"));
                        birthday = new java.sql.Date(date.getTime());
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                    employee = new Employee();
                    employee.setName(employName);
                    employee.setPhone(Integer.parseInt(phone));
                    employee.setBirthday(birthday);
                    employee.setAddress(address);
                    employee.setSex(Boolean.parseBoolean(sex));
                    employee.setSalary(Float.parseFloat(salary));
                    employeeService.update(employee, empolyeeId);
                    showListEmployee(request, response);
                    break;
                case "sort":
                    request.setAttribute("employeeList", employeeService.getSortedEmployeetList());
                    request.getRequestDispatcher("views/employee.jsp").forward(request, response);
                    break;
                case "search":
                    String searchTerm = request.getParameter("searchTerm");
                    List<Employee> searchResult = employeeService.searchName(searchTerm);
                    request.setAttribute("employeeList", searchResult);
                    request.getRequestDispatcher("views/employee.jsp").forward(request, response);
                    break;
            }
        }
    }
    private void showListEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> employeeList = employeeService.findAll();
        request.setAttribute("employeeList", employeeList);
        request.getRequestDispatcher("views/employee.jsp").forward(request,response);

    }
}