package com.wzz.oa1.controller;

import com.wzz.oa1.global.Constant;
import com.wzz.oa1.global.ServerResponse;
import com.wzz.oa1.pojo.Employee;
import com.wzz.oa1.service.EmployeeService;
import com.wzz.oa1.service.GlobalService;
import com.wzz.oa1.utils.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * @Author: wzzap
 * @Description:
 * @Date: 2019/6/8
 **/
@Controller("globalController")
public class GlobalController {
    private Logger logger = LoggerFactory.getLogger(GlobalController.class);

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

    @Value("${upload.absolute-path}")
    public String uploadPath;           // 文件在服务器中的绝对路径

    @Value("${upload.access-path}")
    public String accessPath;           // 文件的访问路径前缀
    /**
     * 头像上传
     * @param uploadFile
     * @param request
     * @return
     */
    @PostMapping("/upload_icon")
    public String uploadFile(@RequestParam("uploadFile") MultipartFile uploadFile, HttpServletRequest request, HttpSession session) throws FileNotFoundException {
        File folder = new File(uploadPath);
//        logger.info(ResourceUtils.getURL("classpath:").getPath());
        if(!folder.isDirectory())
            folder.mkdirs();
        String oldName = uploadFile.getOriginalFilename();
        Employee employee = (Employee) session.getAttribute(Constant.CURRENT_USER);
        String newName = employee.getSn() + "_" + employee.getName() +
                oldName.substring(oldName.lastIndexOf("."), oldName.length());
        // 保存到数据库, 这里保存的是图片的访问路径
        ServerResponse<Employee> serverResponse = globalService.uploadIcon(accessPath + newName, employee);
        if(serverResponse.isSuccess()){
            try {
                // 保存到服务器，图片的绝对路径
                uploadFile.transferTo(new File(folder, newName));
                // 更新session
                session.setAttribute(Constant.CURRENT_USER, serverResponse.getData());
                logger.info(serverResponse.getData().getIcon().getPath());
//                 //返回访问路径
//                return request.getScheme() + "://" + request.getServerName() + ":" +
//                        request.getServerPort() + accessPath + newName;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:self";
    }
}
