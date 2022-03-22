package com.yyh.crm.workbench.service.impl;

import com.yyh.crm.workbench.entity.ActivityRemark;
import com.yyh.crm.workbench.mapper.ActivityRemarkMapper;
import com.yyh.crm.workbench.service.ActivityRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yyh
 * @date 2022-03-22 18:41
 */
@Service("ActivityRemarkService")
public class ActivityRemarkServiceImpl implements ActivityRemarkService {

    private ActivityRemarkMapper activityRemarkMapper;

    @Autowired
    public ActivityRemarkServiceImpl(ActivityRemarkMapper activityRemarkMapper){
        this.activityRemarkMapper = activityRemarkMapper;
    }

    @Override
    public List<ActivityRemark> queryActivityRemarkForDetailByActivityId(String activityId) {
        return activityRemarkMapper.selectActivityRemarkForDetailByActivityId(activityId);
    }
}
