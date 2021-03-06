package extrace.user.login;


/**
 * Created by chao on 2016/4/16.
 * 登陆控制层
 */
public class LoginPresenterImpl implements LoginPresenter {
    LoginFragmentView loginFragmentView;
    LoginModel loginModel;
    public LoginPresenterImpl(LoginFragmentView loginView){
        this.loginFragmentView = loginView;
        loginModel = new LoginModelImpl(loginView.getTheActivity(),this);
    }

    @Override
    public void onLoginSuccess() {
        loginFragmentView.showToast("登陆成功");
        //登陆成功之后，把model用户曾信息补全，登陆标志位置为成功
        loginFragmentView.onback();
    }

    @Override
    public void onLoginFail() {
        loginFragmentView.showToast("登陆失败，手机号或密码错误");
    }

    @Override
    public void onRegisterRepeat() {
        loginFragmentView.onError(LoginFragment.TELERROR,"手机号已经存在，请登录");
    }

    @Override
    public void onRegisterSuccess() {
        loginFragmentView.showToast("注册成功");
    }

    @Override
    public void onRegisterFail() {
        loginFragmentView.showToast("注册失败请重试");
    }

    @Override
    public void startLogin(String tel, String password) {
        loginModel.onStartLogin(tel,password);
    }

    @Override
    public void startRegister(String tel,String password) {
        loginModel.onStartRegister(tel, password);
    }
}

