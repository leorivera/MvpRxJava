package com.enavarrodev.mvprxjava.api.services;

import com.activeandroid.Model;
import com.enavarrodev.mvprxjava.BuildConfig;
import com.enavarrodev.mvprxjava.api.utils.HeaderInterceptor;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by enava on 12/2/2018.
 * <p>
 * Clase base de la cual han de extender todas aquellas que deseen ser cliente de un servio REST.
 * Esta clase se encarga bue parte de la logica de configuracion y posee metodos convenientes
 * para customizar el cliente que se desee crear
 *
 * @param <T> El tipo de la interface que contiene los servicios que ha de proveer la API Rest
 */

public abstract class AbstractRestService<T> {
    private T service;
    private Gson gson;
    private Retrofit mRetrofit = null;
    GsonConverterFactory factory = GsonConverterFactory.create(new GsonBuilder().create());
    OkHttpClient.Builder client;

    AbstractRestService(){
        //createGson();
    }

    /**
     * Se encarga de crear un objeto {@link Gson} con la configuracion necesaria para el proyecto
     */

    /*private void createGson(){
        GsonBuilder gsonBuilder = Utils.createGsonBuilder();
        final Map<Type, Object> typeAdapters = getTypeAdapters();
        final Set<Type> types = typeAdapters.keySet();
        for (Type type : types) {
            gsonBuilder.registerTypeAdapter(type, typeAdapters.get(type));
        }

        gson = gsonBuilder.create();
    }*/

    /**
     * Crea una instancia del cliente Rest con todas las configuraciones asignadas
     *
     * @param restApiDefinitionInterface interface con la definicion de los servicios que a de aproveer la API Rest
     * @param endPoint                   URL del andpount base del cual salen los servicios que provee la API Rest
     * @param <T>                        El tipo de la interface que contiene los servicios que ha de proveer la API Rest
     * @return Instancia del cliente Rest
     */
    private <T> T createService(Class<T> restApiDefinitionInterface, String endPoint, Interceptor interceptor) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(endPoint)
                .client(getOkHttpClient(interceptor))
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return mRetrofit.create(restApiDefinitionInterface);
    }

    /**
     * @return Configura y devuelve un objeto {@link OkHttpClient} configurado
     */
    private OkHttpClient getOkHttpClient(Interceptor interceptor) {
        client = new OkHttpClient().newBuilder();
        client.connectTimeout(1200, TimeUnit.SECONDS);
        client.readTimeout(1200, TimeUnit.SECONDS);
        client.writeTimeout(1200, TimeUnit.SECONDS);
        client.addInterceptor(interceptor);
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        if (BuildConfig.DEBUG) {
            // development build
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            // production build
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        }

        client.addInterceptor(logging);
        return client.build();
    }

    /**
     * Crea y retorna una instancia del servicio cliente definido por que devuelve {@link #getRestApiDefinitionInterface()}
     *
     * @return na instancia del servicio cliente
     */
    public T getService() {
        if (service == null)
            service = createService(getRestApiDefinitionInterface(), getServiceEndPoint(), getRequestInterceptor());
        return service;
    }

    /**
     * Crea y retorna una instancia del servicio cliente definido por que devuelve {@link #getRestApiDefinitionInterface()}
     *
     * @return na instancia del servicio cliente
     */

    public T getService(Interceptor interceptor) {
        if (service == null)
            service = createService(getRestApiDefinitionInterface(), getServiceEndPoint(), interceptor);
        return service;
    }

    /**
     * Permite definir y devolver un {@link Interceptor} para cosas como agregar parametros al Header de una consulta a la API.
     * Es utilizado para crear la instancia del servicio cliente. Por defecto devuelve una instancia de {@link HeaderInterceptor}.
     *
     * @return instancia de {@link HeaderInterceptor}
     */
    protected Interceptor getRequestInterceptor() {
        return new HeaderInterceptor();
    }

    /**
     * @return Objeto {@link Class} de la interface que contiene la definicion de los servicios que ha de proveer la API Rest.
     * Es necesaria para crear una instancia del servicio rest que dicha interface representa
     */
    protected abstract Class<T> getRestApiDefinitionInterface();

    /**
     * @return String con el endpoint donde se encuentra el servicio Rest en el servidor
     */
    protected abstract String getServiceEndPoint();


    /**
     * Permite definir y devolver un {@link Map} de [{@link Type}, {@link com.google.gson.TypeAdapter}]  para configurar el servicio cliente.
     * Por default es un mapa vacio.
     *
     * @return Mapa con los {@link com.google.gson.TypeAdapter}
     */
    protected Map<Type, Object> getTypeAdapters() {
        return new HashMap<>();
    }
}
