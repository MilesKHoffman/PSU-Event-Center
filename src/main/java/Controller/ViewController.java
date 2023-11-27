package Controller;

import View.*;

public class ViewController {

    private static BaseView baseView;

    private ViewClass view;

    public ViewController( ViewClass view ){
        setView(view);
    }

    public void setView( ViewClass view ){
        this.view = view;
    }

    public void setBaseView( BaseView bView ){
        baseView = bView;
    }

    public void showView(){

        baseView.setStageScene( view.getScene() );
    }
}