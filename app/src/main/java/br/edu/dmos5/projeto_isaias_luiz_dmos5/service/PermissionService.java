package br.edu.dmos5.projeto_isaias_luiz_dmos5.service;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import br.edu.dmos5.projeto_isaias_luiz_dmos5.R;

public class PermissionService {

    public static final int REQUEST_PERMISSION = 64;

    private Activity activity;

    public PermissionService(Activity activity) {
        this.activity = activity;
    }

    public void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.INTERNET)) {
            new AlertDialog.Builder(activity)
                    .setMessage(R.string.request_internet_msg)
                    .setPositiveButton(R.string.yes_msg, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.INTERNET}, REQUEST_PERMISSION);
                        }
                    })
                    .setNegativeButton(R.string.not_msg, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    })
                    .show();
        } else {
            ActivityCompat.requestPermissions(
                    activity,
                    new String[]{
                            Manifest.permission.INTERNET
                    },
                    REQUEST_PERMISSION);
        }
    }

    public boolean hasPermission() {
        return ContextCompat.checkSelfPermission(activity, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED;
    }

}
