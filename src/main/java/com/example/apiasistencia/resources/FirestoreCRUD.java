package com.example.apiasistencia.resources;
import com.google.cloud.firestore.*;


public class FirestoreCRUD {
     public final Firestore db;

    public FirestoreCRUD() {
        this.db = new FirestoreInitializer().initialize();
    }
}

