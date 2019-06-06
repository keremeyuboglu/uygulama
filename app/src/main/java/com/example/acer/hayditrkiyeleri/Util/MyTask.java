package com.example.acer.hayditrkiyeleri.Util;

import android.os.AsyncTask;
import android.util.Log;

public class MyTask extends AsyncTask<Void, Void, Void> {

    Runnable toRun;

    public MyTask(Runnable toRun) {
        this.toRun = toRun;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        toRun.run();

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        Log.i("asnyctask", "bitti");
    }
}
