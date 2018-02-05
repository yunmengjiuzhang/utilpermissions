
package wangfei.permision;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;

import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.SettingService;

import java.util.List;

public final class PermissionSetting {


    private final Context mContext;

    public PermissionSetting(Context context) {
        this.mContext = context;
    }

    public void showSetting(final List<String> permissions) {
        List<String> permissionNames = Permission.transformText(mContext, permissions);

        final SettingService settingService = AndPermission.permissionSetting(mContext);
        new AlertDialog.Builder(mContext)
                .setCancelable(false)
                .setTitle("权限提醒！")
                .setMessage("app需要一下权限才能运行:\n" + TextUtils.join("\n", permissionNames))
                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        settingService.execute();
                    }
                })
                .setNegativeButton("拒绝", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        settingService.cancel();
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                })
                .show();
    }
}