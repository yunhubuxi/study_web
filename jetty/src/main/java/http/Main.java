package http;

/**
 * 启动HTTP服务
 */
public class Main {
    public static void main(String[] args) {
        try {
            HTTPService httpService = new HTTPService();
            int httpPort = Integer.valueOf(8080);
            httpService.initServer(httpPort);
        } catch (Exception e) {
            System.out.println("启动错误:" + e.getMessage());
        }
    }
}
