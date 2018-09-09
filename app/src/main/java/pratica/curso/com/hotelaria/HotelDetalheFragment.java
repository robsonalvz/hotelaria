package pratica.curso.com.hotelaria;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

public class HotelDetalheFragment extends Fragment {
    public static final String TAG_DETALHE = "tagDetalhe";
    public static final String EXTRA_HOTEL = "hotel";
    TextView mTxtNome;
    TextView mTxtEndereco;
    RatingBar mRatingEstrelas;

    Hotel mHotel;
    public static HotelDetalheFragment novaInstancia(Hotel hotel){
        Bundle parametros = new Bundle();
        parametros.putSerializable(EXTRA_HOTEL,hotel);

        HotelDetalheFragment fragment = new HotelDetalheFragment();
        fragment.setArguments(parametros);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHotel = (Hotel) getArguments().getSerializable(EXTRA_HOTEL);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_detalhe_hotel,container,false);
        mTxtNome = layout.findViewById(R.id.txtNome);
        mTxtEndereco = layout.findViewById(R.id.txtEndereco);
        mRatingEstrelas = layout.findViewById(R.id.rtbEstrelas);
        if (mHotel!=null){
            mTxtNome.setText(mHotel.getNome());
            mTxtEndereco.setText(mHotel.getEndereco());
            mRatingEstrelas.setRating(mHotel.getEstrelas());
        }
        return layout;

    }
}
