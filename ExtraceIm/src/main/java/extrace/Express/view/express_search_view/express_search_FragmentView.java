package extrace.Express.view.express_search_view;

import android.app.Activity;

import java.util.List;

import extrace.Express.model.express_info_model.ExpressInfo;

/**
 * Created by 黎明 on 2016/4/17.
 */
public interface express_search_FragmentView
{
    public void onToastFail();
    public Activity getTheActivity();
    public void onToastSuccess(List<ExpressInfo> list);
}
