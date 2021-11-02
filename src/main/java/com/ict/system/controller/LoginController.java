package com.ict.system.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import com.ict.system.constant.constant;
import com.ict.system.domain.LogInfo;
import com.ict.system.domain.SysUser;
import com.ict.system.service.LogInfoService;
import com.ict.system.service.SysUserService;
import com.ict.system.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * @Author: Lizbeth9421
 */
@Controller
public class LoginController {
    @Autowired
    private SysUserService service;

    @Autowired
    private LogInfoService infoService;

    @RequestMapping("/login")
    public String login(final SysUser user, final String code) {
        final HttpSession session = WebUtils.getHttpSession();
        final String currentCode = (String) session.getAttribute(constant.CODE_IN_SESSION);
        if (code.equalsIgnoreCase(currentCode)) {
            final SysUser current = this.service.checkLogin(user);
            if (current != null) {
                session.setAttribute(constant.USER_IN_SESSION, current);
                //录入登录日志信息
                final LogInfo info = new LogInfo();
                info.setLogintime(new Date());
                info.setLoginname(current.getRealname() + "-" + current.getLoginname());
                info.setLoginip(WebUtils.getHttpServletRequest().getRemoteAddr());
                this.infoService.insertSelective(info);
                return "index";
            } else {
                session.setAttribute("ERROR_MSG", "账号或密码错误！");
                return "login";
            }
        }
        session.setAttribute("ERROR_MSG", "验证码错误，请重新输入！");
        return "login";
    }


    @RequestMapping("/getCode")
    public void getCode(final HttpServletResponse resp) {
        //定义图像验证码的长、宽、验证码字符数、干扰线宽度
        final ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 100, 4, 4);
        //把验证码信息设置到session
        WebUtils.getHttpSession().setAttribute(constant.CODE_IN_SESSION, captcha.getCode());
        //将验证码输出到页面
        try {
            captcha.write(resp.getOutputStream());
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}
