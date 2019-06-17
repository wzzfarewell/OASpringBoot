package com.wzz.oa1.service.impl;

import com.wzz.oa1.dao.ClaimVoucherItemMapper;
import com.wzz.oa1.dao.ClaimVoucherMapper;
import com.wzz.oa1.dao.DealRecordMapper;
import com.wzz.oa1.dao.EmployeeMapper;
import com.wzz.oa1.global.Constant;
import com.wzz.oa1.pojo.ClaimVoucher;
import com.wzz.oa1.pojo.ClaimVoucherItem;
import com.wzz.oa1.pojo.DealRecord;
import com.wzz.oa1.pojo.Employee;
import com.wzz.oa1.service.ClaimVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: wzzap
 * @Description:
 * @Date: 2019/6/16
 **/
@Service
public class ClaimVoucherServiceImpl implements ClaimVoucherService {
    @Autowired
    ClaimVoucherMapper claimVoucherMapper;
    @Autowired
    private ClaimVoucherItemMapper claimVoucherItemMapper;
    @Autowired
    private DealRecordMapper dealRecordMapper;
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public ClaimVoucher select(int id) {
        return claimVoucherMapper.selectById(id);
    }

    @Override
    public void save(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items) {
        claimVoucher.setNextDealSn(claimVoucher.getCreateSn());
        claimVoucher.setStatus(Constant.CLAIMVOUCHER_CREATED);
        claimVoucherMapper.insert(claimVoucher);    // 这里自动生成了属性id
        for(ClaimVoucherItem item: items){
            item.setClaimVoucherId(claimVoucher.getId());   // 所以这里可以得到
            claimVoucherItemMapper.insert(item);
        }
    }

    @Override
    public List<ClaimVoucherItem> getItems(int cvId) {
        return claimVoucherItemMapper.selectItems(cvId);
    }

    @Override
    public List<DealRecord> getRecords(int cvId) {
        return dealRecordMapper.selectByCvId(cvId);
    }

    @Override
    public List<ClaimVoucher> getForSelf(String sn) {
        return claimVoucherMapper.selectByCreateSn(sn);
    }

    @Override
    public List<ClaimVoucher> getForDeal(String sn) {
        return claimVoucherMapper.selectByNextDealSn(sn);
    }

    @Override
    public void update(ClaimVoucher claimVoucher, List<ClaimVoucherItem> items) {
        claimVoucher.setNextDealSn(claimVoucher.getCreateSn());
        claimVoucher.setStatus(Constant.CLAIMVOUCHER_CREATED);
        claimVoucherMapper.updateByPrimaryKey(claimVoucher);

        List<ClaimVoucherItem> olds = claimVoucherItemMapper.selectItems(claimVoucher.getId());
        // 1.删除不需要的条目
        for (ClaimVoucherItem old : olds) {
            boolean isHave = false;
            for (ClaimVoucherItem item: items) {
                if(item.getId().equals(old.getId())) {
                    isHave = true;
                    break;
                }
            }
            if(!isHave)     // 旧的条目不在新的条目中则删除
                claimVoucherItemMapper.deleteByPrimaryKey(old.getId());
        }
        for (ClaimVoucherItem item: items) {
            item.setClaimVoucherId(claimVoucher.getId());
            if(item.getId() != null && item.getId() > 0) {
                // 2.修改已有的条目
                claimVoucherItemMapper.updateByPrimaryKey(item);
            }else{
                // 3.添加新增的条目
                claimVoucherItemMapper.insert(item);
            }
        }
    }

    @Override
    public void submit(int id) {
        // 提交报销单操作
        ClaimVoucher claimVoucher = claimVoucherMapper.selectByPrimaryKey(id);
        Employee employee = employeeMapper.selectByPrimaryKey(claimVoucher.getCreateSn());
        claimVoucher.setStatus(Constant.CLAIMVOUCHER_SUBMIT);
        // 将待处理人变为创建人同部门的部门经理
        claimVoucher.setNextDealSn(employeeMapper.selectByDepartmentAndPost(
                employee.getDepartmentSn(), Constant.POST_FM).get(0).getSn());
        claimVoucherMapper.updateByPrimaryKey(claimVoucher);

        // 添加一条处理记录
        DealRecord dealRecord = new DealRecord();
        dealRecord.setDealWay(Constant.DEAL_SUBMIT);
        dealRecord.setDealSn(employee.getSn());
        dealRecord.setClaimVoucherId(id);
        dealRecord.setDealResult(Constant.CLAIMVOUCHER_SUBMIT);
        dealRecord.setComment("无");
        dealRecordMapper.insert(dealRecord);
    }

    @Override
    public void deal(DealRecord dealRecord) {
        ClaimVoucher claimVoucher = claimVoucherMapper.selectByPrimaryKey(dealRecord.getClaimVoucherId());
        Employee employee = employeeMapper.selectByPrimaryKey(dealRecord.getDealSn());
        dealRecord.setDealTime(new Date());
        // 处理通过
        switch (dealRecord.getDealWay()) {
            case Constant.DEAL_PASS:
                // 直接通过
                if (claimVoucher.getTotalAmount() <= Constant.LIMIT_CHECK || employee.getPost().equals(Constant.POST_GM)) {
                    claimVoucher.setStatus(Constant.CLAIMVOUCHER_APPROVED);
                    Employee e = employeeMapper.selectByDepartmentAndPost(null, Constant.POST_CASHIER).get(0);
                    claimVoucher.setNextDealSn(e.getSn());
                    dealRecord.setDealResult(Constant.CLAIMVOUCHER_APPROVED);
                } else {
                    // 需要复审
                    claimVoucher.setStatus(Constant.CLAIMVOUCHER_RECHECK);
                    Employee e = employeeMapper.selectByDepartmentAndPost(null, Constant.POST_GM).get(0);
                    claimVoucher.setNextDealSn(e.getSn());

                    dealRecord.setDealResult(Constant.CLAIMVOUCHER_RECHECK);
                }
                break;
            case Constant.DEAL_BACK:
                // 处理打回
                claimVoucher.setStatus(Constant.CLAIMVOUCHER_BACK);
                claimVoucher.setNextDealSn(claimVoucher.getCreateSn());
                dealRecord.setDealResult(Constant.CLAIMVOUCHER_BACK);
                break;
            case Constant.DEAL_REJECT:
                // 处理拒绝
                claimVoucher.setStatus(Constant.CLAIMVOUCHER_TERMINATED);
                claimVoucher.setNextDealSn(null);
                dealRecord.setDealResult(Constant.CLAIMVOUCHER_TERMINATED);
                break;
            case Constant.DEAL_PAID:
                // 处理打款
                claimVoucher.setStatus(Constant.CLAIMVOUCHER_PAID);
                claimVoucher.setNextDealSn(null);
                dealRecord.setDealResult(Constant.CLAIMVOUCHER_PAID);
                break;
        }
        claimVoucherMapper.updateByPrimaryKey(claimVoucher);
        dealRecordMapper.insert(dealRecord);
    }
}
