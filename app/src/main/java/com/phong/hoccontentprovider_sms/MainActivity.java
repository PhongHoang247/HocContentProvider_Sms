package com.phong.hoccontentprovider_sms;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView lvSms;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        readSms();
    }

    private void readSms() {
        Uri uri = Uri.parse("content://sms/inbox");//đến
        //Uri uri = Uri.parse("content://sms/sent");//gửi đi
        Cursor cursor = getContentResolver().query(uri,null,null,null,null);
        int vitriPhone = cursor.getColumnIndex("address");//lấy địa chỉ
        int vitriTime = cursor.getColumnIndex("date");//lấy ngày/tháng/năm
        int vitriBody = cursor.getColumnIndex("body");//lấy nội dung tin nhắn
        while (cursor.moveToNext()){
            String phone = cursor.getString(vitriPhone);
            String time = cursor.getString(vitriTime);
            String body = cursor.getString(vitriBody);
            adapter.add(phone + "\n" + time + "\n" + body);
        }
        cursor.close();
    }

    private void addControls() {
        lvSms = findViewById(R.id.lvSms);
        adapter = new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.simple_list_item_1);
        lvSms.setAdapter(adapter);
    }
}
