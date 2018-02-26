package org.xxz.myblog.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;
import org.xxz.myblog.common.Result;
import org.xxz.myblog.common.constant.UserConstants;
import org.xxz.myblog.entity.User;
import org.xxz.myblog.entity.UserLog;
import org.xxz.myblog.exception.UserException;
import org.xxz.myblog.helper.UserConvertUtil;
import org.xxz.myblog.mapper.UserLogMapper;
import org.xxz.myblog.mapper.UserMapper;
import org.xxz.myblog.model.dto.UserDTO;
import org.xxz.myblog.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static org.xxz.myblog.enums.UserExceptionEnum.NOT_FOUND_USER;

/**
 * @author tt
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    public static final String salt = "{2038alkdjfwoieurnvytnlklsjdgalkjdfah#}";

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserLogMapper userLogMapper;

    @Override
    public Result<?> userLogin(UserDTO userDTO, HttpServletRequest request) {
        if(userDTO == null) {
            log.error("userDo is empty");
            return Result.fail("用户名密码不能为空");
        }
        User user = userMapper.findByUsername(userDTO.getUsername());
        if(user == null) {
            log.error("user is empty");
            return Result.fail("用户名或密码不正确");
        }
        UserLog userLog = userLogMapper.findByUserId(user.getId());

        String pwd = DigestUtils.md5DigestAsHex((userDTO.getPassword()+salt).getBytes());
        
        if(!user.getPassword().equals(pwd)) {
            log.error("user password error");
            if(userLog == null) {
                userLogMapper.save(new UserLog(0L, user.getId(), new Date(), 1, 1));
            } else {
                userLog.setLoginTime(new Date());
                userLog.setLoginErrorCount(userLog.getLoginErrorCount() + 1);
                userLog.setLoginStat(userLog.getLoginStat() + 1);
                userLogMapper.update(userLog);
            }
            return Result.fail("用户名或密码不正确");
        }

        if(userLog != null) {
            if(System.currentTimeMillis() - userLog.getLoginTime().getTime() < 1000 * 60 * 30 && userLog.getLoginErrorCount() >= 3) {
                return Result.fail("该用户禁止登录，请联系管理员");
            }
            userLog.setLoginStat(userLog.getLoginStat() + 1);
            userLog.setLoginTime(new Date());
            userLog.setLoginErrorCount(0);
            userLogMapper.update(userLog);
        } else {
            userLogMapper.save(new UserLog(0L, user.getId(), new Date(), 1, 0));
        }

        request.getSession().setAttribute(UserConstants.SESSION_USER, user);
        return Result.success("登录成功");
    }

    @Override
    public Result<?> logout(HttpServletRequest request) {
        request.getSession().removeAttribute(UserConstants.SESSION_USER);
        return Result.success("退出成功");
    }

    @Override
    public Result<?> updateUser(UserDTO userDTO) {
        if(userDTO == null) {
            throw new IllegalArgumentException("userDTO is null");
        }
        User user = userMapper.findByUsername(userDTO.getUsername());
        if (user == null) {
            throw new UserException(NOT_FOUND_USER);
        }
        String pwd = DigestUtils.md5DigestAsHex((userDTO.getPassword()+salt).getBytes());
        user.setPassword(pwd);
        int i = userMapper.updateByUsername(user);
        return i > 0 ? Result.success("更新成功") : Result.fail("更新失败");
    }

    @Override
    public Result<?> saveUser(UserDTO userDTO) {
        Assert.notNull(userDTO, "userDTO must not be null");

        String pwd = DigestUtils.md5DigestAsHex((userDTO.getPassword() + salt).getBytes());
        userDTO.setPassword(pwd);
        User user = UserConvertUtil.userDTO2User(userDTO, null);
        user.setCreateTime(System.currentTimeMillis());
        int row = userMapper.save(user);
        return row > 0 ? Result.success("添加成功") : Result.fail("添加失败");
    }
}
