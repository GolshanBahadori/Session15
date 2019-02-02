package com.example.session15;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

//قدم اول
// برای اینکه BroadcastReceiver بسازید باید در ابتدا کلاس BroadcastReceiver رو باید extend کنید
public class BootCompleteBroadCastReceiver extends BroadcastReceiver {

    //قدم دوم
    //چون کلاس BroadcastReceiver از نوع abstract class هست باید متد onReceive ش رو ایمپلمنت کنید
    //این متد به شما چندتا Data میده یکی Context هست که شما به Context اینجا دسترسی دارید و یکی Intent هست
    // این intent همون چیزی هست که اون کسی که داره این event رو ارسال میکنه این intent رو توش data قرار میده و به شما تحویل میده
    @Override
    public void onReceive(Context context, Intent intent) {

    }
}
