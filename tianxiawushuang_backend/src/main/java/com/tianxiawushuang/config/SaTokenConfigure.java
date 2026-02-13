//package com.tianxiawushuang.config;
//
//import cn.dev33.satoken.interceptor.SaInterceptor;
//import cn.dev33.satoken.router.SaRouter;
//import cn.dev33.satoken.stp.StpUtil;
//import lombok.extern.slf4j.Slf4j;
//import cn.dev33.satoken.context.SaHolder;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Slf4j
//@Configuration
//public class SaTokenConfigure implements WebMvcConfigurer {
//    // 注册 Sa-Token 拦截器，打开注解式鉴权功能
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 注册 Sa-Token 拦截器，定义详细认证规则
//        registry.addInterceptor(new SaInterceptor(handler -> {
//            // 跳过 OPTIONS 请求
//            if (SaHolder.getRequest().getMethod().equals("OPTIONS")) {
//                log.info("Sa-Token收到OPTIONS请求，直接return");
//                return;
//            }
//
//            // 1. 登录校验：所有接口都需要登录，除了已添加@SaIgnore的接口
//            SaRouter.match("/**").check(r -> StpUtil.checkLogin());
//
//            // 2. 角色校验
//            // 用户端接口：需要 normal_user 角色
//            SaRouter.match("/user/**", r -> StpUtil.checkRole("normal_user"));
//            // 管理端接口：需要 normal_admin 或 super_admin 角色
//            SaRouter.match("/admin/**", r -> StpUtil.checkRoleOr("normal_admin", "super_admin"));
//
//            // 3. 权限校验
//            // 公共接口权限
//            SaRouter.match("/file/**", r -> StpUtil.checkPermission("file"));
//            SaRouter.match("/group/list", r -> StpUtil.checkPermission("group:read"));
//            SaRouter.match("/group/{id}", r -> StpUtil.checkPermission("group:read"));
//            SaRouter.match("/group", r -> StpUtil.checkPermission("group:write"));
//            SaRouter.match("/team", r -> StpUtil.checkPermission("team:read"));
//            SaRouter.match("/team", r -> StpUtil.checkPermission("team:write"));
//
//            // 用户端接口权限
//            SaRouter.match("/user/**", r -> StpUtil.checkPermission("user"));
//
//            // 管理端接口权限
//            SaRouter.match("/admin/user/**", r -> StpUtil.checkPermission("admin:user"));
//            SaRouter.match("/admin/processes/**", r -> StpUtil.checkPermission("admin:process"));
//            SaRouter.match("/admin/user-process/**", r -> StpUtil.checkPermission("admin:user-process"));
//            SaRouter.match("/admin/appointments/**", r -> StpUtil.checkPermission("admin:appointment"));
//            SaRouter.match("/admin/user-appointments/**", r -> StpUtil.checkPermission("admin:user-appointment"));
//            SaRouter.match("/admin/process-comment/**", r -> StpUtil.checkPermission("admin:process-comment"));
//
//        })).addPathPatterns("/**");
//    }
//}
