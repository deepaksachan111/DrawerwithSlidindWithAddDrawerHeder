package com.lko.qserver.shinecitycp.drawerwithswipetab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lko.qserver.shinecitycp.R;

/**
 * Created by Ratan on 7/29/2015.
 */
public class SocialFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      //  FontsOverride.setDefaultFont(getActivity(), "MONOSPACE", "Pacifico.ttf");
        return inflater.inflate(R.layout.social_layout,null);

    }


}
