/*
 * Created by 动脑科技-Tim on 17-6-27 下午9:10
 * Copyright (c) 2017. All rights reserved
 *
 * Last modified 17-6-27 下午9:10
 */

package com.dn.tim.timkotlincomiler

import android.Manifest
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.Need
import com.example.OnDeined
import com.example.OnNeverAsk
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() ,AnkoLogger{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bt1.setOnClickListener {
            MainActivityPermission.showCamera(this)
        }
        bt2.setOnClickListener {
            MainActivityPermission.showLocation(this)
        }
    }

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
        MainActivityPermission.onRequestPermissionsResult(this, requestCode,grantResults)
    }


}
