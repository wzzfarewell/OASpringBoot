package com.wzz.oa1.controller;

import com.wzz.oa1.global.Constant;
import com.wzz.oa1.global.ServerResponse;
import com.wzz.oa1.pojo.Employee;
import com.wzz.oa1.service.DepartmentService;
import com.wzz.oa1.service.EmployeeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @Author: wzzap
 * @Description:
 * @Date: 2019/6/9
 **/

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/list")
    public String list(Map<String, Object> map, HttpSession session){
        Employee employee = (Employee) session.getAttribute(Constant.CURRENT_USER);
        List<Employee> employeeList = employeeService.selectAll().getData();
        ServerResponse<List<Employee>> res;
        // 访问的人如果不是总经理，则不能操作和查看总经理级别的员工
        if(!employee.getPost().equals(Constant.POST_GM)){
            employeeList.removeIf(employee1 -> employee1.getPost().equals(Constant.POST_GM));
        }
        employeeList.forEach(employee1 -> employee1.setPassword(StringUtils.EMPTY));
        res = ServerResponse.createBySuccess(employeeList);
        map.put("res", res);
        return "employee_list";
    }

    @RequestMapping("/to_add")
    public String toAdd(Map<String, Object> map){
        map.put("employee", new Employee());
        map.put("dList", departmentService.selectAll());
        map.put("pList", Constant.getPosts());
        return "employee_add";
    }

    @PostMapping("/add")
    public String add(Employee employee, Map<String, Object> map){
        ServerResponse<String> res = employeeService.addEmployee(employee);
//        map.put("res", res);
        if(res.isSuccess())
            return "redirect:list";
//        map.put("msg", res.getMsg());
        return "redirect:to_add";
    }
}
