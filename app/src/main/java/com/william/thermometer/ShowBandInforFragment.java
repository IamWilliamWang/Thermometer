package com.william.thermometer;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import static com.william.thermometer.R.id.textView;

/**
 * 显示手环信息Fragment
 */
public class ShowBandInforFragment extends Fragment {

//    final String[] textItem = {"当前手环电量：", "距离手环距离："};
//    final int[] progressItem = {67,90};
//    final String[] descrepionItem = {"67%","2.5米"};
//    final String[] keys = {"text","progress","descreption"};
//    List<Map<String,Object>> string2Object_Map_List = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root = inflater.inflate(R.layout.fragment_showbandinfor, container, false);
//        ListView listView = (ListView) root.findViewById(R.id.showbandinforListView);
//        this.initItemList();
//        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext()
//                ,string2Object_Map_List
//                ,R.layout.fragment_showbandinfor_item
//                ,new String[]{keys[0],keys[1],keys[2]}
//                ,new int[]{R.id.batteryTextView,R.id.batteryProgressBar,R.id.batteryTextView2});

        //listView.setAdapter(simpleAdapter);


        return root;
    }



//
//    private void initItemList() {
//
//        for(int i=0;i<textItem.length;i++) {
//            Map<String,Object> map = new HashMap<>();
//            map.put(keys[0],textItem[i]);
//            map.put(keys[1],progressItem[i]);
//            map.put(keys[2],descrepionItem[i]);
//            string2Object_Map_List.add(map);
//        }
//    }
}
