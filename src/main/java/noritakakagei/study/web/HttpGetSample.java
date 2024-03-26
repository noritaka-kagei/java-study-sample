package noritakakagei.study.web;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.net.URISyntaxException;

public class HttpGetSample {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        // HttpClientのインスタンスを作成
        HttpClient client = HttpClient.newHttpClient();

        // HttpRequestのインスタンスを作成
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://gihyo.jp/book/2014/978-4-7741-6685-8")) // ここにリクエストしたいURLを指定
                .GET() // HTTP GETリクエストを指定
                .build();

        // リクエストを送信し、レスポンスを受け取る
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // レスポンスボディを出力
        System.out.println(response.body());
    }
}