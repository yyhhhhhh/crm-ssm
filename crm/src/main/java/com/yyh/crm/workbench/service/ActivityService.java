package com.yyh.crm.workbench.service;

import com.yyh.crm.workbench.entity.Activity;

import java.util.List;
import java.util.Map;

/**
 * @author yyh
 * @date 2022-03-10 20:41
 */
public interface ActivityService {

    int saveCreateActivity(Activity activity);

    List<Activity> queryActivityByConditionForPage(Map<String,Object> map);

    int queryCountOfActivityByCondition(Map<String,Object> map);

    int deleteActivityByIds(String[] ids);

    Activity queryActivityById(String id);

    int saveEditActivityById(Activity activity);

    List<Activity> queryAllActivities();

    List<Activity> queryActivitiesByIds(String[] ids);

    int saveCreateActivityByList(List<Activity> activityList);

    Activity queryActivityForDetailById(String id);
}
