package com.esell.yxfsdksimple;

import android.content.Context;
import android.content.Intent;

import com.esell.component_log.Log;
import com.esell.yxf.bean.AlarmOnOffItem4;
import com.esell.yxf.controller.IController;

import java.util.Calendar;
import java.util.List;

/**
 * 远程控制实现
 * @author NiuLei
 * @date 2021/5/10 15:51
 */
public class ControllerImp implements IController {
    @Override
    public void setVolume(Context context, int volume) {
        Log.i("volume : " + volume);
    }

    @Override
    public void setBrightness(Context context, int brightness) {
        Log.i("brightness : " + brightness);
    }

    @Override
    public void reboot(Context context) {
        Log.i("reboot : ");
    }

    @Override
    public void shutdown(Context context) {
        Log.i("shutdown : ");
    }

    @Override
    public void setPowerOnOff(Context context, int mode, Calendar offTime, Calendar onTime) {
        Log.i("setPowerOnOff : ");
    }

    @Override
    public void setPowerOnOffWeek(Context context, List<AlarmOnOffItem4> list) {

    }

    @Override
    public void cancelPowerOnOff(Context context) {
        Log.i("cancelPowerOnOff : ");
    }

    @Override
    public void screenshot(Context context, String filePath, long screenshotId) {
        Log.i("screenshot : ");
    }

    @Override
    public void install(Context context, String filePath) {
        Log.i("install : " + filePath);
    }

    @Override
    public void firmwareUpdate(Context context, String filePath) {
        Log.i("firmwareUpdate : " + filePath);
    }

    @Override
    public void firmwareReset(Context context) {
        Log.i("firmwareReset : ");
    }

    @Override
    public void screenshotResponse(Context context, boolean success, String msg) {

    }

    @Override
    public Intent createIntent(String action) {
        return null;
    }

    @Override
    public void sendBroadcast(Context context, Intent intent) {

    }

    @Override
    public void unique(Context context) {
        Log.i("unique : ");
    }

    @Override
    public void clear(Context context) {

    }
}
