package extrace.main;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import extrace.sorter.Sorter_Index_Fragment;
import extrace.ui.main.R;

public class MainActivity extends Activity implements MainView {

    FragmentManager fm;
    MainPresenter mainPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenter = new MainPresenterImpl(this);

        fm = getFragmentManager();
        setDefaultFragment();//设置第一个主布局的fragment
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }



    @Override
    public void setDefaultFragment(){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        Sorter_Index_Fragment fragment=new Sorter_Index_Fragment();
        transaction.replace(R.id.fragment_container_layout,fragment);
        //MainFragment indexFragment = new MainFragment();
       // transaction.replace(R.id.fragment_container_layout,indexFragment);
        transaction.commit();
    }
}
