package com.yyh.crm.settings.web.controller;

import com.yyh.crm.commons.contants.Contants;
import com.yyh.crm.commons.entity.ReturnObject;
import com.yyh.crm.commons.utils.DateUtils;
import com.yyh.crm.settings.entity.User;
import com.yyh.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yyh
 * @date 2022-03-08 15:59
 */
@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    /**
    * url和controller方法处理完请求之后,响应信息返回的页面与资源目录保持一致
    */

    @RequestMapping("/settings/qx/user/toLogin.do")
    public String toLogin(){
        return "settings/qx/user/login";
    }

    //登录
    @RequestMapping(value = "/settings/qx/user/login.do",method = RequestMethod.POST)
    @ResponseBody
    public Object login(String loginAct, String loginPwd, String isRemPwd, HttpServletRequest request, HttpSession session, HttpServletResponse response){
        Map<String,Object> map = new HashMap<>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);
        User user = userService.queryUserByLoginActAndPwd(map);
        ReturnObject returnObject = new ReturnObject();
        if(user == null){ //登录失败
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("用户名或密码错误");
        }else{
            if(DateUtils.formatDateTime(new Date()).compareTo(user.getExpireTime())>0){ //登录失败,账号已过期
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("账号已过期");
            }else if(Contants.RETURN_OBJECT_CODE_FAIL.equals(user.getLockState())){ //登录失败,账户已被锁定
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("账户被锁定");
            }else if(!user.getAllowIps().contains(request.getRemoteAddr())){ //登录失败,IP受限
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("IP受限");
            }else{ //登录成功
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
                //把user对象保存到session中
                session.setAttribute(Contants.SESSION_USER,user);
                //如果需要记住密码,写cookie
                if("true".equals(isRemPwd)){
                    Cookie cookie1 = new Cookie("loginAct",user.getLoginAct());
                    Cookie cookie2 = new Cookie("loginPwd",user.getLoginPwd());
                    cookie1.setMaxAge(10*24*60*60);
                    cookie2.setMaxAge(10*24*60*60);
                    response.addCookie(cookie1);
                    response.addCookie(cookie2);
                }else{ //将没有过期的cookie删除
                    Cookie cookie1 = new Cookie("loginAct","1");
                    Cookie cookie2 = new Cookie("loginPwd","1");
                    cookie1.setMaxAge(0);
                    cookie2.setMaxAge(0);
                    response.addCookie(cookie1);
                    response.addCookie(cookie2);
                }
            }
        }
        return returnObject;
    }

    //安全退出
    @RequestMapping("/settings/qs/user/logout.do")
    public String logout(HttpServletResponse response,HttpSession session){
        //清空cookie
        Cookie cookie1 = new Cookie("loginAct","1");
        Cookie cookie2 = new Cookie("loginPwd","1");
        cookie1.setMaxAge(0);
        cookie2.setMaxAge(0);
        response.addCookie(cookie1);
        response.addCookie(cookie2);
        //销毁session
        session.invalidate();
        //跳转首页
        return "redirect:/"; //借助springmvc来重定向 response.sendRedirect("/crm"+"/");
    }
}
