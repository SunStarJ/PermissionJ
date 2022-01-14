package com.github.SunStarJ.aspectj_permission.aspectj

import androidx.fragment.app.Fragment

/**
 *
 * @ProjectName:    My Application
 * @Package:        com.github.SunStarJ.aspectj_permission.aspectj
 * @ClassName:      PermissionCheckFragment
 * @Description:    类作用描述
 * @Author:         孙浩
 * @CreateDate:     2022/1/11 5:10 下午
 * @UpdateUser:
 * @UpdateDate:     2022/1/11 5:10 下午
 * @UpdateRemark:
 * @Version:        1.0
 */
class PermissionCheckFragment(var callBack: (IntArray, Array<out String>) -> Unit) : Fragment() {


    fun requestPermission(permissionArray: Array<String>) {
        requestPermissions(permissionArray, RequestCode)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (RequestCode == requestCode) {
            callBack.invoke(grantResults, permissions)
        }
    }

}