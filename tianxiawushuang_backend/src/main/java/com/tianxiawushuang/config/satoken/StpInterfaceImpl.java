//package com.tianxiawushuang.config.satoken;
//
//import cn.dev33.satoken.SaManager;
//import cn.dev33.satoken.stp.StpInterface;
//import com.tianxiawushuang.mapper.PermissionMapper;
//import com.tianxiawushuang.mapper.RoleMapper;
//import com.tianxiawushuang.mapper.RolePermissionMapper;
//import com.tianxiawushuang.pojo.entity.Role;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.List;
//
//@Slf4j
//@Component
//public class StpInterfaceImpl implements StpInterface {
//
//    @Resource
//    private RolePermissionMapper rolePermissionMapper;
//
//    @Resource
//    private RoleMapper roleMapper;
//
//    // 返回一个账号所拥有的权限码集合
//    @Override
//    @SuppressWarnings("unchecked")
//    public List<String> getPermissionList(Object loginId, String loginType) {
//
//        // 1. 声明权限码集合
//        List<String> list = new ArrayList<>();
//        // 从数据库查询这个账号id拥有的角色列表，
//        Long userId = Long.valueOf(loginId.toString());
//        List<Long> roleList = roleMapper.selectRoleIdByUserId(userId);
//
//
//        // 2. 遍历角色列表，查询拥有的权限码
//        for (Long roleId : roleList) {
//            List<String> permissionList = (List<String>) SaManager.getSaTokenDao().getObject("satoken:role-find-permission:" + roleId);
//            if (permissionList == null) {
//                // 从数据库查询这个角色 id 所拥有的权限列表
//                permissionList = rolePermissionMapper.selectPermissionsByRoleId(roleId);
//                // 查好后，set 到缓存中
//                SaManager.getSaTokenDao().setObject("satoken:role-find-permission:" + roleId, permissionList, 60 * 60 * 24 * 30);
//            }
//            list.addAll(permissionList);
//        }
//        log.info("getPermissionList: {}", list);
//
//        // 3. 返回权限码集合
//        return list;
//    }
//
//    // 返回一个账号所拥有的角色标识集合
//    @Override
//    @SuppressWarnings("unchecked")
//    public List<String> getRoleList(Object loginId, String loginType) {
//        List<String> roleList = (List<String>) SaManager.getSaTokenDao().getObject("satoken:userId-find-role:" + loginId);
//        if (roleList == null) {
//            // 安全转换：先转String，再转Long
//            Long userId = Long.valueOf(loginId.toString());
//            // 从数据库查询这个账号id拥有的角色列表，
//            roleList = roleMapper.selectRoleNameByUserId(userId);
//            // 查好后，set 到缓存中
//            SaManager.getSaTokenDao().setObject("satoken:userId-find-role:" + loginId, roleList, 60 * 60 * 24 * 30);
//        }
//        log.info("getRoleList: {}", roleList);
//        return roleList;
//    }
//
//}
