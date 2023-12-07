package NewController;

import NewView.NewBaseView;
import View.BaseView;
import View.ViewClass;

public class NewViewController {

    private static NewBaseView newBaseView;

    private ViewClass view;

    public NewViewController(ViewClass view ){
        setView(view);
    }

    public void setView( ViewClass view ){
        this.view = view;
    }

    public void setBaseView( NewBaseView bView ){
        newBaseView = bView;
    }

    public void showView(){

        newBaseView.setStageScene( view.getScene() );
    }
}