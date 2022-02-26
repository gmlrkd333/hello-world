package com.example.test_project_1;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J#\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0010\u001a\u00020\nH\u0002\u00a2\u0006\u0002\u0010\u0011J\u0012\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014R\u0019\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\t\u001a\u00020\nX\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0016"}, d2 = {"Lcom/example/test_project_1/LoginPage;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "PERMISSIONS", "", "", "getPERMISSIONS", "()[Ljava/lang/String;", "[Ljava/lang/String;", "PERMISSIONS_REQUEST", "", "getPERMISSIONS_REQUEST", "()I", "checkPermissions", "", "permissions", "permissionsRequest", "([Ljava/lang/String;I)Z", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
public final class LoginPage extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String[] PERMISSIONS = {"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"};
    private final int PERMISSIONS_REQUEST = 100;
    
    public LoginPage() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String[] getPERMISSIONS() {
        return null;
    }
    
    public final int getPERMISSIONS_REQUEST() {
        return 0;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final boolean checkPermissions(java.lang.String[] permissions, int permissionsRequest) {
        return false;
    }
}