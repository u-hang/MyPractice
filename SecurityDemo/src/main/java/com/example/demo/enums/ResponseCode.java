package com.example.demo.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 错误码
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT) //直接返回枚举类型要加上这个注解,不然只返回名字
//set @@GLOBAL.sql_mode='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO, NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
//set @@SESSION.sql_mode='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO, NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
public enum ResponseCode {
    // todo 完善错误码
    SUCCESS(1, "SUCCESS"),
    CHECK_SUCCESS(1,"审核成功"),
    ERROR(0, "ERROR"),
    NEED_LOGIN(10, "NEED_LOGIN"),
    ILLEGAL_ARGUMENT(3, "ILLEGAL_ARGUMENT"),
    // head
    INVALID_BODY(4, "invalid post body"),
    // 认证相关
    INVALID_USER(401, "用户名或密码错误"),
    //权限
    SHRIO_AUTHEN_ERROR(403, "权限认证失败"),
    SHRIO_AUTHEN_NULL(404, "无权限"),
    // 表操作
    FIELD_ERROR(1001, "表字段错误"),
    INSERT_ERROR(1002, "插入错误"),
    UPDATE_ERROR(1003, "更新错误"),
    DELETED_ERROR(1004, "删除错误"),
    GET_ERROR(1005, "查找错误"),
    // 用户
    INVALID_USER_LOGIN_NAME(4001, "用户名不能为空！"),
    INVALID_USER_PASSWORD(4002, "密码不能为空！"),
    LEVEL_USER(4011, "用户级别不能为空！"),
    USER_EXISTS(4003, "用户已存在！"),
    USER_NOT_EXISTS(4004, "用户不存在！"),
    USER_SIGNOUT(4010, "已退出登录！"),
    //验证码
    USER_NOT_CODE(4005, "验证码为空！"),
    USER_ERROR_CODE(4006, "验证码错误！"),
    USER_EXPIRE_CODE(4007, "请重新获取验证码！"),
    // 角色
    ROLE_EXISTS(5001, "角色已存在！"),
    //用户保存
    SAVE_USER_SUCCESS(4008, "保存成功！"),
    SAVE_USER_ERROR(4009, "保存失败！"),
    //修改
    UPDATE_USER_SUCCESS(4012, "修改成功！"),
    UPDATE_USER_ERROR(4013, "修改失败！"),
    //删除
    DELECT_SUCCESS(4020, "删除成功！"),
    DELECT_ERROR(4021, "删除失败！"),

    //密码修改
    UPDATE_OLDPWD_ERROR(4014, "旧密码输入错误！"),
    UPDATE_USER_PWD_ERROR(4015, "密码修改失败！"),
    UPDATE_USER_PWD_SUCCESS(4016, "密码修改成功！"),

    //权限
    SAVE_SUCCESS(4010, "保存成功！"),
    SAVE_ERROR(4011, "名称已存在"),
    SAVE_ROLE_ERROR(4012, "角色已有权限"),
    NULL_NAME(4013, "名称不能为空！"),
    //附件名称已存在
    ONE_NAME(4051, "附件名称已存在"),
    //id或名称重复
    SAVE_CITY_ERROR(4052, "ID或名称已存在"),
    FILE_UPLOAD_ERROR(4060, "上传失败"),
    FILE_UPLOAD_SUCCESS(4062, "上传成功"),
    FILE_DOWNLOAD_ERROR(4063, "下载失败"),
    FILE_DOWNLOAD_SUCCESS(4065, "下载成功"),
    FILE_NAME_ERROR(4070, "附件名称重复"),
    SAVE_TO_ERROR(4072, "上传成功,保存失败"),
    SAVE_ODR_ERROR(4088, "请新增订单审核流程"),
    SAVE_ZIP_ERROR(4089, "解压文件出错")
    ;
    private int code;

    private String desc;

    private Object data;

    ResponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
