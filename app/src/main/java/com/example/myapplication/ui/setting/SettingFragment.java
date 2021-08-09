package com.example.myapplication.ui.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;


public class SettingFragment extends Fragment {
    private SettingViewModel settingViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        startActivity(new Intent(getActivity(), com.example.myapplication.ui.setting.activity_preference.class));

        settingViewModel =
                new ViewModelProvider(this).get(SettingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_setting, container, false);

        final TextView textView = root.findViewById(R.id.text_setting);
        settingViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
//    private SettingViewModel settingViewModel;
//    @Override
//    public void onCreate(Bundle _savedInstanceState) {
//        super.onCreate(_savedInstanceState);
//        addPreferencesFromResource(R.xml.prefs);
//
//
//    }



//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        addPreferencesFromResource(R.xml.prefs);
//        View root = inflater.inflate(R.layout.fragment_news, container, false);
//        Intent intent = new Intent(getActivity(), NewsActivity.class);
//        startActivity(intent);
//        return root;
//
//    }
//
//    @Override
//    public void onActivityCreated(Bundle _savedInstanceState) {
//        super.onActivityCreated(_savedInstanceState);
//
//    }


}