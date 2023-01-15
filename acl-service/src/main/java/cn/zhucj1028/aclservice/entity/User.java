package cn.zhucj1028.aclservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @description: 用户实体类
 * @author zhucj
 * @date 2023/1/11 20:19
 * @version 1.0
 */
@TableName(value ="acl_user")
@Data
public class User implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    @TableField(value = "username")
    @NotBlank(message = "用户名不能为空！")
    private String username;

    /**
     * 密码
     */
    @TableField(value = "password")
    @NotBlank(message = "密码不能为空！")
    private String password;

    /**
     * 用户头像
     */
    @TableField(value = "avatar")
    private String avatar="default.jpg";

    /**
     * 用户邮箱
     */
    @NotBlank(message = "邮箱不能为空！")
    @Email(message = "邮箱格式不正确")
    @TableField(value = "email")
    private String email;

    /**
     * 手机号码
     */
    @NotBlank(message = "手机号码不能为空！")
    @TableField(value = "phonenumber")
    private String phonenumber;

    /**
     * 最后登录时间
     */
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    @TableField(value = "login_date")
    private Date loginDate;

    /**
     * 创建日期
     */
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新日期
     */
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 帐号状态（0正常 1停用）
     */
    @TableField(value = "status")
    private String status;

    /**
     * 所属角色
     */
    @TableField(exist = false)
    private String roles;

    /**
     * 所有角色集合
     */
    @TableField(exist = false)
    private List<Role> sysRoleList;

    /**
     * 确认新密码
     */
    @TableField(exist = false)
    private String newPassword;

}
