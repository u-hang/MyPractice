package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author uhang
 * @since 2021-09-06
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

}
