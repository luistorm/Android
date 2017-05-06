package luistorm.curso;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;

/**
 * Created by Luis Torres on 29/04/2017.
 */

public class Mensajes {
    private SmsManager manager;
    private PendingIntent intentEnvio;
    private Context contexto;

    public Mensajes(Context contexto) {
        this.contexto = contexto;
        manager = SmsManager.getDefault();
    }

    public void EnviarMensaje(String mensaje,String numero) {
        PendingIntent sentIntent = PendingIntent.getBroadcast(contexto, 0, new Intent("SMS_SENT"), 0);
        manager.sendTextMessage(numero,null,mensaje,sentIntent,null);

    }
}























/*Mensajes {
private SmsManager manager;
private PendingIntent sentIntent;
private Context ctx;
public Mensajes(Context ctx) {
        manager = SmsManager.getDefault();
        this.ctx = ctx;
        }
public void EnviarMensaje(String Numero,String Msj) {
        PendingIntent sentIntent = PendingIntent.getBroadcast(ctx, 0, new Intent("SMS_SENT"), 0);

        ctx.registerReceiver(new BroadcastReceiver() {

@Override
public void onReceive(Context context, Intent intent) {
        switch (getResultCode()){
        case Activity.RESULT_OK:
        Toast.makeText(ctx, "Mensaje enviado con exito", Toast.LENGTH_SHORT).show();
        break;
        case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
        Toast.makeText(ctx, "No se pudo enviar SMS", Toast.LENGTH_SHORT).show();
        break;
        case SmsManager.RESULT_ERROR_NO_SERVICE:
        Toast.makeText(ctx, "Servicio no diponible", Toast.LENGTH_SHORT).show();
        break;
default:
        Toast.makeText(ctx,"Error inesperado",Toast.LENGTH_LONG).show();
        break;
        }
        }
        }, new IntentFilter("SMS_SENT"));

        manager.sendTextMessage( Numero.toString() , null, Msj.toString() , sentIntent, null);
        }
        }
*/