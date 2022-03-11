package com.yyh.crm.workbench.service.impl;

import com.yyh.crm.workbench.entity.Activity;
import com.yyh.crm.workbench.mapper.ActivityMapper;
import com.yyh.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yyh
 * @date 2022-03-10 20:43
 */
@Service("activityService")
public class ActivityServiceImpl implements ActivityService {

    private ActivityMapper activityMapper;

    @Autowired
    public ActivityServiceImpl(ActivityMapper activityMapper){
        this.activityMapper = activityMapper;
    }

    @Override
    public int saveCreateActivity(Activity activity) {
        return activityMapper.insertActivity(activity);
    }
}
