package com.example.loudalarm.AuthController;

import android.net.Uri;

import com.example.loudalarm.App;
import com.example.loudalarm.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;

public class DBController {
    private final FirebaseAuth auth;

    FirebaseFirestore firebase;
    StorageReference storage;

    DatabaseReference database;

    public DBController() {
        this.storage = FirebaseStorage.getInstance("gs://loudalarm-cc8d4.appspot.com").getReference();
        this.auth = FirebaseAuth.getInstance();
        this.database = FirebaseDatabase.getInstance("https://loudalarm-cc8d4-default-rtdb.europe-west1.firebasedatabase.app").getReference();
    }

    public boolean isAuth() {
        return auth.getCurrentUser() != null;
    }

    public FirebaseUser getUser() {
        return auth.getCurrentUser();
    }


    public void getAlarmsFromDb(OnCompleteListener<DataSnapshot> listener) {
        if (isAuth())
            database.child("alarmEntities").child(getUser().getUid()).get().addOnCompleteListener(listener);
    }

    public void getUserFromDb(OnCompleteListener<DataSnapshot> listener) {
        if (isAuth())
            database.child("users")
                    .child(getUser().getUid())
                    .get().addOnCompleteListener(listener);
    }

    public void clearAlarmsDB() {
        if (isAuth()) database.child("alarmEntities").child(getUser().getUid()).removeValue();
    }

    public void registerUser(String email, String password, OnCompleteListener listener) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(listener);

    }

    public void addUserToDb(String email, String name, OnCompleteListener listener) {
        User user = new User(email, name);
        if (isAuth()) {
            database.child("users").child(getUser().getUid()).setValue(user).addOnCompleteListener(listener);
        }
    }

    public void addAlarmsToDb(OnSuccessListener<Void> listener) {
        if (isAuth()) {
            new Thread(() -> {
                database.child("alarmEntities").child(getUser().getUid()).setValue(App.getDatabase().alarmDAO().getAll())
                        .addOnSuccessListener(listener);
            }).start();
        }
    }

    public void addAlarmsToDb() {

        if (isAuth()) {
            new Thread(() -> {
                database.child("alarmEntities").child(getUser().getUid()).setValue(App.getDatabase().alarmDAO().getAll());
            }).start();
        }
    }

    public void updateIcon(Uri uri) {
        if (isAuth()) {
            database.child("users").child(getUser().getUid()).child("url").setValue(uri.toString());
            getUser().updateProfile(new UserProfileChangeRequest.Builder()
                    .setDisplayName(uri.toString())
                    .build()
            );
            storage.child("images/" + getUser().getUid()).putFile(uri);

        }

    }

    public void updateName(String name) {
        if (isAuth()) {
            getUser().updateProfile(new UserProfileChangeRequest.Builder()
                    .setDisplayName(name)
                    .build()
            );
            database.child("users").child(getUser().getUid()).child("nickname").setValue(name);

        }


    }

    public void sendMailWithNewPassword(String email, OnCompleteListener<Void> listener) {
        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.sendPasswordResetEmail(email).addOnCompleteListener(listener);
    }

    public void enterUser(String email, String password, OnCompleteListener<AuthResult> listener) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(listener);
    }

    public void singOut() {
        auth.signOut();
    }


    public FirebaseAuth getAuth() {
        return auth;
    }

    public FirebaseFirestore getFirebase() {
        return firebase;
    }

    public void setFirebase(FirebaseFirestore firebase) {
        this.firebase = firebase;
    }

    public StorageReference getStorage() {
        return storage;
    }

    public void setStorage(StorageReference storage) {
        this.storage = storage;
    }

    public DatabaseReference getDatabase() {
        return database;
    }

    public void setDatabase(DatabaseReference database) {
        this.database = database;
    }

    public void addIconToDb() {
        File file = new File(storage.child("kitty.png").getPath());
        storage.child("image").child(getUser().getUid()).putFile(Uri.fromFile(file)).addOnSuccessListener(taskSnapshot -> {

        });

    }
}
