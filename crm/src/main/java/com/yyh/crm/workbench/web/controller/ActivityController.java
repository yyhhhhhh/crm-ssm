package com.yyh.crm.workbench.web.controller;

import com.yyh.crm.commons.contants.Contants;
import com.yyh.crm.commons.entity.ReturnObject;
import com.yyh.crm.commons.utils.DateUtils;
import com.yyh.crm.commons.utils.UUIDUtils;
import com.yyh.crm.settings.entity.User;
import com.yyh.crm.settings.service.UserService;
import com.yyh.crm.workbench.entity.Activity;
import com.yyh.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author yyh
 * @date 2022-03-10 13:53
 * 市场活动功能
 */
@Controller
public class ActivityController {

    private UserService userService;
    private ActivityService activityService;

    @Autowired
    public ActivityController(UserService userService,ActivityService activityService){
        this.userService = userService;
        this.activityService = activityService;
    }


    @RequestMapping ("/workbench/activity/index.do")
    public String index(HttpServletRequest request){
        List<User> list = userService.queryAllUsers();
        request.setAttribute(Contants.REQUEST_ALL_USERS,list);
        return "workbench/activity/index";
    }

    @RequestMapping("/workbench/activity/saveCreateActivity.do")
    @ResponseBody
    public Object saveCreateActivity(Activity activity, HttpSession session){
        User user = (User)session.getAttribute(Contants.SESSION_USER);
        activity.setId(UUIDUtils.getUUID());
        activity.setCreateTime(DateUtils.formatDateTime(new Date()));
        activity.setCreateBy(user.getId());
        ReturnObject returnObject = new ReturnObject();
        try {
            int result = activityService.saveCreateActivity(activity);
            if(result > 0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }else{
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage(Contants.FAIL_INFO);
            }
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage(Contants.FAIL_INFO);
        }
        return returnObject;
    }

}
