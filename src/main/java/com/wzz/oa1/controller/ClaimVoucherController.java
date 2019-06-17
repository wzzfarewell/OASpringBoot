package com.wzz.oa1.controller;

import com.wzz.oa1.dto.ClaimVoucherInfo;
import com.wzz.oa1.global.Constant;
import com.wzz.oa1.pojo.ClaimVoucher;
import com.wzz.oa1.pojo.ClaimVoucherItem;
import com.wzz.oa1.pojo.DealRecord;
import com.wzz.oa1.pojo.Employee;
import com.wzz.oa1.service.ClaimVoucherService;
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
 * @Date: 2019/6/16
 **/
@Controller
@RequestMapping("/claim_voucher")
public class ClaimVoucherController {

    @Autowired
    private ClaimVoucherService claimVoucherService;

    @RequestMapping("/to_add")
    public String toAdd(Map<String, Object> map){
        map.put("items", Constant.getItems());
        map.put("info", new ClaimVoucherInfo());
        return "claim_voucher_add";
    }

    @PostMapping("/add")
    public String add(ClaimVoucherInfo info, HttpSession session){
        Employee employee = (Employee) session.getAttribute(Constant.CURRENT_USER);
        info.getClaimVoucher().setCreateSn(employee.getSn());
        claimVoucherService.save(info.getClaimVoucher(), info.getItems());
        return "redirect:deal";
    }

    @RequestMapping(value = "/detail", params = "id")
    public String detail(int id, Map<String, Object> map){
        map.put("claimVoucher", claimVoucherService.select(id));
        map.put("items", claimVoucherService.getItems(id));
        map.put("records", claimVoucherService.getRecords(id));
        return "claim_voucher_detail";
    }

    @RequestMapping("/self")
    public String self(HttpSession session, Map<String, Object> map){
        Employee employee = (Employee) session.getAttribute(Constant.CURRENT_USER);
        map.put("list", claimVoucherService.getForSelf(employee.getSn()));
        return "claim_voucher_self";
    }

    @RequestMapping("/deal")
    public String deal(HttpSession session, Map<String, Object> map){
        Employee employee = (Employee) session.getAttribute(Constant.CURRENT_USER);
        map.put("list", claimVoucherService.getForDeal(employee.getSn()));
        return "claim_voucher_deal";
    }

    @RequestMapping("/to_update")
    public String toUpdate(int id, Map<String, Object> map){
        map.put("items", Constant.getItems());
        ClaimVoucherInfo info = new ClaimVoucherInfo();
        info.setClaimVoucher(claimVoucherService.select(id));
        info.setItems(claimVoucherService.getItems(id));
        map.put("info", info);
        return "claim_voucher_update";
    }

    @RequestMapping("/update")
    public String update(HttpSession session, ClaimVoucherInfo info){
        Employee employee = (Employee) session.getAttribute("employee");
        info.getClaimVoucher().setCreateSn(employee.getSn());
        claimVoucherService.update(info.getClaimVoucher(), info.getItems());
        return "redirect:deal";
    }

    @RequestMapping("/submit")
    public String submit(int id){
        claimVoucherService.submit(id);
        return "redirect:deal";
    }

    @RequestMapping("/to_check")
    public String toCheck(int id, Map<String, Object> map){
        map.put("claimVoucher", claimVoucherService.select(id));
        map.put("items", claimVoucherService.getItems(id));
        map.put("records", claimVoucherService.getRecords(id));
        DealRecord dealRecord = new DealRecord();
        dealRecord.setClaimVoucherId(id);
        map.put("record", dealRecord);
        map.put("constant", new Constant());
        return "claim_voucher_check";
    }

    @PostMapping("/check")
    public String check(HttpSession session, DealRecord dealRecord){
        Employee employee = (Employee) session.getAttribute("employee");
        dealRecord.setDealSn(employee.getSn());
        claimVoucherService.deal(dealRecord);
        return "redirect:deal";
    }
}
