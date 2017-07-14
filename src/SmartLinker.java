package org.apache.cordova;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.widget.Toast;

import com.hiflying.smartlink.ISmartLinker;
import com.hiflying.smartlink.OnSmartLinkListener;
import com.hiflying.smartlink.SmartLinkedModule;
import com.hiflying.smartlink.v7.MulticastSmartLinker;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 李世杰.
 */
public class SmartLinker extends CordovaPlugin {
    private ISmartLinker mSnifferSmartLinker;
    @Override
    public boolean execute(String action, final CordovaArgs args, final CallbackContext callbackContext) throws JSONException {

        if ("startSmartLinker".equals(action)) {
//            callbackContext.success("连接完成");
            mSnifferSmartLinker = MulticastSmartLinker.getInstance();
            mSnifferSmartLinker.setOnSmartLinkListener(new OnSmartLinkListener() {
                @Override
                public void onLinked(SmartLinkedModule smartLinkedModule) {
                    callbackContext.success(smartLinkedModule.getMac());

                }

                @Override
                public void onCompleted() {
                    callbackContext.success("连接完成");
                    Log.e("SmartLinker", "onCompleted: " + "连接完成");
                }

                @Override
                public void onTimeOut() {
                    callbackContext.error("连接超时");
//                    Toast.makeText(SmartLinker.this.cordova.getActivity(), "连接超时", Toast.LENGTH_LONG).show();
                    Log.e("SmartLinker", "onTimeOut: " + "连接超时");
                }
            });
            this.cordova.getThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        String psw = args.getString(0);
                        Log.e("SmartLinker", "starLink: " + psw);
//                        cordova.getActivity().runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                Toast.makeText(SmartLinker.this.cordova.getActivity(), getSSID(), Toast.LENGTH_LONG).show();
//                            }
//                        });

                        mSnifferSmartLinker.start(SmartLinker.this.cordova.getActivity(), psw, getSSID().trim());
                    } catch (Exception e) {
                        Log.e("SmartLinker", "erro: " + e);
                    }

                }
            });

            return true;
        } else if ("getSSID".equals(action)) {
            callbackContext.success(getSSID());
            return true;
        }
        return super.execute(action, args, callbackContext);
    }

    private String getSSID() {
        WifiManager conMan = (WifiManager) this.cordova.getActivity().getSystemService(Context.WIFI_SERVICE);
        return conMan.getConnectionInfo().getSSID();
    }
}
