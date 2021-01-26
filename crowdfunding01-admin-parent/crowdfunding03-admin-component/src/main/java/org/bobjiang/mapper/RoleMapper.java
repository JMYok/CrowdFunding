package org.bobjiang.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.bobjiang.entity.Role;
import org.bobjiang.entity.RoleExample;

public interface RoleMapper {
    int countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    /**
     * 根据关键字查询Role
     * @param keyword
     * @return List<Role>
     */
    List<Role> selectRoleByKeyWord(String keyword);

    /**
     * 查询对应adminId的未被分配的Role
     * @param adminId
     * @return List<Role>
     */
    List<Role> queryUnAssignedRoleList(Integer adminId);

    /**
     * 查询对应adminId的已被分配的Role
     * @param adminId
     * @return List<Role>
     */
    List<Role> queryAssignedRoleList(Integer adminId);

}