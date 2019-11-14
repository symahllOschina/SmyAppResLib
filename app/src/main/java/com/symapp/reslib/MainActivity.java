package com.symapp.reslib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sym.libs.usbprint.USBPrinter;
import com.sym.libs.util.ToastUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        USBPrinter usbPrinter = USBPrinter.getInstance();
        USBPrinter.initPrinter(MainActivity.this);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        USBPrinter.getInstance().close();
    }
}
