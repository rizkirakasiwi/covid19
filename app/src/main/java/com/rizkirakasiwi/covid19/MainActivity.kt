package com.rizkirakasiwi.covid19

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {


    private val TAG = "MainActivity"
    private val locationRequestCode = 1
    private var snackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestForPermission()

    }

    private fun requestForPermission(){
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),locationRequestCode)
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == locationRequestCode){
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED){
                AlertDialog.Builder(this)
                    .setTitle(getString(R.string.information))
                    .setMessage(getString(R.string.app_need_permissionLocation))
                    .setPositiveButton("Give permission"){ _, _ ->
                        requestForPermission()
                    }
                    .setNegativeButton("Close the app"){ _, _ ->
                        finish()
                        exitProcess(0)
                    }
                    .create()
                    .show()
            }
        }
    }



}
