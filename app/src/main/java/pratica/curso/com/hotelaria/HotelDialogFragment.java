package pratica.curso.com.hotelaria;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

public class HotelDialogFragment extends DialogFragment implements TextView.OnEditorActionListener {

    private static final String DIALOG_TAG = "editDialog";
    private static final String EXTRA_HOTEL = "hotel";
    private EditText txtNome;
    private EditText txtEndereco;
    private RatingBar rtbEstrelas;
    private Hotel mHotel;

    public static HotelDialogFragment newInstance(Hotel hotel){
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_HOTEL,hotel);
        HotelDialogFragment dialog = new HotelDialogFragment();
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHotel = (Hotel)getArguments().getSerializable(EXTRA_HOTEL);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_dialog_hotel,container,false);
        txtNome = layout.findViewById(R.id.txtNome);
        txtNome.requestFocus();
        txtEndereco = layout.findViewById(R.id.txtEndereco);
        txtEndereco.setOnEditorActionListener(this);
        rtbEstrelas = layout.findViewById(R.id.rtbEstrelas);

        if (mHotel!=null){
            txtNome.setText(mHotel.getNome());
            txtEndereco.setText(mHotel.getEndereco());
            rtbEstrelas.setRating(mHotel.getEstrelas());
        }

        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        getDialog().setTitle(R.string.acao_novo);
        return layout;
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (EditorInfo.IME_ACTION_DONE == actionId){
            Activity activity = getActivity();
            if (activity instanceof AoSalvarHotel){
                if (mHotel == null){
                    mHotel = new Hotel(txtNome.getText().toString(),txtEndereco.getText().toString(),rtbEstrelas.getRating());
                }else {
                    mHotel.setNome(txtNome.getText().toString());
                    mHotel.setEndereco(txtEndereco.getText().toString());
                    mHotel.setEstrelas(rtbEstrelas.getRating());
                }
                AoSalvarHotel listener = (AoSalvarHotel) activity;
                listener.salvouHotel(mHotel);
                dismiss();
                return true;
            }

        }
        return false;
    }
    public void abrir(FragmentManager fm){
        if (fm.findFragmentByTag(DIALOG_TAG)==null){
            show(fm,DIALOG_TAG);
        }
    }
    public interface AoSalvarHotel{
        void salvouHotel(Hotel hotel);
    }
}
