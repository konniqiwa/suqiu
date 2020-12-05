package com.atguigu.crud.dao;

import com.atguigu.crud.bean.Employee;
import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Integer empId);

    int insert(Employee record);

    Employee selectByPrimaryKey(Integer empId);

    Employee selectByPrimaryKeyWithDept(Integer empId);

    List<Employee> selectAll();

    List<Employee> selectAllWithDept();

    int updateByPrimaryKey(Employee record);

    Employee selectByEmpName(String empName);
}