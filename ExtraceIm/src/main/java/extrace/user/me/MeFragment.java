package extrace.user.me;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import extrace.Customer.Express.view.express_search_view.express_search_Fragment;
import extrace.main.MyApplication;
import extrace.ui.main.R;
import extrace.user.address.AddressFragment;

/**
 * Created by chao on 2016/4/17.
 */
public class MeFragment extends Fragment implements View.OnClickListener{
    FragmentManager fragmentManager;
    FragmentTransaction transaction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.me_fragment,container,false);
        TextView textView = (TextView) view.findViewById(R.id.top_bar_center_text);
        textView.setText("我");
        fragmentManager = getFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

        view.findViewById(R.id.top_bar_left_img).setOnClickListener(this);
        view.findViewById(R.id.user_receive_address).setOnClickListener(this);
        view.findViewById(R.id.user_tel).setOnClickListener(this);
        view.findViewById(R.id.user_password).setOnClickListener(this);
        view.findViewById(R.id.send_record).setOnClickListener(this);
        view.findViewById(R.id.receive_record).setOnClickListener(this);
        view.findViewById(R.id.about_soft).setOnClickListener(this);
        view.findViewById(R.id.my_complaint).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.top_bar_left_img:
                getFragmentManager().popBackStack();
                break;
            case R.id.user_receive_address:
                toUserReceiveAddress();
                break;
            case R.id.user_tel:
                break;
            case R.id.user_password:
                break;
            case R.id.send_record:
                toSendRecordFragment();
                break;
            case R.id.receive_record:
                toReceiveRecordFragment();
                break;
            case R.id.about_soft:
                break;
            case R.id.my_complaint:
                break;
        }
    }
    private void toUserReceiveAddress(){
        transaction.replace(R.id.fragment_container_layout,new AddressFragment());
        transaction.addToBackStack("mefragment");
        transaction.commit();
    }
    private void toUserInfoFragment(){

    }

    private void toSendRecordFragment(){

       express_search_Fragment fragment=new express_search_Fragment();
        Bundle bundle = new Bundle();
        bundle.putString("CID",String.valueOf(((MyApplication)getActivity().getApplication()).getUserInfo().getUID()));
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container_layout, fragment);
        transaction.addToBackStack("mefragment");
        transaction.commit();
    }
    private void toReceiveRecordFragment(){

        express_search_Fragment fragment=new express_search_Fragment();
        Bundle bundle = new Bundle();
        bundle.putString("Tel",String.valueOf(((MyApplication)getActivity().getApplication()).getUserInfo().getTelCode()));
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container_layout, fragment);
        transaction.addToBackStack("mefragment");
        transaction.commit();
    }

    private void toAboutSoftFragment(){

    }
    private void toMyComplaint(){

    }
}
