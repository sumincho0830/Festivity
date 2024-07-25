package org.techtown.festivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import org.techtown.festivity.ui.invitations.InvitationsActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsReceiver extends BroadcastReceiver {

    public static final String TAG = "SmsReceiver";

    public SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.i(TAG,"onReceive() 메서드 호출됨");

        Bundle bundle = intent.getExtras(); //인텐트에서 Bundle객체 가져오기
        SmsMessage[] messages = parseSmsMessage(bundle); //메시지를 받아서 형식화한 뒤 저장함

        if(messages != null && messages.length > 0){ //messages 변수에 인스턴스 배열이 할당되었고, 배열이 비어있지 않다면
            String sender = messages[0].getOriginatingAddress(); //주소 가져오기
            String contents = messages[0].getMessageBody(); //내용 가져오기
            Date receivedDate = new Date(messages[0].getTimestampMillis());

            sendToActivity(context.getApplicationContext(), sender,contents,receivedDate);
        }
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    private SmsMessage[] parseSmsMessage(Bundle bundle){
        Object[] objs = (Object[]) bundle.get("pdus"); //Bundle객체에 들어가 있는 부가 데이터 중 pdus 가져오기
        SmsMessage[] messages = new SmsMessage[objs.length]; //pdus 데이터의 길이만큼 SMS 메시지 배열

        int smsCount = objs.length; //== messages.length

        for(int i = 0;i<smsCount;i++){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){ //버전이 낮다면?
                String format = bundle.getString("format");
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i],format); //바이트 배열을 정형화 시켜 SMS배열에 넣기
            }else{
                messages[i] = SmsMessage.createFromPdu((byte[]) objs[i]); //버전이 맞는다면 이미 형식이 맞는다
            }
        }

        return messages;
    }

    private void sendToActivity(Context context, String sender, String contents, Date receivedDate){
        Intent newIntent = new Intent(context, InvitationsActivity.class);
        newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|
                Intent.FLAG_ACTIVITY_SINGLE_TOP| Intent.FLAG_ACTIVITY_CLEAR_TOP);
        newIntent.putExtra("sender", sender);
        newIntent.putExtra("contents", contents);
        newIntent.putExtra("receivedDate", format.format(receivedDate));
        context.startActivity(newIntent);
    }
}