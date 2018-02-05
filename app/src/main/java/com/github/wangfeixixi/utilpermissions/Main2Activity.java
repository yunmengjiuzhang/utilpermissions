package com.github.wangfeixixi.utilpermissions;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.Rationale;

import java.util.List;

import wangfei.permision.DefaultRationale;
import wangfei.permision.PermissionSetting;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
    private Rationale mRationale;
    private PermissionSetting mSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        findViewById(R.id.asdfasfasfa).setOnClickListener(this);
        mRationale = new DefaultRationale();
        mSetting = new PermissionSetting(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.asdfasfasfa:

                // 版本判断。当手机系统大于 23 时，才有必要去判断权限是否获取
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermission(Permission.READ_CALENDAR,
                            Permission.CAMERA,
                            Permission.READ_CONTACTS,
                            Permission.ACCESS_COARSE_LOCATION,
                            Permission.RECORD_AUDIO,
                            Permission.READ_PHONE_STATE,
                            Permission.BODY_SENSORS,
                            Permission.READ_EXTERNAL_STORAGE);

                }
                break;
        }
    }

    private void requestPermission(String... permissions) {
        AndPermission.with(this)
                .permission(permissions)
                .rationale(mRationale)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        Toast.makeText(Main2Activity.this, "成功", Toast.LENGTH_SHORT).show();
                    }
                })
                .onDenied(new Action() {
                    @Override
                    public void onAction(@NonNull List<String> permissions) {
                        Toast.makeText(Main2Activity.this, "失败", Toast.LENGTH_SHORT).show();
                        if (AndPermission.hasAlwaysDeniedPermission(Main2Activity.this, permissions)) {
                            mSetting.showSetting(permissions);
                        }
                    }
                })
                .start();
    }
}
