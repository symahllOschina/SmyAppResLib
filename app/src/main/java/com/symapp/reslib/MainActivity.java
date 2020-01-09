package com.symapp.reslib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sym.libs.usbprint.USBPrinter;
import com.sym.libs.util.ToastUtil;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 接收USB设备插入，拔出状态
         */
//        IntentFilter filter = new IntentFilter(USBPrinter.IS_USB_CONN_ACTION);
//        registerReceiver(receiver, filter);

        textView = findViewById(R.id.helloworld);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                USBPrinter usbPrinter = USBPrinter.getInstance();
                USBPrinter.initPrinter(MainActivity.this,false);
                usbPrinter.bold(true);
                usbPrinter.setTextSize(3);
                usbPrinter.setAlign(1);
                usbPrinter.printTextNewLine("标题");
                usbPrinter.printLine(1);
                usbPrinter.setTextSize(0);
                usbPrinter.setAlign(0);
                usbPrinter.bold(false);
                usbPrinter.printTextNewLine("出品单号："+"54545646456");
                usbPrinter.printTextNewLine("出品员："+"偶家哈吉娃娃i哦我是等级");
                usbPrinter.printBarCode("6936983800013");
                usbPrinter.printLine(1);
                usbPrinter.setAlign(1);
                usbPrinter.printQRCode("6936983800013",12);
                usbPrinter.printLine(5);
                usbPrinter.cutPager();
            }
        });


    }


    /*BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            String state = intent.getExtras().getString("state");

            if(state.equals(USBPrinter.USB_CONN_STATUS_INPUT)){

                ToastUtil.showText(context, "有设备插入",1);

            }else if(state.equals(USBPrinter.USB_CONN_STATUS_OUTPUT)){

                ToastUtil.showText(context, "有设备拔出",1);

            }else if(state.equals(USBPrinter.USB_CONN_STATUS_DISABLE)){

                ToastUtil.showText(context,"USB设备请求被拒绝",1);

            }
        }
    };*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        USBPrinter.getInstance().close();
    }
}
