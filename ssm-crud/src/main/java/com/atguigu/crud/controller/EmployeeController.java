package com.atguigu.crud.controller;

import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.bean.Msg;
import com.atguigu.crud.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @ResponseBody
    @RequestMapping("/checkuser")
    public Msg checkUser(@RequestParam("empName") String empName) {
        Employee employee = employeeService.checkUser(empName);
        if (employee == null) return Msg.success();
        else return Msg.fail();
    }

    @RequestMapping("/emps")
    @ResponseBody
    public Msg getEmpsByJson(@RequestParam(value = "pn",defaultValue = "1") Integer pn) {

        PageHelper.startPage(pn,5);
        List<Employee> emps = employeeService.getAll();

        PageInfo page = new PageInfo(emps, 5);

        return Msg.success().add("pageInfo",page);
    }

    @RequestMapping(value = "/emp",method = RequestMethod.POST)
    @ResponseBody
    public Msg saveEmp(Employee employee) {
        employeeService.saveEmp(employee);
        return Msg.success();
    }
}
