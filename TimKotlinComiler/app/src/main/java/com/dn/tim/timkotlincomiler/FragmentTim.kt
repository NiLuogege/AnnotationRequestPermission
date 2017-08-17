/*
 * Created by 动脑科技-Tim on 17-6-27 下午10:27
 * Copyright (c) 2017. All rights reserved
 *
 * Last modified 17-6-27 下午10:27
 */

package com.dn.tim.timkotlincomiler

import android.Manifest
import android.app.Fragment
import com.example.Need
import com.example.OnDeined
import com.example.OnNeverAsk
import org.jetbrains.anko.toast


class FragmentTim : Fragment(){
    @Need(arrayOf(Manifest.permission.CAMERA))
    fun showCamera() {
        toast(" Camera 权限申请成功")
    }

    @Need(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION))
    fun showLocation() {
        toast(" Location 权限申请成功")
    }

    @OnNeverAsk(arrayOf(Manifest.permission.CAMERA))
    fun OnNeverAskCamera() {
        toast(" Carmera 权限不再访问")
    }
    @OnNeverAsk(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION))
    fun onNeverAskLocation() {
        toast(" Location 权限不再访问")
    }

    @OnDeined(arrayOf(Manifest.permission.CAMERA))
    fun onDeinedCarmera() {
        toast(" Carmera 权限拒绝")
    }

    @OnDeined(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION))
    fun onDeinedLocation() {
        toast(" Location 权限拒绝")
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}