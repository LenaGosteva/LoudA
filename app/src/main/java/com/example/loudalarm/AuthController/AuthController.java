package com.example.loudalarm.AuthController;

import android.net.Uri;
import android.util.Log;

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

import java.util.concurrent.atomic.AtomicReference;

public class AuthController {
    private final FirebaseAuth auth;

    FirebaseFirestore firebase;
    DatabaseReference database;

    public AuthController() {
        this.auth = FirebaseAuth.getInstance();
        this.database = FirebaseDatabase.getInstance("https://loudalarm-cc8d4-default-rtdb.europe-west1.firebasedatabase.app").getReference();
    }

    public boolean isAuth() {
        return auth.getCurrentUser() != null;
    }

    public FirebaseUser getUser() {
        return auth.getCurrentUser();
    }

    public User getUserFromDb() {
        AtomicReference<User> user = new AtomicReference<>();
        if (isAuth()) {
            database.child("users").child(getUser().getUid()).get().addOnCompleteListener(task -> {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    user.set(task.getResult().getValue(User.class));
                }
            });
        }
        return user.get();

    }

    public void getAlarmsFromDb(OnCompleteListener<DataSnapshot> listener) {
        if (isAuth())
            database.child("alarmEntities").child(getUser().getUid()).get().addOnCompleteListener(listener);
    }

    public void clearDb() {
        if (isAuth()) database.child("alarmEntities").child(getUser().getUid()).removeValue();
    }

    public void registerUser(String email, String password, OnSuccessListener<AuthResult> listener) {
        auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(listener);

    }

    public void addUserToDb(String email, String name, OnSuccessListener<Void> listener) {
        User user = new User(email, name);
        if (isAuth()) {
            database.child("users").child(getUser().getUid()).setValue(user).addOnSuccessListener(listener);
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
        if (isAuth())
            getUser().updateProfile(new UserProfileChangeRequest.Builder()
                    .setPhotoUri(uri).build());
    }

    public void updateName(String name) {
        if (isAuth())
            getUser().updateProfile(new UserProfileChangeRequest.Builder()
                    .setDisplayName(name)
                    .build()
            );

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


}
