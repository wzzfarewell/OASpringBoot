package com.wzz.oa1.controller;

import com.wzz.oa1.pojo.Department;
import com.wzz.oa1.pojo.Employee;
import com.wzz.oa1.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * @Author: wzzap
 * @Description:
 * @Date: 2019/6/16
 **/

@Controller
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/list")
    public String list(Map<String, Object> map){
        map.put("dList", departmentService.selectAll());
        return "department_list";
    }

    @RequestMapping(value = "/remove", params = "sn")
    public String remove(String sn){
        departmentService.remove(sn);
        return "redirect:list";
    }

    @RequestMapping(value = "/to_add")
    public String toAdd(Map<String, Object> map){
        map.put("department", new Department());
        return "department_add";
    }

    @PostMapping("/add")
    public String add(Department department){
        departmentService.add(department);
        return "redirect:list";
    }

    @RequestMapping(value = "/to_update", params = "sn")
    public String toUpdate(String sn,Map<String, Object> map){
        map.put("department", departmentService.select(sn));
        return "department_update";
    }

    @PostMapping("/update")
    public String update(Department department){
        departmentService.update(department);
        return "redirect:list";
    }

}
