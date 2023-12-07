package Controller;

import View.*;

public class ViewController {

    private static BaseView newBaseView;

    private ViewClass view;

    public ViewController(ViewClass view ){
        setView(view);
    }

    public void setView( ViewClass view ){
        this.view = view;
    }

    public void setBaseView( BaseView bView ){
        newBaseView = bView;
    }

    public void showView(){

        newBaseView.setStageScene( view.getScene() );
    }
}