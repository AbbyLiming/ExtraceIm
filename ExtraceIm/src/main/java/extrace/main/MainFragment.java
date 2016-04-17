package extrace.main;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import extrace.ui.main.R;
import extrace.user.login.LoginFragment;
import extrace.user.me.MeFragment;
import zxing.util.CaptureActivity;

/**
 * Created by songchao on 16/4/4.
 */
public class MainFragment extends Fragment {

    private Button meButton;
    private FragmentManager fm;
    private FragmentTransaction transaction;
    private ImageButton cameraButton;
    private ImageButton messageButton;
    private MyApplication myApplication;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.index_fragment,container,false);
        myApplication = (MyApplication) getActivity().getApplication();

        cameraButton = (ImageButton) view.findViewById(R.id.index_top_bar_camera);
        messageButton = (ImageButton) view.findViewById(R.id.index_top_bar_message);

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCamera();
            }
        });

        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        fm = getFragmentManager();
        transaction = fm.beginTransaction();
        FragmentTransaction transaction = fm.beginTransaction();

        meButton = (Button) view.findViewById(R.id.me_button);
        meButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!myApplication.getUserInfo().getLoginState()) {//没有登陆跳转登陆界面
                    toLoginFragment();
                }else {
                    //登陆后跳转"我"界面
                    toMeFragment();
                }
            }
        });
        return view;
    }

    private void toMeFragment(){
        MeFragment meFragment = new MeFragment();
        transaction.replace(R.id.fragment_container_layout, meFragment);
        transaction.addToBackStack("index");
        transaction.commit();
    }

    private void toLoginFragment(){
        LoginFragment loginFragment = new LoginFragment();
        transaction.replace(R.id.fragment_container_layout, loginFragment);
        transaction.addToBackStack("index");
        transaction.commit();
    }

    private void startCamera(){
        Intent intent = new Intent();
        intent.putExtra("Action","Captrue");
        intent.setClass(getActivity(), CaptureActivity.class);
        startActivityForResult(intent, 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        System.out.println("");
    }
}