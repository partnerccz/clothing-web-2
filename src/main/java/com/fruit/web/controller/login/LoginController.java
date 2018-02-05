package com.fruit.web.controller.login;

import com.fruit.web.base.BaseController;
import com.fruit.web.model.BusinessUser;
import com.fruit.web.util.Constant;
import com.fruit.web.util.HttpUtils;
import com.fruit.web.util.VerifyCodeUtils;
import com.jfinal.aop.Before;
import com.jfinal.ext2.kit.RandomKit;
import com.jfinal.kit.HashKit;
import com.jfinal.plugin.activerecord.tx.Tx;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class LoginController extends BaseController {

    private Logger log = Logger.getLogger(getClass());

    private static int counter = 0;

    /**
     * 登录用的session的key
     */
    private final static String LOGIN_VERIFY_CODE = "login_verify_code";

    /**
     * 登录操作
     */
    @Before(Tx.class)
    public void auth() {
//        Object uid = getSessionAttr(Constant.SESSION_UID);
//        if (uid != null) {
//            renderJson(new DataResult<>(DataResult.CODE_SUCCESS, "登录成功"));
//            //return;
//        }
        String phone = getPara("phone");
        String password = StringUtils.isNotBlank(getPara("password")) ? HashKit.md5(getPara("password")) : getPara("password");

        if (StringUtils.isNotBlank(phone) && StringUtils.isNotBlank(password)) {
            BusinessUser user = BusinessUser.dao.getUser(phone, password);
            if (user != null) {
                // 保存ip
                HttpServletRequest request = getRequest();

                String ip = HttpUtils.getRequestIp(request);
                setSessionAttr(Constant.SESSION_IP, ip);

                // 保存uId
                setSessionAttr(Constant.SESSION_UID, user.getId());

                // 保存登录序列号
                String sequence = RandomKit.randomMD5Str();
                setSessionAttr(Constant.SESSION_SEQUENCE, sequence);

                //保存token
                String token = RandomKit.randomMD5Str();
                setSessionAttr(Constant.SESSION_TOKEN, token);

                user.setIp(ip);
                user.setSequence(sequence);
                user.setUpdateTime(new Date());
                user.setLastLoginTime(new Date());
                user.update();

                log.info("----------session获取jsessionid为" + getSession().getId() + "----------");
                renderText(token);
            } else {
                renderErrorText("用户名或密码有误");
                return;
            }
        } else {
            renderErrorText("请填写用户名、密码");
            return;
        }
    }

    /**
     * 注册操作
     */
    public void register() {
        try {
            String password = getPara("password");
            String phone = getPara("phone");
            BusinessUser user = new BusinessUser();
            user.setPhone(phone);
            user.setPass(HashKit.md5(password));
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            user.save();
            renderNull();
        } catch (Exception e) {
            e.printStackTrace();
            renderErrorText("系统异常,请稍后再试");
        }

    }

    /**
     * 退出登录操作
     */
    public void logout() {
        Object uid = getSessionAttr(Constant.SESSION_UID);
        if (null != uid) {
            log.info("登出系统：uid=" + uid.toString());
        }
        getSession().invalidate();
        renderNull();
    }

    /**
     * 验证码校验
     */
    public void validationVerifyCode() {
        String verifyCode = getPara("verifyCode");
        String serviceVerifyCode = getVerifyCode();
        if (verifyCode != null && serviceVerifyCode != null && verifyCode.toUpperCase().equals(serviceVerifyCode)) {

        } else {
            renderErrorText("验证码错误!");
        }

    }

    /**
     * 生成验证码
     */
    public void createVerifyCode() {
        try {
            //文件夹
            String folder = "/verify";
            //文件名
            String dirName = System.currentTimeMillis() + ++counter + ".jpg";
            //文件路径
            String realPath = getRequest().getSession().getServletContext().getRealPath(folder);

            File dir = new File(realPath);
            String verify = VerifyCodeUtils.generateVerifyCode(4);
            getSession().setAttribute(LOGIN_VERIFY_CODE, verify);

            File file = new File(dir, dirName);
            VerifyCodeUtils.outputImage(200, 80, file, verify);
            // /verify/imgName.jsp
            renderText(folder + File.separator + dirName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取来验证码
     *
     * @return
     */
    private String getVerifyCode() {
        return getSessionAttr(LOGIN_VERIFY_CODE);
    }


}
