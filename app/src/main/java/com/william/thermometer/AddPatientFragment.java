package com.william.thermometer;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * 录入患者信息Fragment
 */
public class AddPatientFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View root = inflater.inflate(R.layout.fragment_addpatient, container, false);

        Button submitButton = (Button) root.findViewById(R.id.add_patient_submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingsFragment.showFakeProgressDialog(root,"正在录入","请稍等...","录入成功",1000);

            }
        });

        return root;
    }
}
