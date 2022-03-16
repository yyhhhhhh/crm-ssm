package com.yyh.crm.workbench.service.impl;

import com.yyh.crm.workbench.entity.Activity;
import com.yyh.crm.workbench.mapper.ActivityMapper;
import com.yyh.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public List<Activity> queryActivityByConditionForPage(Map<String, Object> map) {
        return activityMapper.selectActivityByConditionForPage(map);
    }

    @Override
    public int queryCountOfActivityByCondition(Map<String, Object> map) {
        return activityMapper.selectCountOfActivityByCondition(map);
    }

    @Override
    public int deleteActivityByIds(String[] ids) {
        return activityMapper.deleteActivityByIds(ids);
    }
}
