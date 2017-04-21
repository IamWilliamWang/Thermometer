package com.william.thermometer;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * 设置Fragment
 */
public class SettingsFragment extends Fragment {

    private String[] settingsItem = {"连接手环","更换手环状态","设置检测间隔时间","设置预警温度","编辑数据","删除患者信息","初始化数据"};
    private boolean isBandOpen = true;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*获得当前view*/
        final View root = inflater.inflate(R.layout.fragment_settings, container, false);

        /*适配器加载*/
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_expandable_list_item_1,settingsItem);

        ListView listView = (ListView) root.findViewById(R.id.settings_listview);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (settingsItem[position]) {
                    case "连接手环":
                        connectBand(root);
                        break;
                    case "更换手环状态":
                        changeBandStates(root);
                        break;
                    case "设置检测间隔时间":
                        setTestTime(root);
                        break;
                    case "设置预警温度":
                        setAlertTemporature(root);
                        break;
                    case "编辑数据":
                        Snackbar.make(view, "没有数据可以编辑", Snackbar.LENGTH_LONG).show();
                        break;
                    case "删除患者信息":
                        Snackbar.make(view, "没有数据可以编辑", Snackbar.LENGTH_LONG).show();
                        break;
                    case "初始化数据":
                        clearAllData(view);
                        break;
                }
            }
        });

        return root;
    }

    private void connectBand(final View view) {
        showFakeProgressDialog(view,"连接手环中","请稍等片刻...","连接手环完成",2000);
    }

    private void changeBandStates(final View view) {
        showFakeProgressDialog(view,"切换手环状态中","请稍等片刻...",isBandOpen?"关闭手环成功":"开启手环成功",1000);
        isBandOpen=!isBandOpen;
    }

    private void setTestTime(final View view) {
        String[] items = {"1分钟","2分钟","3分钟","5分钟","10分钟","15分钟","30分钟","45分钟","1小时","2小时","3小时","6小时","12小时","24小时"};
        String title = "请选择间隔时间";
        showFakeAlertDialog(view,title,items,"设置时间完成");

    }

    private void setAlertTemporature(final View view) {
        String[] items = {"36℃","36.5℃","37℃","37.5℃","38℃","38.5℃","39℃","39.5℃","40℃","40.5℃","41℃","41.5℃","42℃"};
        String title = "请选择预警最低温度";
        showFakeAlertDialog(view,title,items,"设置预警温度完成");
    }

    private void clearAllData(final View view) {
        Snackbar.make(view, "初始化数据成功", Snackbar.LENGTH_LONG)
                .setAction("撤销", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Snackbar.make(v,"全部数据已恢复",Snackbar.LENGTH_LONG).show();
                    }
                }).show();
    }





    public static void showFakeProgressDialog(final View view, String progressDialogTitle, String progressDialogMessage, final String successInfor, final long waitTime) {
        final ProgressDialog progressDialog = new ProgressDialog(view.getContext());
        progressDialog.setTitle(progressDialogTitle);
        progressDialog.setMessage(progressDialogMessage);
        progressDialog.setCancelable(false);
        progressDialog.show();

        Thread nilThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progressDialog.dismiss();
                Snackbar.make(view, successInfor, Snackbar.LENGTH_SHORT)
                        .show();
            }
        });
        nilThread.start();
    }

    private void showFakeAlertDialog(final View view, String title, String[] items, final String successInfor) {
        new AlertDialog.Builder(view.getContext())
                .setTitle(title)
                .setIcon(R.drawable.ic_menu_manage)
                .setSingleChoiceItems(items,0,new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Snackbar.make(view, successInfor, Snackbar.LENGTH_SHORT)
                                .show();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();
    }
}
