package com.qw.sample;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import com.qw.sample.utils.Utils;
import com.qw.sample.utils.UtilsWithPermission;

public class AfterActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_CONTACT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        findViewById(R.id.bt_call).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UtilsWithPermission.makeCall(AfterActivity.this, "10086");
            }
        });
        findViewById(R.id.bt_choose_contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UtilsWithPermission.chooseContact(AfterActivity.this, REQUEST_CODE_CONTACT);
            }
        });
        findViewById(R.id.bt_read_phone_status).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UtilsWithPermission.readPhoneStatus();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_CONTACT:
                    Utils.onGetChooseContactData(AfterActivity.this, data, new Utils.ReadContactListener() {
                        @Override
                        public void onSuccess(Utils.ContactInfo contactInfo) {
                            Toast.makeText(AfterActivity.this, contactInfo.toString(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailed() {

                        }
                    });
                    break;
                default:
                    break;
            }
        }
    }
}
