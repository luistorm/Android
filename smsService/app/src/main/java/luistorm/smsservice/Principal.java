package luistorm.smsservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.inputmethodservice.KeyboardView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class Principal extends AppCompatActivity  implements View.OnClickListener,View.OnFocusChangeListener,View.OnKeyListener{
    private TabHost tbH;
    private EditText numero;
    private EditText mensaje;
    private Mensajes ctrl;
    private Button enviar;
    private BroadcastReceiver receiver;
    private TextView conversacion;
    private  TextView historial;
    private Button nuevo;
    public contactFragment fragment;
    protected static final int REQUEST_CODE = 10;
    private ScrollView conv;
    private RelativeLayout ppal;
    private int dim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        tbH = (TabHost) findViewById(R.id.tbHost);
        tbH.setup();
        TabHost.TabSpec tab1 = tbH.newTabSpec("tb1");
        TabHost.TabSpec tab2 = tbH.newTabSpec("tb2");
        TabHost.TabSpec tab3 = tbH.newTabSpec("tb3");
        tab1.setIndicator("Enviar");
        tab1.setContent(R.id.tb1);
        tab2.setIndicator("Contacto");
        tab2.setContent(R.id.tb2);
        tbH.addTab(tab1);
        tbH.addTab(tab2);
        tab3.setIndicator("Recibidos");
        tab3.setContent(R.id.tb3);
        tbH.addTab(tab3);
        tbH.setCurrentTab(1);
        numero = (EditText) findViewById(R.id.editText);
        mensaje = (EditText) findViewById(R.id.editText3);
        enviar = (Button) findViewById(R.id.button);
        enviar.setOnClickListener(this);
        ctrl = new Mensajes(this);
        IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                processReceive(context,intent);
            }
        };
        registerReceiver(receiver,filter);
        conversacion = (TextView) findViewById(R.id.textView4);
        historial = (TextView) findViewById(R.id.textView6);
        fragment = (contactFragment) getSupportFragmentManager().
                findFragmentById(R.id.contactContainer);
        if(fragment == null) {
            fragment = contactFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.contactContainer,fragment).commit();
        }
        nuevo = (Button) findViewById(R.id.button4);
        nuevo.setOnClickListener(this);
        conv = (ScrollView) findViewById(R.id.conv);
        dim = conv.getLayoutParams().height;
        mensaje.setOnFocusChangeListener(this);
        ppal = (RelativeLayout) findViewById(R.id.activity_principal);
        mensaje.setOnKeyListener(this);
    }

    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    public void processReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        Object[] objArr = (Object[])bundle.get("pdus");
        String sms = "";
        for (int i = 0; i < objArr.length; i++) {
            SmsMessage smsMsg = SmsMessage.createFromPdu((byte[])objArr[i]);
            String texto = smsMsg.getMessageBody();
            String remitente = smsMsg.getDisplayOriginatingAddress();
            String n ="+58"+numero.getText().toString().substring(1,numero.getText().toString().length());
            if(remitente.compareTo(n) == 0) {//Si es a la persona a la que le envio el mensaje
                    conversacion.setText(conversacion.getText().toString()+"\n\t\t\t\t\t\t\t\t\t\t\t\t"+n+":\n\t\t\t\t\t\t\t\t\t\t\t\t"+texto+"\n");
            }
            historial.setText(historial.getText().toString()+"\n\t\t\t\t\t\t\t\t\t\t\t\t"+n+":\n\t\t\t\t\t\t\t\t\t\t\t\t"+texto+"\n");
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == enviar.getId()) {
            if(numero.getText().toString().length()>0 && mensaje.getText().toString().length()>0) {
                ctrl.EnviarMensaje(numero.getText().toString(),mensaje.getText().toString());
                conversacion.setText(conversacion.getText().toString()+"Yo: "+mensaje.getText().toString());
                conv.getLayoutParams().height = dim;
            }
        }
        if(v.getId() == nuevo.getId()) {
            Intent intent = new Intent(Principal.this, registroContacto.class);
            startActivityForResult(intent, REQUEST_CODE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            String result = data.getStringExtra("result");
            if (result.compareTo("refrescar") == 0) {
                fragment = contactFragment.newInstance();
                getSupportFragmentManager().beginTransaction().add(R.id.contactContainer, fragment).commit();
            }
        }
    }


    @Override
    public void onFocusChange(View view, boolean b) {
        if (view.getId() == mensaje.getId()) {
            if(b) {
                conv.getLayoutParams().height = 50;
            }
            else {
                conv.getLayoutParams().height = dim;
            }
        }
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if(view.getId() == mensaje.getId()) {
            if(i == 66) {
                conv.getLayoutParams().height = dim;
            }
        }

        return false;
    }
}
