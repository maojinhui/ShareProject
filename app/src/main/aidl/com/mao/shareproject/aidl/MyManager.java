package com.mao.shareproject.aidl;

import android.os.IBinder;
import android.os.RemoteException;

public class MyManager implements IMyAidlInterface {


    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }

    @Override
    public IBinder asBinder() {
        return null;
    }



}
