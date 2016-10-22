package com.jpeng.demo.picasso;


import android.content.Context;
import android.support.annotation.WorkerThread;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import okhttp3.*;
import okio.*;

import java.io.IOException;

/**
 * Created by axing on 16/7/2.
 */
public class SquareUtils {

    static private OkHttpClient client;

    static public synchronized OkHttpClient getClient() {
        if (client == null) {
            client = new OkHttpClient.Builder()
                    //Interceptor -> cache -> NetworkInterceptor
                    //.addNetworkInterceptor(getLogger())
                    //.dispatcher(getDispatcher())
                    //.dns(HTTP_DNS)
                    .build();
        }
        return client;
    }

    /**
     * Not singleton
     */
    private static OkHttpClient getProgressBarClient(final ProgressListener listener) {
        return getClient().newBuilder().addNetworkInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Response originalResponse = chain.proceed(chain.request());
                return originalResponse.newBuilder()
                        .body(new ProgressResponseBody(originalResponse.body(), listener))
                        .build();
            }
        }).build();
    }

    /**
     * Download Big Image only, Not singleton but shared cache
     */
    static public Picasso getPicasso(Context context, ProgressListener listener) {
        OkHttpClient client = getProgressBarClient(listener);
        OkHttp3Downloader downloader = new OkHttp3Downloader(client);
        return new Picasso.Builder(context).downloader(downloader)
                .memoryCache(com.squareup.picasso.Cache.NONE)
                .build();
    }

    public interface ProgressListener {
        @WorkerThread
        void update(long current, long total);
    }

    private static class ProgressResponseBody extends ResponseBody {

        private final ResponseBody responseBody;
        private final ProgressListener progressListener;
        private BufferedSource bufferedSource;

        public ProgressResponseBody(ResponseBody responseBody, ProgressListener progressListener) {
            this.responseBody = responseBody;
            this.progressListener = progressListener;
        }

        @Override
        public MediaType contentType() {
            return responseBody.contentType();
        }

        @Override
        public long contentLength() {
            return responseBody.contentLength();
        }

        @Override
        public BufferedSource source() {
            if (bufferedSource == null) {
                bufferedSource = Okio.buffer(source(responseBody.source()));
            }
            return bufferedSource;
        }

        private Source source(Source source) {

            return new ForwardingSource(source) {
                long totalBytesRead = 0L;

                @Override
                public long read(Buffer sink, long byteCount) throws IOException {
                    long bytesRead = super.read(sink, byteCount);
                    // read() returns the number of bytes read, or -1 if this source is exhausted.
                    totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                    if (progressListener != null) {
                        progressListener.update(totalBytesRead,responseBody.contentLength());
                    }
                    return bytesRead;
                }
            };
        }
    }
}