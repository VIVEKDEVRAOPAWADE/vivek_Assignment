package com.example.carwale.activities;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.carwale.Comman.Api;
import com.example.carwale.R;
import com.example.carwale.adapter.ListAdapter;
import com.example.carwale.model.ListFields;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;
public class MainActivity extends AppCompatActivity implements ListAdapter.EventListener, SearchView.OnQueryTextListener, SearchView.OnCloseListener {

            TextView  totalcases, totalrecovered ,totaldeaths,tnewcases ,tnewdeaths,tnewrecovered;
            RecyclerView recyclerView;
            ArrayList<ListFields> listfields = new ArrayList<>();
            ArrayList<ListFields> hotelsAll = new ArrayList<>();
            SearchView searchview;
            private ListAdapter adapter;

           @Override
           protected void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_main);
                    recyclerView=findViewById(R.id.recyclerView1);
                    totalcases =findViewById(R.id.totalcasescount);
                    totalrecovered =findViewById(R.id.totalrecoveredcount);
                    totaldeaths =findViewById(R.id.totaldeathcount);
                    tnewcases= findViewById(R.id.newconfirmedcount);
                    tnewdeaths=findViewById(R.id.newdeathcount);
                    tnewrecovered=findViewById(R.id.newrecoveredcount);
                    adapter= new ListAdapter(this,listfields);
                    recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
                    recyclerView.setAdapter(adapter);
                    searchview =findViewById(R.id.searchview1);
                    searchview.setOnQueryTextListener(this);
                    searchview.setOnCloseListener(this);
                   // searchview.onActionViewExpanded();
             }



                @Override
                protected void onResume() {
                    super.onResume();
                    loadData();
                }
                private void loadData() {
                        Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(Api.BASE_URL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .build();

                    //creating the api interface
                     Api api = retrofit.create(Api.class);
                        Call<String> call = api.getList();


                    call.enqueue(new Callback<String>() {
                    @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                if (response !=null) {
                    String jsonresponse = response.body().toString();
                     try {
                        JSONObject obj = new JSONObject(jsonresponse);
                        JSONArray dataArray = obj.getJSONArray("Countries");

                        String globalcount = obj.optString("Global");
                        JSONObject objg = new JSONObject(globalcount);
                        totalcases.setText(""+objg.getInt("TotalConfirmed"));
                        totaldeaths.setText(""+objg.getInt("TotalDeaths"));
                        totalrecovered.setText(""+objg.getInt("TotalRecovered"));
                        tnewcases.setText("+"+objg.getInt("NewConfirmed"));
                        tnewdeaths.setText("+"+objg.getInt("NewDeaths"));
                        tnewrecovered.setText("+"+objg.getInt("NewRecovered"));

                        for (int i = 0; i < dataArray.length(); i++) {

                            ListFields listf = new ListFields();
                            JSONObject dataobj = dataArray.getJSONObject(i);
                            System.out.println("data       array"+dataobj.getString("Country"));
                            listf.setTotalcases(dataobj.getInt("TotalConfirmed"));
                            listf.setTotaldeaths(dataobj.getInt("TotalDeaths"));
                            listf.setTotalrecovered(dataobj.getInt("TotalRecovered"));
                            listf.setCountryname(dataobj.getString("Country"));
                            Log.i(null, " list fileds" + listf.toString());
                            listfields.add(listf);
                                }
                            } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                hotelsAll.clear();
                hotelsAll.addAll(listfields);

                 adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
                });

                }

                        @Override
                        public void onSelect(ListFields listfields) {

                            Toast.makeText(this,"onQueryTextSubmit "+listfields.getCountryname() ,Toast.LENGTH_LONG).show();

                            Intent intent = new Intent(this, DetailActivity.class);
                            intent.putExtra("CountrymameDetail", listfields.getCountryname());
                            intent.putExtra("Totalconfirmed",listfields.getTotalcases());
                            intent.putExtra("Totalrecoverd",listfields.getTotalrecovered());
                            intent.putExtra("Totaldeaths" ,listfields.getTotaldeaths());
                            startActivity(intent);
                        }

                        @Override
                        public boolean onQueryTextSubmit(String query) {
                           // Toast.makeText(this,"onQueryTextSubmit "+query ,Toast.LENGTH_LONG).show();

                            loadData();
                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {
                            String text = newText;
                              adapter.getFilter().filter(newText);
                          //  Toast.makeText(this,"Query is "+ text,Toast.LENGTH_SHORT).show();


                            return false;
                        }

                        @Override
                        public boolean onClose() {
                                   loadData();
                           adapter.notifyDataSetChanged();
                            return false;
                        }
}
