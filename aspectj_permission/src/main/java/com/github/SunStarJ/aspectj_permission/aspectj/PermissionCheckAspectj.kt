package com.github.SunStarJ.aspectj_permission.aspectj

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut

/**
 *
 * @ProjectName:    My Application
 * @Package:        com.github.SunStarJ.aspectj_permission.aspectj
 * @ClassName:      PermissionCheckAspectj
 * @Description:    类作用描述
 * @Author:         孙浩
 * @CreateDate:     2022/1/11 4:33 下午
 * @UpdateUser:
 * @UpdateDate:     2022/1/11 4:33 下午
 * @UpdateRemark:
 * @Version:        1.0
 */
@Aspect
class PermissionCheckAspectj {

    var permissionCheckFragment: PermissionCheckFragment? = null

    @Pointcut("execution(@PermissionChecker * *(..)) && @annotation(ann)")
    private fun checkPermission(ann: PermissionChecker?) {
    }

    @Around("checkPermission(permissioncheck)")
    @Throws(Throwable::class)
    open fun check(joinPoint: ProceedingJoinPoint, permissioncheck: PermissionChecker?) {
        var ctx: Context? = null
        var checkString: Array<String> = arrayOf()
        var fragmentManager: FragmentManager? = null
        var mFragment: PermissionCheckFragment? = null
        permissioncheck?.run {
            checkString = this.checkString
            kotlin.runCatching {
                if (joinPoint.`this` is Fragment) {
                    fragmentManager = (joinPoint.`this` as Fragment).childFragmentManager
                } else {
                    fragmentManager = (joinPoint.`this` as FragmentActivity).supportFragmentManager
                }
            }
        }
        fragmentManager?.run {
            if (permissionCheckFragment == null) {
                permissionCheckFragment = PermissionCheckFragment { result, permission ->
                    var allGrant = true
                    result.forEach {
                        if (PackageManager.PERMISSION_GRANTED != it) {
                            allGrant = false
                        }
                    }
                    if (allGrant) {
                        joinPoint.proceed()
                    } else {
                        AspectJUtil.checkPermissionAspect(
                            joinPoint.`this`,
                            PermissionDine::class.java,
                            RequestCode
                        )
                    }
                }
                beginTransaction().add(permissionCheckFragment!!, "PermissionCheckAspectj")
                    .commitNow()
            }


        }
        if (checkString.isNotEmpty()) {
            permissionCheckFragment?.requestPermission(checkString)
        }
    }

    companion object {
        @JvmStatic
        fun aspectOf(): PermissionCheckAspectj {
            return PermissionCheckAspectj()
        }
    }


}