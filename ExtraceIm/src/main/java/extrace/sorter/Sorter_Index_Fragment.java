package extrace.sorter;

import android.app.Activity;
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
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;

import extrace.sorter.Package.ep_search.package_list.package_list_Fragment;
import extrace.sorter.Package.package_search.package_search_Fragment;
import extrace.main.MyApplication;
import extrace.ui.main.R;
import extrace.user.login.LoginFragment;
import extrace.user.me.MeFragment;


/**
 * Created by 黎明 on 2016/4/25.
 */
public class Sorter_Index_Fragment extends Fragment
{
    private Button meButton;
    private FragmentManager fm;
    private FragmentTransaction transaction;
    private ImageButton cameraButton;
    private ImageButton messageButton;
    private MyApplication myApplication;
    private Button open,close;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sorter_index_fragment,container,false);
        myApplication = (MyApplication) getActivity().getApplication();
        cameraButton = (ImageButton) view.findViewById(R.id.index_top_bar_camera);
        messageButton = (ImageButton) view.findViewById(R.id.index_top_bar_message);
        open=(Button)view.findViewById(R.id.open);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(),CaptureActivity.class),1);
                //1为拆包动作 此时需调用zxing执行扫码 扫码完毕后会返回一个packageID
                //由此packageID会获得其所含的快件list 显示list信息
                //拆包就是扫码-显示包裹信息-查看快件信息-确认拆包
            }
        });
        close=(Button)view.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(),CaptureActivity.class),2);
                //2为打包
                //扫一堆快件码-显示所扫快件信息list-打包-输入新包裹的信息-打包成功
                //
            }
        });
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCamera();
            }
        });
        transaction = getFragmentManager().beginTransaction();
        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


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
        startActivityForResult(new Intent(getActivity(),CaptureActivity.class),0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode== Activity.RESULT_OK)
        {
            if(requestCode==0) {
                Bundle bundle = data.getExtras();
                String result = bundle.getString("result");

                //Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
                package_search_Fragment fragment = new package_search_Fragment();
                Bundle bundle1 = new Bundle();
                bundle1.putString("ID", result);
                fragment.setArguments(bundle1);
                transaction.replace(R.id.fragment_container_layout, fragment);
                transaction.addToBackStack("sindex");
                transaction.commit();
            }
            if(requestCode==1) {
                Bundle bundle = data.getExtras();
                String packageID = bundle.getString("result");
               // Toast.makeText(getActivity(), packageID, Toast.LENGTH_LONG).show();
                //这是一个packageID，由此ID去search页面显示其expressinfo
                package_search_Fragment fragment = new package_search_Fragment();
                Bundle bundle1 = new Bundle();
                bundle1.putString("packageID", packageID);
                fragment.setArguments(bundle1);
                transaction.remove(Sorter_Index_Fragment.this);
                transaction.add(R.id.fragment_container_layout,fragment);
               //transaction.replace(R.id.fragment_container_layout, fragment);
                transaction.addToBackStack("sindex");
                transaction.commit();
            }
            if(requestCode==2) {
                Bundle bundle = data.getExtras();
                String result = bundle.getString("result");
               // Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();
                package_list_Fragment fragment = new package_list_Fragment();
                Bundle bundle1 = new Bundle();
                bundle1.putString("ID", result);
                fragment.setArguments(bundle1);
                transaction.replace(R.id.fragment_container_layout, fragment);
                transaction.addToBackStack("sindex");
                transaction.commit();
            }

        }
    }
}
