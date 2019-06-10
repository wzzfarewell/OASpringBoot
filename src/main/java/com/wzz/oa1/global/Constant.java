package com.wzz.oa1.global;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: wzzap
 * @Description:
 * @Date: 2019/6/6
 **/
public class Constant {
    public static final String CURRENT_USER = "current_user";
    public static final String INIT_PASSWORD = "000000";

    // 职务
    public static final String POST_STAFF = "员工";
    public static final String POST_FM = "部门经理";
    public static final String POST_GM = "总经理";
    public static final String POST_CASHIER = "财务";

    public static List<String> getPosts(){
        List<String> posts = new ArrayList<>();
        posts.add(POST_CASHIER);
        posts.add(POST_FM);
        posts.add(POST_GM);
        posts.add(POST_STAFF);
        return posts;
    }
}
