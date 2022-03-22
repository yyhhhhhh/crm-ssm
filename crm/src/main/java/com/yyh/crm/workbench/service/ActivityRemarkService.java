package com.yyh.crm.workbench.service;

import com.yyh.crm.workbench.entity.ActivityRemark;

import java.util.List;

/**
 * @author yyh
 * @date 2022-03-22 18:39
 */
public interface ActivityRemarkService {

    List<ActivityRemark> queryActivityRemarkForDetailByActivityId(String activityId);
}
