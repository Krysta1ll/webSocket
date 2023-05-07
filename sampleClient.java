import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class sampleClient {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;


    public void startConnection(String ip, int port) throws Exception {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String sendMessage(String msg) throws Exception{
        out.println(msg);
        String resp = in.readLine();
        return resp;

    }

    public void stopConnection(){
        try{
            in.close();
            out.close();
            clientSocket.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }


    }

    public static void main(String[] args) {
        try{
            sampleClient client =new sampleClient();
            client.startConnection("127.0.0.1",6666);
            Scanner sc =new Scanner(System.in);
            while(true){
                String response = client.sendMessage(sc.nextLine());
                System.out.println(response);
                if(response.equals("bye")){
                    client.stopConnection();
                    break;
                }
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
