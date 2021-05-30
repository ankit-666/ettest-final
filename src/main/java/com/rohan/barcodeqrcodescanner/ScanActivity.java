package com.rohan.barcodeqrcodescanner;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import java.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class ScanActivity extends AppCompatActivity {

    TextView hello;
    Uri uri;
    Button copy, scan, open, save, submit;
    EditText email, password;
    String copyText = "";
    JSONObject saved = new JSONObject();
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String qrresult;
    public static final String salt = "ettestInProcess";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        init();

        Intent in = getIntent();
        if (in.getIntExtra("position", -1) != -1) {

            try {
                if (!preferences.getString("saved", "").equals(""))
                    saved = new JSONObject(preferences.getString("saved", ""));
                hello.setText(saved.getString("saved"+in.getIntExtra("position",0)));
                copyText = saved.getString("saved"+in.getIntExtra("position",0));
                changeView(1);
                try {
                    URL url = new URL(copyText);
                    uri = Uri.parse(copyText);
                    open.setText("Open Link");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    uri = Uri.parse(copyText);
                    open.setText("Wrong Link");
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        } else {
            changeView(0);
            openScanner();
        }


        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (copyText.equals("")) {
                    Toast.makeText(ScanActivity.this, "Nothing to copy", Toast.LENGTH_SHORT).show();
                } else {
                    ClipboardManager clipMan = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("QRTest", copyText);
                    clipMan.setPrimaryClip(clip);
                    Toast.makeText(ScanActivity.this, "Copied to clipboard", Toast.LENGTH_SHORT).show();
                }
            }
        });

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScanner();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!copyText.equals("")) {
                    try {
                        if (!preferences.getString("saved", "").equals(""))
                            saved = new JSONObject(preferences.getString("saved", ""));
                        saved.put("saved" + saved.length(), copyText);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.d("testing", saved + "");
                    editor.putString("saved", saved.toString());
                    editor.apply();
                    Intent intent = new Intent(ScanActivity.this, ListActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(ScanActivity.this, "Sorry.. Scan a barcode or QR code and try again..", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void init() {
        preferences = getSharedPreferences("barcodeqrcode", Context.MODE_PRIVATE);
        editor = preferences.edit();
        copy = (Button) findViewById(R.id.copy);
        open = (Button) findViewById(R.id.open);
        scan = (Button) findViewById(R.id.scan);
        save = (Button) findViewById(R.id.save);
        submit = (Button) findViewById(R.id.submit);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        hello = (TextView) findViewById(R.id.hello);
    }

    private void changeView(int view) {
        if (view == 0) {
            copy.setVisibility(View.INVISIBLE);
            open.setVisibility(View.INVISIBLE);
            save.setVisibility(View.INVISIBLE);
        } else {
            copy.setVisibility(View.VISIBLE);
            open.setVisibility(View.VISIBLE);
            save.setVisibility(View.VISIBLE);
        }
    }

    private void openScanner() {
        IntentIntegrator intentIntegrator = new IntentIntegrator(ScanActivity.this);
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.setOrientationLocked(false);
        intentIntegrator.setPrompt("Scan the barcode or QR code to get the data!");
        intentIntegrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        final String[] emailID = new String[1];
        final String[] passwordID = new String[1];
        final String[] encryptedKey = new String[1];
        final String[] abc = {new String()};
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hello.setText("Verified");
                emailID[0] =email.getText().toString();
                passwordID[0] =password.getText().toString();
                encryptedKey[0] = passwordID[0];
                try {
                    abc[0] = AesDecrypt(result.getContents(),encryptedKey[0]);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (InvalidKeySpecException e) {
                    e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                } catch (InvalidAlgorithmParameterException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (BadPaddingException e) {
                    e.printStackTrace();
                } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
                }
                copyText = abc[0];
                hello.setText(abc[0]);
                try {
                    URL url = new URL(abc[0]);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                uri = Uri.parse(abc[0]);
                open.setText("Open Link");
            }
        });

        if (result != null) {
            if (result.getContents() == null) {
                Log.d("ScanActivity", "Cancelled scan");
                hello.setText("OOPS.. You did not scan anything");
                changeView(0);
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                try {
                    copyText = "abc[0]";
                    Log.d("ScanActivity", "Scanned");
                    hello.setText("abc[0]");
                    URL url = new URL("abc[0]");
                    uri = Uri.parse("abc[0]");
                    open.setText("Open Link");
                } catch (MalformedURLException e) {
                    hello.setText("OOPS.. You are not verified");
                    e.printStackTrace();
                    uri = Uri.parse(result.getContents());
                    open.setText("Wrong URL");
                }
                changeView(1);
//                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else
            super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            if(preferences.getString("saved","").equals("")){
                Toast.makeText(ScanActivity.this, "Nothing saved yet", Toast.LENGTH_SHORT).show();
            }
            else {
                Intent in = new Intent(ScanActivity.this,ListActivity.class);
                startActivity(in);
                finish();
            }
            return true;
        }
        else {
            Intent in = new Intent(ScanActivity.this,GenerateQR.class);
            startActivity(in);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    @TargetApi(Build.VERSION_CODES.O)
    public String AesDecrypt(String encryptedPath, String secretKey)
            throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException,
            InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException,
            IllegalBlockSizeException {
        byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec keySpec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(),65536,256);
        SecretKey tempSecretKey = factory.generateSecret(keySpec);
        SecretKey secretKeyActive = new SecretKeySpec(tempSecretKey.getEncoded(),"AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE,secretKeyActive,ivParameterSpec);
        Log.d("string value {}", new String(cipher.doFinal(Base64.getDecoder().decode(encryptedPath))));
        Log.d("encrypted path {}", encryptedPath);
        Log.d("secret key {}", secretKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedPath)));
    }

}
