package com.baomidou.mybatisplus.samples.generator.sys.mapper;

import com.baomidou.mybatisplus.samples.generator.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2018-12-19
 */
public interface UserMapper extends BaseMapper<User> {

     List<User> selectUpdatedData(@Param("name") String name);

}
