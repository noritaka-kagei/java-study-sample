package noritakakagei.study.web;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;
import java.net.URISyntaxException;

public class HttpPostSample {
    public static void main(String[] args)
            throws IOException, InterruptedException, URISyntaxException {

        // POSTリクエストに含める本文を定義（ここではJSON形式の例）
        String json = "{\"name\":\"John Doe\", \"age\":30}";

        // HttpClientのインスタンスを作成
        HttpClient client = HttpClient.newHttpClient();

        // HttpRequestのインスタンスを作成
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.hoge.com/users/{user}")) // ここにリクエストしたいURLを指定
                .header("Content-Type", "application/json") // データフォーマットを指定
                .POST(HttpRequest.BodyPublishers.ofString(json)) // HTTPメソッドをPOSTに指定し、本文をセット
                .build();

        // リクエストを送信し、レスポンスを文字列として受け取る
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // レスポンスの本文を出力
        System.out.println("Response status code: " + response.statusCode());
        System.out.println("Response body: " + response.body());
    }
}
