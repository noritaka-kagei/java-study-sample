package noritakakagei.study.web;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import java.io.IOException;
import java.net.URISyntaxException;

public class HttpGetNonBlockingSample {
    public static void main(String[] args) 
            throws URISyntaxException, IOException, InterruptedException {
        
        // HttpRequestのインスタンスを作成
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.github.com/users/noritaka-kagei")) // ここにリクエストしたいURLを指定
                .GET() // HTTPメソッドをGETに指定(省略可)
                .build();

        // HttpClientのインスタンスを作成
        HttpClient client = HttpClient.newHttpClient();

        // リクエストを送信し、レスポンスを文字列として受け取る
        CompletableFuture<HttpResponse<String>> responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        // レスポンスを受け取るまでの時間で何らかの処理を実行

        // レスポンスのボディを出力
        responseFuture.thenApply(HttpResponse::body)
                      .thenAccept(System.out::println)
                      .join();
    }
}
