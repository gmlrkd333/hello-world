package com.example.test_project_1.join;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J@\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u0007\u001a\u00020\u00062\b\b\u0001\u0010\b\u001a\u00020\t2\b\b\u0001\u0010\n\u001a\u00020\u00062\b\b\u0001\u0010\u000b\u001a\u00020\tH\'\u00a8\u0006\f"}, d2 = {"Lcom/example/test_project_1/join/JoinService;", "", "requestJoin", "Lretrofit2/Call;", "Lcom/example/test_project_1/login/Login;", "username", "", "password", "age", "", "sex", "weight", "app_debug"})
public abstract interface JoinService {
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "/join/")
    @retrofit2.http.FormUrlEncoded()
    public abstract retrofit2.Call<com.example.test_project_1.login.Login> requestJoin(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Field(value = "username")
    java.lang.String username, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Field(value = "password")
    java.lang.String password, @retrofit2.http.Field(value = "age")
    int age, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Field(value = "sex")
    java.lang.String sex, @retrofit2.http.Field(value = "weight")
    int weight);
}