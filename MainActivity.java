package com.example.webservicesget;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
EditText e1,e2,e3,e4;
TextView t1,t2,t3;
Button b1,b2,b3,b4;
Spinner sp1,sp2;
    ArrayList<String> id=new ArrayList<String>();
    ArrayList<String> origen=new ArrayList<String>();
    ArrayList<String> destino=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
            relacionaVistas();
    }

    public  void relacionaVistas(){
        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);
        e3=(EditText)findViewById(R.id.editText3);
        e4=(EditText)findViewById(R.id.editText4);
    t1=(TextView) findViewById(R.id.textView);
    t2=(TextView) findViewById(R.id.textView2);
    t3=(TextView) findViewById(R.id.textView3);
    b1=(Button) findViewById(R.id.button);
    b2=(Button) findViewById(R.id.button2);
    b3=(Button) findViewById(R.id.button3);
    b4=(Button) findViewById(R.id.button4);
    sp1=(Spinner)findViewById(R.id.spinner);
    sp2=(Spinner)findViewById(R.id.spinner2);
    b1.setOnClickListener(this);
    b2.setOnClickListener(this);
    b3.setOnClickListener(this);
    b4.setOnClickListener(this);

}

public void insertar2(){
//    final String id=e1.getText().toString();
//    final String origen=e2.getText().toString();
//    final String destino=e3.getText().toString();

  RequestQueue servicio= Volley.newRequestQueue(this);
  String url="http://192.168.0.4/reservacionvuelos/cargaDatos.php";
  StringRequest respuesta= new StringRequest(Request.Method.POST, url,
          new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {
Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
              }
          }, new Response.ErrorListener(){
      @Override
      public void onErrorResponse(VolleyError error) {
          Toast.makeText(getApplicationContext(),"Error de comunicacion:"+error,Toast.LENGTH_LONG).show();
      }
  })
//  {
//      @Override
//      protected Map<String, String> getParams() throws AuthFailureError {
//          Map<String,String>  valoresPOST= new HashMap<String, String>();
//          valoresPOST.put("id",id);
//          valoresPOST.put("o",origen);
//          valoresPOST.put("d",destino);
//          return valoresPOST;
//      }
//  }
  ;

  servicio.add(respuesta);
}

public void servicios(){
        RequestQueue servicioConsulta=Volley.newRequestQueue(this);
        JsonArrayRequest respuestaConsulta= new JsonArrayRequest(
                "http://192.168.0.4/reservacionvuelos/cargaDatos.php",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                    id.clear();
                    origen.clear();
                    destino.clear();
                        JSONObject json=null;
                        try {
                            for (int i = 0; i < response.length() ; i++) {
                                json=response.getJSONObject(i);
                                id.add(json.getString("id"));
                                origen.add(json.getString("origen"));
                                destino.add(json.getString("destino"));
                            }

                            Toast.makeText(getApplicationContext(),
                                    ""+origen+"--"+destino,Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(),
                                    "ERROR COMUNICACION",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        servicioConsulta.add(respuestaConsulta);

    ArrayAdapter<String> adapta=new ArrayAdapter<String>(getApplicationContext(),
            android.R.layout.simple_list_item_1,origen);
    sp1.setAdapter(adapta);

    ArrayAdapter<String> adapter2=new ArrayAdapter<String>(getApplicationContext(),
            android.R.layout.select_dialog_item,destino);
    sp2.setAdapter(adapter2);
    }

public void insertar(){

    RequestQueue servicio2= Volley.newRequestQueue(this);
    String url="http://192.168.128.1:3977/api/registro";
    StringRequest respuesta2= new StringRequest(Request.Method.POST, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
    Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getApplicationContext(),"Error Comunicacion",Toast.LENGTH_LONG).show();
        }
    }){
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> valores= new HashMap<String,String>();
                valores.put("nombre",e1.getText().toString());
                valores.put("apellido",e2.getText().toString());
                valores.put("email",e3.getText().toString());
            valores.put("password",e4.getText().toString());
                return valores;
        }
    };
servicio2.add(respuesta2);

}

public void eliminar(){
    RequestQueue servicio3= Volley.newRequestQueue(this);
    String url="http://192.168.0.6/reservacionvuelos/eliminar.php";
    StringRequest respuesta3= new StringRequest(Request.Method.POST, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getApplicationContext(),"Error C",Toast.LENGTH_LONG).show();
        }
    }){
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String,String> valores= new HashMap<String,String>();
            valores.put("id",e1.getText().toString());
            return valores;
        }
    };
    servicio3.add(respuesta3);
}

public void editar(){
      RequestQueue servicio4= Volley.newRequestQueue(this);
      String id=e1.getText().toString();
      String origen=e2.getText().toString();
      String destino=e3.getText().toString();
      String url="http://169.254.174.56/reservacionvuelos/editar.php?id="+id+"&o="+origen+"&d="+destino;
      StringRequest respuesta4= new StringRequest(Request.Method.GET, url,
              new Response.Listener<String>() {
                  @Override
                  public void onResponse(String response) {
                      Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                  }
              }, new Response.ErrorListener() {
          @Override
          public void onErrorResponse(VolleyError error) {
              Toast.makeText(getApplicationContext(),"Error de Comunicacion",Toast.LENGTH_LONG).show();
          }
      });
servicio4.add(respuesta4);
}

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button:
                insertar();
                break;
            case R.id.button2:
                servicios();
                break;
            case R.id.button3:
                eliminar();

                break;
            case R.id.button4:
                editar();
                break;
        }}}




