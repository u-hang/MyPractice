package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author uhang
 * @since 2021-09-06
 */
@ApiModel(description= "用户数据对象")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SysUser extends Model<SysUser> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id", required = true)
    @TableId(value = "id", type = IdType.ID_WORKER)
    private String id;

    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    private String password;

    @ApiModelProperty(value = "角色id", required = false)
    private String roleId;

    @ApiModelProperty(value = "状态 A  ACTIVE  A 在用 PAUSE   P  停用", required = false)
    private String state;

    @ApiModelProperty(value = "手机", required = false)
    private String phone;

    @ApiModelProperty(value = "邮箱", required = false)
    private String email;

    @ApiModelProperty(value = "创建时间", required = false,hidden = true)
    private Date createDate;

    @ApiModelProperty(value = "修改时间", required = false,hidden = true)
    private Date updateDate;


}
