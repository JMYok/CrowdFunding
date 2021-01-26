package org.bobjiang.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.bobjiang.entity.Admin;
import org.bobjiang.entity.AdminExample;

public interface AdminMapper {
    int countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer id);

    List<Admin> selectAdminByKeyWord(String keyword);

    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    /**
     * 删除admin所有已分配的角色
     * @param adminId
     */
    void clearOldRelationship(Integer adminId);

    /**
     * 给指定admin分配角色
     * @param adminId
     * @param roleIdList
     */
    void saveAdminRoleRelationship(@Param("adminId") Integer adminId, @Param("roleIdList") List<Integer> roleIdList);

}