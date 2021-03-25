package com.onearmy.mstapp.activity1;

public class Presenter implements Ipresenter {
    Iview view;
    String message;
    Boolean valid= false;

    public Presenter(Iview view) {
        this.view = view;
    }

    @Override
    public void doOnLogin(String email, String password) {
        Model model = new Model(email,password);

        int validation = model.isValid();
        if(validation == 1){
            message = "Enter email id";
            valid = false;
        }else if (validation == 2){
            message = "Enter password";
            valid = false;
        }else if(validation == 3){
            message = "Enter valid email id";
            valid = false;
        }
        else if(validation==4){
            message = "Password need to be 10 char long";
            valid = false;
        }else if (validation == -1){
            message = "valid credantials";
            valid = true;
        }
        view.login(message,valid);
    }
}
