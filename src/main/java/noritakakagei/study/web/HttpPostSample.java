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

        // POSTリクエストに含めるボディを定義（ここではJSON形式の例）
        String json = "{\"name\":\"John Doe\", \"age\":30}";

        // HttpClientのインスタンスを作成
        HttpClient client = HttpClient.newHttpClient();

        // HttpRequestのインスタンスを作成
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("<url>")) // ここにリクエストしたいURLを指定
                .header("Content-Type", "application/json") // コンテントタイプを指定
                .POST(HttpRequest.BodyPublishers.ofString(json)) // HTTPメソッドをPOSTに指定し、ボディをセット
                .build();

        // リクエストを送信し、レスポンスを受け取る
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // レスポンスボディを出力
        System.out.println("Response status code: " + response.statusCode());
        System.out.println("Response body: " + response.body());
    }
}
