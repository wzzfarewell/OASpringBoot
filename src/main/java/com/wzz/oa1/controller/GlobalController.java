package com.wzz.oa1.controller;

import com.wzz.oa1.global.Constant;
import com.wzz.oa1.pojo.Employee;
import com.wzz.oa1.service.EmployeeService;
import com.wzz.oa1.service.GlobalService;
import com.wzz.oa1.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author: wzzap
 * @Description:
 * @Date: 2019/6/8
 **/
@Controller("globalController")
public class GlobalController {

    @Autowired
    private GlobalService globalService;

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/to_login")
    public String toLogin(){
        return "login";
    }

    @PostMapping("login")
    public String login(HttpSession session, @RequestParam String sn, @RequestParam String password){
        Employee employee = globalService.login(sn, password);
        if(employee == null)
            return "redirect:to_login";
        session.setAttribute(Constant.CURRENT_USER, employee);
        return "redirect:self";
    }

    @RequestMapping("/self")
    public String self(){
        return "self";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        Employee employee = (Employee) session.getAttribute(Constant.CURRENT_USER);
        if(employee != null)
            session.setAttribute(Constant.CURRENT_USER, null);
        return "redirect:to_login";
    }

    @RequestMapping("/to_update_password")
    public String toUpdatePassword(){
        return "update_password";
    }

    @PostMapping("update_password")
    public String updatePassword(HttpSession session, @RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword1") String newPassword1,
                                 @RequestParam("newPassword2") String newPassword2){
        Employee employee = (Employee) session.getAttribute(Constant.CURRENT_USER);
        if(employee == null)
            return "redirect:to_login";
        if(globalService.checkPassword(employee.getSn(), oldPassword)){
            if(newPassword1.equals(newPassword2)){
                employee.setPassword(MD5Util.MD5EncodeUtf8(newPassword1));
                int resultCount = employeeService.updateEmployee(employee);
                if(resultCount > 0){
                    session.setAttribute(Constant.CURRENT_USER, employee);
                    return "redirect:self";
                }
            }
        }
        return "redirect:to_update_password";
    }

    @PostMapping("/upload_file")
    public String uploadFile(@RequestParam("uploadFile") MultipartFile multipartFile, HttpServletRequest request){
        return null;
    }
}
