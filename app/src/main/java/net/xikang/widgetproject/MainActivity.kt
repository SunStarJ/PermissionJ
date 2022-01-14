package net.xikang.widgetproject

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.github.SunStarJ.aspectj_permission.aspectj.PermissionChecker
import com.github.SunStarJ.aspectj_permission.aspectj.PermissionDine

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.test).setOnClickListener {

        }
    }

    @PermissionChecker([Manifest.permission.WRITE_EXTERNAL_STORAGE])//权限数组
    fun openCamera() {
        //同意请求
        //业务代码
    }

    @PermissionDine
    fun permissionDine() {
        //请求失败
        Log.e("getPermission", "getPermission: error")
    }


}