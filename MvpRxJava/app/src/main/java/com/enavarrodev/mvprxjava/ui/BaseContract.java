package com.enavarrodev.mvprxjava.ui;

/**
 * Created by enava on 13/2/2018.
 */

/**
 * Base Contract.
 * All Contracts in the app needs to extend from this Contract
 */
public interface BaseContract {
    /**
     * Here is the Contract from the view to presenter.
     * Here you set any method from VIEW available to Presenter.
     */
    interface View {
        void setPresenter(Presenter presenter);

        //void showLoading(String message);

        //void showLoading();

        //void hideLoading();

        void onUnknownError(String error);

        void onTimeout();

        void onNetworkError();

        boolean isNetworkConnected();

        void onConnectionError();

    }

    /**
     * Here is the contract from the Presenter to the View.
     * Here you set any methods from PRESENTER available to View.
     */
    interface Presenter {
    }

    /**
     * Este es el contrato del Presentador a la Vista, el cual se va a encargar de recibir la respuesta de servicios web.
     */
    interface ServicePresenter extends Presenter {

        void registerOnEventBus();

        void unregisterFromEventBus();
    }

}
