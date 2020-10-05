package com.army.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.army.R;
import com.army.model.CCode;
import com.army.model.Contry;
import com.army.model.RestResponse;
import com.army.model.User;
import com.army.retrofit.APIClient;
import com.army.retrofit.GetResult;
import com.army.utils.CustPrograssbar;
import com.army.utils.SessionManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;

import static com.army.utils.Utils.isvarification;

public class SignUpactivity extends AppCompatActivity implements GetResult.MyListener {
    CustPrograssbar custPrograssbar;
    SessionManager sessionManager;
    @BindView(R.id.ed_username)
    EditText edUsername;
    @BindView(R.id.ed_email)
    EditText edEmail;
    @BindView(R.id.ed_alternatmob)
    EditText edAlternatmob;
    @BindView(R.id.ed_password)
    EditText edPassword;
//    @BindView(R.id.spinner)
//    Spinner spinner;
    List<CCode> cCodes = new ArrayList<>();
    String codeSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(SignUpactivity.this);
        custPrograssbar = new CustPrograssbar();


    }



    private void isRegister() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", edAlternatmob.getText().toString());
            jsonObject.put("ccode", codeSelect);
            JsonParser jsonParser = new JsonParser();
            Call<JsonObject> call = APIClient.getInterface().getForgot((JsonObject) jsonParser.parse(jsonObject.toString()));
            GetResult getResult = new GetResult();
            getResult.setMyListener(this);
            getResult.callForLogin(call, "1");
            custPrograssbar.prograssCreate(SignUpactivity.this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void callback(JsonObject result, String callNo) {
        try {
            if (callNo.equalsIgnoreCase("1")) {
                custPrograssbar.closePrograssBar();
                Gson gson = new Gson();
                RestResponse response = gson.fromJson(result.toString(), RestResponse.class);
                if (response.getResult().equals("true")) {
                    Toast.makeText(SignUpactivity.this, "Mobile Number Already Registered", Toast.LENGTH_LONG).show();
                } else {
                    User user = new User();
                    user.setEmail(edEmail.getText().toString());
                    user.setMobile(edAlternatmob.getText().toString());
                    user.setName(edUsername.getText().toString());
                    user.setPassword(edPassword.getText().toString());


                    user.setCcode(codeSelect);
                    sessionManager.setUserDetails("", user);
                    isvarification =1;
                    startActivity(new Intent(SignUpactivity.this, VerifyPhoneActivity.class).putExtra("code", codeSelect).putExtra("phone", edAlternatmob.getText().toString()));

                }
            } else if (callNo.equalsIgnoreCase("2")) {
                Gson gson = new Gson();
                Contry contry = gson.fromJson(result.toString(), Contry.class);
                cCodes = contry.getData();
                List<String> Arealist = new ArrayList<>();
                for (int i = 0; i < cCodes.size(); i++) {
                    if (cCodes.get(i).getStatus().equalsIgnoreCase("1")) {
                        Arealist.add(cCodes.get(i).getCcode());
                    }
                }


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @OnClick({R.id.btn_sign, R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sign:
                startActivity(new Intent(SignUpactivity.this, VerifyPhoneActivity.class));
                //change above line to://startActivity(new Intent(SignUpactivity.this, VerifyPhoneActivity.class).putExtra("code", codeSelect).putExtra("phone", edAlternatmob.getText().toString()));


                //Uncomment below lines

                // if (validation()) {
                 //isRegister();

                // }
                break;
            case R.id.btn_login:
                startActivity(new Intent(SignUpactivity.this, LoginActivity.class));
                finish();
                break;
            default:
                break;
        }
    }
    public boolean validation() {
        if (edUsername.getText().toString().isEmpty()) {
            edUsername.setError("Enter Name");
            return false;
        }
        if (edEmail.getText().toString().isEmpty()) {
            edEmail.setError("Enter Valid Email");
            return false;
        }
        if (edAlternatmob.getText().toString().isEmpty()) {
            edAlternatmob.setError("Enter Valid Mobile No");
            return false;
        }
        if (edPassword.getText().toString().isEmpty()) {
            edPassword.setError("Enter Password");
            return false;
        }
        return true;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(SignUpactivity.this, LoginActivity.class));
        finish();
    }
}
