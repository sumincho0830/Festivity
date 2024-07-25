package org.techtown.festivity.ui.invitations;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.techtown.festivity.R;

public class InvitationsActivity extends AppCompatActivity {
    EditText senderTxt;
    EditText contentTxt;
    TextView dateTxt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invitations);

        senderTxt = findViewById(R.id.sender_txt);
        contentTxt = findViewById(R.id.content_txt);
        dateTxt = findViewById(R.id.date_txt);

        //ToggleButton button = findViewById(R.id.accept_button);
        Intent newIntent = getIntent();
        processIntent(newIntent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        processIntent(intent);

        super.onNewIntent(intent);
    }

    public void processIntent(Intent intent){
        Intent newIntent = getIntent();
        String sender = newIntent.getStringExtra("sender");
        String contents = newIntent.getStringExtra("contents");
        String receivedDate = newIntent.getStringExtra("receivedDate");

        senderTxt.setText(sender);
        contentTxt.setText(contents);
        dateTxt.setText(receivedDate);
    }
}
