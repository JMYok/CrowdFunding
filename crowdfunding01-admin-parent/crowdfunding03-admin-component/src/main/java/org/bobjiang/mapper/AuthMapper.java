package org.bobjiang.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.bobjiang.entity.Auth;
import org.bobjiang.entity.AuthExample;

public interface AuthMapper {
    int countByExample(AuthExample example);

    int deleteByExample(AuthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Auth record);

    int insertSelective(Auth record);

    List<Auth> selectByExample(AuthExample example);

    Auth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Auth record, @Param("example") AuthExample example);

    int updateByExample(@Param("record") Auth record, @Param("example") AuthExample example);

    int updateByPrimaryKeySelective(Auth record);

    int updateByPrimaryKey(Auth record);

    /**
     * 根据Roleid查找对应的权限
     * @param roleId
     * @return List<Integer>
     */
    List<Integer> selectAuthByRoleId(Integer roleId);

    /**
     * 创建新的角色权限关系
     * @param
     */
    void insertNewRelationship(@Param("roleId")Integer roleId,@Param("authIdList")List<Integer> authIdList);

    /**
     * 删除旧的权限角色关系
     * @param roleId
     */
    void deleteOldRelationshipByRoleId(Integer roleId);
}