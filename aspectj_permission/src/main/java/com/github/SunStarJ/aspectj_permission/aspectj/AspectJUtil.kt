package com.github.SunStarJ.aspectj_permission.aspectj

/**
 *
 * @ProjectName:    My Application
 * @Package:        com.github.SunStarJ.aspectj_permission.aspectj
 * @ClassName:      AspectJUtil
 * @Description:    类作用描述
 * @Author:         孙浩
 * @CreateDate:     2022/1/12 10:17 上午
 * @UpdateUser:
 * @UpdateDate:     2022/1/12 10:17 上午
 * @UpdateRemark:
 * @Version:        1.0
 */
object AspectJUtil {

    fun <T : Annotation> checkPermissionAspect(
        o: Any,
        annotationClazz: Class<T>,
        requestCode: Int
    ) {
        var clazz = o.javaClass
        var methods = clazz.declaredMethods
        methods.forEach {
            it.isAccessible = true
            // 判断方法 是否有被 annotationClass注解的方法
            val annotationPresent = it.isAnnotationPresent(annotationClazz)
            if (annotationPresent) {
                val annotation = it.getAnnotation(annotationClazz)
                val mRequestCode = if (annotationClazz == PermissionCancel::class.java) {
                    (annotation as PermissionCancel).requestCode
                } else if (annotationClazz == PermissionDine::class.java) {
                    (annotation as PermissionDine).requestCode
                } else {
                    -1
                }
                if (requestCode == mRequestCode) {
                    it.invoke(o)
                }
            }
        }
    }

}