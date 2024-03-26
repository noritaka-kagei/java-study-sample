package noritakakagei.study.web;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.net.URISyntaxException;

public class HttpGetSample {
    public static void main(String[] args) 
            throws URISyntaxException, IOException, InterruptedException {
        
        // HttpRequestのインスタンスを作成
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://api.github.com/users/noritaka-kagei")) // ここにリクエストしたいURLを指定
                .GET() // HTTPメソッドをGETに指定(省略可)
                .build();

        // HttpClientのインスタンスを作成
        HttpClient client = HttpClient.newHttpClient();

        // リクエストを送信し、レスポンスを受け取る
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // レスポンスのステータスコードとボディを出力
        System.out.println("Response status code: " + response.statusCode());
        System.out.println("Response body: " + response.body());
    }
}