package luistorm.curso;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class Principal extends AppCompatActivity implements View.OnClickListener{

    private TabHost tbH;
    private Button b,b2;
    private EditText numero,mensaje;
    private Mensajes varMensajes;
    private TextView conversacion;
    private BroadcastReceiver receiver;
    protected static final int REQUEST_CODE = 10;
    private Fragment cF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        tbH = (TabHost) findViewById(R.id.tabHost);
        tbH.setup();
        TabHost.TabSpec tb1 = tbH.newTabSpec("tab1");
        TabHost.TabSpec tb2 = tbH.newTabSpec("tab2");
        TabHost.TabSpec tb3 = tbH.newTabSpec("tab3");
        tb1.setIndicator("Enviar");
        tb2.setIndicator("Contactos");
        tb3.setIndicator("Historial");
        tb1.setContent(R.id.tab1);
        tb2.setContent(R.id.tab2);
        tb3.setContent(R.id.tab3);
        tbH.addTab(tb1);
        tbH.addTab(tb2);
        tbH.addTab(tb3);
        b = (Button) findViewById(R.id.button2);
        b.setOnClickListener(this);
        numero = (EditText) findViewById(R.id.editText2);
        mensaje = (EditText) findViewById(R.id.editText4);
        varMensajes = new Mensajes(this);
        conversacion = (TextView) findViewById(R.id.conversacion);
        IntentFilter filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                processReceive(context, intent);
            }
        };
        registerReceiver(receiver,filter);
        b2 = (Button) findViewById(R.id.button3);
        b2.setOnClickListener(this);
        contactosFragment ContactosFragment = (contactosFragment)
                getSupportFragmentManager().findFragmentById(R.id.contenedor_contactos);

        if (ContactosFragment == null) {
            ContactosFragment = contactosFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contenedor_contactos, ContactosFragment)
                    .commit();
        }

        cF = (contactosFragment) getSupportFragmentManager().
                findFragmentById(R.id.contenedor_contactos);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == b.getId()) {
            varMensajes.EnviarMensaje(mensaje.getText().toString(),numero.getText().toString());
            conversacion.setText(conversacion.getText().toString()+"\n\t\t\t\t\tYo:\n\t\t\t\t\t"+mensaje.getText().toString());
        }
        if(view.getId() == b2.getId()) {
            Intent intent = new Intent(Principal.this,RegistroContactos.class);
            //startActivity(intent);
            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    public void processReceive(Context context,Intent intent) {
        Bundle bundle = intent.getExtras();
        Object[] objArr = (Object[])bundle.get("pdus");
        String remitente = "";
        String cuerpo = "";
        String numeroComparar = numero.getText().toString();
        numeroComparar = "+58"+numeroComparar.substring(1);
        for (int i = 0; i < objArr.length; i++) {
            SmsMessage mensaje = SmsMessage.createFromPdu((byte[])objArr[i]);
            remitente = mensaje.getOriginatingAddress();
            cuerpo = mensaje.getMessageBody();
            if (remitente.compareTo(numeroComparar) == 0) {
                conversacion.setText(conversacion.getText().toString()+"\n"+remitente+":\n"+cuerpo);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            String result = data.getStringExtra("result");
            if (result.compareTo("refrescar") == 0) {
                cF = contactosFragment.newInstance();
                getSupportFragmentManager().beginTransaction().add(R.id.contenedor_contactos, cF).commit();
            }
        }
    }

    /*
    * Bundle bundle = intent.getExtras();
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
    * */
}





















