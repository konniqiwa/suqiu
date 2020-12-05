package com.atguigu.crud.service;

import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.bean.EmployeeExample;
import com.atguigu.crud.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    public List<Employee> getAll() {
       return employeeMapper.selectAllWithDept();
    }

    public void saveEmp(Employee employee) {
        employeeMapper.insert(employee);
    }

    public Employee checkUser(String empName) {
        return employeeMapper.selectByEmpName(empName);
    }
}
