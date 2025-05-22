package com.example.apiasistencia.resources;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.example.apiasistencia.models.Asistencia;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.*;
import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvException;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

public class FirestoreCRUD {
     public final Firestore db;

    public FirestoreCRUD() {
        this.db = new FirestoreInitializer().initialize();
    }
}

