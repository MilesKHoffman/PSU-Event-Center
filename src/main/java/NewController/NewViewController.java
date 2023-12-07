package NewController;

import NewView.NewBaseView;
import NewView.NewViewClass;
import View.BaseView;
import View.ViewClass;

public class NewViewController {

    private static NewBaseView newBaseView;

    private NewViewClass view;

    public NewViewController( NewViewClass view ){
        setView(view);
    }

    public void setView( NewViewClass view ){
        this.view = view;
    }

    public void setBaseView( NewBaseView bView ){
        newBaseView = bView;
    }

    public void showView(){

        newBaseView.setStageScene( view.getScene() );
    }
}