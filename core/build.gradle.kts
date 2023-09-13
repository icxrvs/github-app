plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies{

    //Okhttp
    api(platform("com.squareup.okhttp3:okhttp-bom:4.10.0"))
    api("com.squareup.okhttp3:okhttp")
    api("com.squareup.okhttp3:logging-interceptor")

    //Retrofit
    api("com.squareup.retrofit2:retrofit:2.9.0")
    api("com.squareup.retrofit2:converter-gson:2.9.0")

    //Gson
    api("com.google.code.gson:gson:2.10.1")

    //Paging
    implementation("androidx.paging:paging-runtime:3.2.1")
    implementation("androidx.paging:paging-common-ktx:3.2.1")

    //Javax Inject
    implementation("javax.inject:javax.inject:1")

    //Coroutines core
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")



}