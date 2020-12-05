package com.atguigu.crud.test;

import com.atguigu.crud.bean.Department;
import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.dao.DepartmentMapper;
import com.atguigu.crud.dao.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class MapperTest {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    EmployeeMapper employeeMapper;
    @Test
    public void testCrud() {
        System.out.println(departmentMapper);
    }

    @Test
    public void testFindAll() {
//        System.out.println(employeeMapper.selectAllWithDept());
//        Department department = new Department();
//        department.setDeptName("测试部");
//        departmentMapper.insert(department);
        /*for(int i = 3; i<50;i ++) {
            employeeMapper.insert(new Employee(i,"hello","M","456789",1));
        }*/
       // departmentMapper.deleteByPrimaryKey(2);
        Employee employee = employeeMapper.selectByEmpName("张三");
        System.out.println(employee);
    }
}
