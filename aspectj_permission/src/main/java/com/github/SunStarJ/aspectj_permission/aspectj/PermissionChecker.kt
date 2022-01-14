package com.github.SunStarJ.aspectj_permission.aspectj

/**
 *
 * @ProjectName:    My Application
 * @Package:        com.github.SunStarJ.aspectj_permission.aspectj
 * @ClassName:      PermissionChecker
 * @Description:    类作用描述
 * @Author:         孙浩
 * @CreateDate:     2022/1/11 4:19 下午
 * @UpdateUser:
 * @UpdateDate:     2022/1/11 4:19 下午
 * @UpdateRemark:
 * @Version:        1.0
 */
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class PermissionChecker(val checkString: Array<String>)

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class PermissionDine(val requestCode: Int = RequestCode)

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class PermissionCancel(val requestCode: Int = RequestCode)