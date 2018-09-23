package com.onecalf.hard;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

public class IRemoteService extends Service{
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    private IBinder iBinder = new IMyAidlInterface.Stub() {
        @Override
        public int sum(int number1, int number2) throws RemoteException {
            return number1 + number2;
        }
    };


}
