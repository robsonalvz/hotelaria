package pratica.curso.com.hotelaria;

import android.app.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class HotelListFragment extends ListFragment {
    List<Hotel> mHoteis;
    ArrayAdapter<Hotel> mAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mHoteis = carregarHoteis();

        limparBusca();

    /*    mAdapter = new ArrayAdapter<Hotel>(getActivity(),android.R.layout.simple_list_item_1,mHoteis);
        setListAdapter(mAdapter);*/

    }
    private List<Hotel> carregarHoteis(){
        List<Hotel> hotels = new ArrayList<Hotel>();
        hotels.add(new Hotel("Recife Hotel", "Av Joao 1",5));
        hotels.add(new Hotel("Salvador Hotel", "Av Joao 2",4));
        hotels.add(new Hotel("Milagres Hotel", "Av Joao 3",3));
        hotels.add(new Hotel("Irece Hotel", "Av Joao 4",2));
        hotels.add(new Hotel("Cajazeiras Hotel", "Av Joao 5",2));
        hotels.add(new Hotel("Recife Hotel", "Av Joao 1",5));
        hotels.add(new Hotel("Salvador Hotel", "Av Joao 2",4));
        hotels.add(new Hotel("Milagres Hotel", "Av Joao 3",3));
        hotels.add(new Hotel("Irece Hotel", "Av Joao 4",2));
        hotels.add(new Hotel("Cajazeiras Hotel", "Av Joao 5",2));
        hotels.add(new Hotel("Recife Hotel", "Av Joao 1",5));
        hotels.add(new Hotel("Salvador Hotel", "Av Joao 2",4));
        hotels.add(new Hotel("Milagres Hotel", "Av Joao 3",3));
        hotels.add(new Hotel("Irece Hotel", "Av Joao 4",2));
        hotels.add(new Hotel("Cajazeiras Hotel", "Av Joao 5",2));
        return hotels;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Activity activity = getActivity();
        if (activity instanceof  AoClicarNoHotel){
            Hotel hotel = (Hotel)l.getItemAtPosition(position);

            AoClicarNoHotel listener = (AoClicarNoHotel)activity;
            listener.clicouNoHotel(hotel);
        }
    }
    public interface AoClicarNoHotel{
        void clicouNoHotel(Hotel hotel);
    }
    public void buscar(String s){
        if (s==null || s.trim().equals("")){
            limparBusca();
            return;
        }
        List<Hotel> hoteisEncontrados = new ArrayList<Hotel>(mHoteis);
        for (int i=hoteisEncontrados.size()-1;i>-0;i--){
            Hotel hotel = hoteisEncontrados.get(i);
            if (!hotel.getNome().toUpperCase().contains(s.toUpperCase())){
                hoteisEncontrados.remove(hotel);
            }
        }
        mAdapter = new ArrayAdapter<Hotel>(getActivity(),android.R.layout.simple_list_item_1,hoteisEncontrados);
        setListAdapter(mAdapter);
    }
    public void limparBusca(){
        mAdapter = new ArrayAdapter<Hotel>(getActivity(),android.R.layout.simple_list_item_1,mHoteis);
        setListAdapter(mAdapter);
    }
    public void adicionar(Hotel hotel){
        mHoteis.add(hotel);
        mAdapter.notifyDataSetChanged();
    }
}
