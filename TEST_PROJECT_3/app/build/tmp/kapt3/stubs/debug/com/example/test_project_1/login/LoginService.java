package com.example.test_project_1.login;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u0006H\'\u00a8\u0006\b"}, d2 = {"Lcom/example/test_project_1/login/LoginService;", "", "requestLogin", "Lretrofit2/Call;", "Lcom/example/test_project_1/login/Login;", "userid", "", "userpw", "app_debug"})
public abstract interface LoginService {
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "/login/")
    @retrofit2.http.FormUrlEncoded()
    public abstract retrofit2.Call<com.example.test_project_1.login.Login> requestLogin(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Field(value = "userid")
    java.lang.String userid, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Field(value = "userpw")
    java.lang.String userpw);
}