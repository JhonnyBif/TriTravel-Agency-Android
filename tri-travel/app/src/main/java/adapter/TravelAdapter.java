package adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.travel_agency_android.DatabaseHelper;
import com.example.travel_agency_android.MainActivity;
import com.example.travel_agency_android.R;
import com.example.travel_agency_android.ReportFormActivity;
import com.example.travel_agency_android.TravelFormActivity;

import java.util.ArrayList;

public class TravelAdapter extends BaseAdapter {
    private ArrayList<TravelModel> travelList;
    private Activity activity;

    private DatabaseHelper databaseHelper;

    public TravelAdapter(final Activity activity) {
        databaseHelper = new DatabaseHelper(activity);
        this.activity = activity;
    }

    public void setTravelList(ArrayList<TravelModel> travels) {
        travelList = travels;
    }

    @Override
    public int getCount() {
        return travelList != null ? travelList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return travelList != null ? travelList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = activity.getLayoutInflater().inflate(R.layout.item_lista, viewGroup, false);
        }

        TravelModel travel = travelList.get(position);

        TextView name = view.findViewById(R.id.travelName);
        name.setText(travel.getName());

        TextView id = view.findViewById(R.id.travelId);
        id.setText("#" + travel.getId().toString() + " ");

        TextView description = view.findViewById(R.id.travelDescription);
        description.setText(travel.getDescription());

//        Button btnEdit = view.findViewById(R.id.btnEdit);
//        Button btnDelete = view.findViewById(R.id.btnDelete);
        Button btnViewReport = view.findViewById(R.id.btnViewReport);

//        btnEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(activity, TravelFormActivity.class);
//                intent.putExtra("travelId", travel.getId());
//                activity.startActivity(intent);
//            }
//        });

//        btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new AlertDialog.Builder(activity)
//                        .setTitle("Excluir Viagem")
//                        .setMessage("Tem certeza de que deseja excluir este item?")
//                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                databaseHelper.deleteTravelById(travel.getId().intValue());
//                                travelList.remove(travel);
//                                notifyDataSetChanged();
//
//                                Intent intent = new Intent(activity, MainActivity.class);
//                                activity.startActivity(intent);
//                            }
//                        })
//                        .setNegativeButton("Não", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                            }
//                        })
//                        .show();
//            }
//        });


        btnViewReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ReportFormActivity.class);
                intent.putExtra("travelId", travel.getId());
                activity.startActivity(intent);
            }
        });

        return view;
    }
}
