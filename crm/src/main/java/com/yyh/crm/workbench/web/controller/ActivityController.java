package com.yyh.crm.workbench.web.controller;

import com.yyh.crm.commons.contants.Contants;
import com.yyh.crm.commons.entity.ReturnObject;
import com.yyh.crm.commons.utils.DateUtils;
import com.yyh.crm.commons.utils.HSSFUtils;
import com.yyh.crm.commons.utils.UUIDUtils;
import com.yyh.crm.settings.entity.User;
import com.yyh.crm.settings.service.UserService;
import com.yyh.crm.workbench.entity.Activity;
import com.yyh.crm.workbench.service.ActivityService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

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

    @RequestMapping(value = "/workbench/activity/saveCreateActivity.do",method = RequestMethod.POST)
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

    @RequestMapping(value = "/workbench/activity/queryActivityByConditionForPage.do",method = RequestMethod.POST)
    @ResponseBody
    public Object queryActivityByConditionForPage(String name,String owner,String startDate,String endDate,int pageNo,int pageSize){
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("owner",owner);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("beginNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        List<Activity> list = activityService.queryActivityByConditionForPage(map);
        int totalRows = activityService.queryCountOfActivityByCondition(map);
        //生成响应信息
        Map<String,Object> reusltMap =  new HashMap<>();
        reusltMap.put("activityList",list);
        reusltMap.put("totalRows",totalRows);
        return reusltMap;
    }

    @RequestMapping(value = "/workbench/activity/deleteActivityIds.do",method = RequestMethod.POST)
    @ResponseBody
    public Object deleteActivityIds(String[] id){
        ReturnObject returnObject = new ReturnObject();
        try{
            int result = activityService.deleteActivityByIds(id);
            if(result > 0) {
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

    @RequestMapping(value = "/workbench/activity/queryActivityById.do",method = RequestMethod.POST)
    @ResponseBody
    public Object queryActivityById(String id){
        Activity activity = activityService.queryActivityById(id);
        return activity;
    }

    @RequestMapping(value = "/workbench/activity/updateActivityById.do",method = RequestMethod.POST)
    @ResponseBody
    public Object updateActivityById(Activity activity,HttpSession session){
        activity.setEditTime(DateUtils.formatDateTime(new Date()));
        User user = (User) session.getAttribute(Contants.SESSION_USER);
        activity.setEditBy(user.getId());
        ReturnObject returnObject = new ReturnObject();
        try{
            int result = activityService.saveEditActivityById(activity);
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

    @RequestMapping(value = "/workbench/activity/exportAllActivities.do")
    public void exportAllActivities(HttpServletResponse response) throws Exception{
        List<Activity> list = activityService.queryAllActivities();
        HSSFWorkbook wb = HSSFUtils.exportActivitiesOrByIds(list);
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition","attachment;filename=activityList.xls");
        OutputStream out = response.getOutputStream();
        wb.write(out);
        out.flush();
        wb.close();
    }

    @RequestMapping(value = "/workbench/activity/exportActivitiesByIds.do")
    public void exportActivitiesByIds(String[] id,HttpServletResponse response)throws Exception{
        List<Activity> list = activityService.queryActivitiesByIds(id);
        HSSFWorkbook wb = HSSFUtils.exportActivitiesOrByIds(list);
        response.setContentType("application/octet-stream;charset=utf-8");
        response.addHeader("Content-Disposition","attachment;filename=activityList.xls");
        OutputStream out = response.getOutputStream();
        wb.write(out);
        out.flush();
        wb.close();
    }

    /*
    @RequestMapping(value = "/workbench/activity/fileUpload.do",method = RequestMethod.POST)
    @ResponseBody
    public Object fileUpload(String userName, MultipartFile myFile) throws Exception{
        System.out.println("userName="+userName);
        myFile.transferTo(new File("/Users/yyh/Documents/serverDir/abc.xls"));
        ReturnObject returnObject = new ReturnObject();
        returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
        returnObject.setMessage("上传成功");
        return returnObject;
    }*/

    /* 测试下载
    @RequestMapping(value = "/workbench/activity/fileDownload.do")
    public void fileDownload(HttpServletResponse response) throws Exception{
        response.setContentType("application/octet-stream;charset=utf-8");
        //直接激活文件下载窗口
        response.addHeader("Content-Disposition","attachment;filename=myStudentList.xls");
        OutputStream out = response.getOutputStream();//谁new谁关
        InputStream in = new FileInputStream("/Users/yyh/Documents/serverDir/studentList.xls");
        byte [] bytes = new byte[256];
        int count = 0;
        while((count = in.read(bytes)) != -1){
            out.write(bytes,0,count);
        }
        in.close();
        out.flush();
    }*/
}
