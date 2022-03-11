package com.yyh.crm.workbench.mapper;

import com.yyh.crm.workbench.entity.Activity;

public interface ActivityMapper {
    int deleteByPrimaryKey(String id);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);

    /**
     * 保存市场活动
     * @param activity
     * @return
     */
    int insertActivity(Activity activity);
}