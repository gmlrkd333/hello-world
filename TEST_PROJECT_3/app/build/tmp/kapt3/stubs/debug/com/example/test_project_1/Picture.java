package com.example.test_project_1;

import java.lang.System;

@kotlin.Metadata(mv = {1, 5, 1}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'\u00a8\u0006\u0007"}, d2 = {"Lcom/example/test_project_1/Picture;", "", "requestPicture", "Lretrofit2/Call;", "Lcom/example/test_project_1/Cal;", "image", "Lokhttp3/MultipartBody$Part;", "app_debug"})
public abstract interface Picture {
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.POST(value = "/calculate/")
    @retrofit2.http.Multipart()
    public abstract retrofit2.Call<com.example.test_project_1.Cal> requestPicture(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Part()
    okhttp3.MultipartBody.Part image);
}