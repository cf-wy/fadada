package com.example.demo.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class HomeController {
    @RequestMapping("/index")
    public String index(){
        return"/index";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, Map<String, Object> map,String username,String password ) throws Exception{
        System.out.println("HomeController.login()");
        Subject currentUser = SecurityUtils.getSubject();
        String msg = "";
        if (!currentUser.isAuthenticated()) {

            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            //token.setRememberMe(true);
            try {
                currentUser.login(token);
                return "/index";
            } catch (UnknownAccountException uae) {
                msg = "UnknownAccountException -- > 账号不存在：";
                //log.info("There is no user with username of " + token.getPrincipal());
            } catch (IncorrectCredentialsException ice) {
                msg = "IncorrectCredentialsException -- > 密码不正确：";
                //log.info("Password for account " + token.getPrincipal() + " was incorrect!");
            } catch (LockedAccountException lae) {
                msg = "kaptchaValidateFailed -- > 验证码错误";
                //log.info("The account for username " + token.getPrincipal() + " is locked.  " +
                        //"Please contact your administrator to unlock it.");
            }
            // ... catch more exceptions here (maybe custom ones specific to your application?
            catch (AuthenticationException ae) {
                //unexpected condition?  error?
                msg = "else >> "+ae;
            }
            map.put("msg", msg);
            return "/login";
        }
        // 登录失败从request中获取shiro处理的异常信息。
        // shiroLoginFailure:就是shiro异常类的全类名.
       /* String exception = (String) request.getAttribute("shiroLoginFailure");
        System.out.println("exception=" + exception);

        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("UnknownAccountException -- > 账号不存在：");
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                System.out.println("kaptchaValidateFailed -- > 验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "else >> "+exception;
                System.out.println("else -- >" + exception);
            }
        }*/

        // 此方法不处理登录成功,由shiro进行处理
        return "/index";
    }

    @RequestMapping("/403")
    public String unauthorizedRole(){
        System.out.println("------没有权限-------");
        return "403";
    }

}
