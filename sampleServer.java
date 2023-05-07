import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class sampleServer {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;


    public void start(int port)throws Exception{
         serverSocket =new ServerSocket(port);
         clientSocket = serverSocket.accept();
         out = new PrintWriter(clientSocket.getOutputStream(),true);
         in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
         String inputLine;
         while((inputLine =in.readLine())!=null){
             if(".".equals(inputLine)){
                 out.println("good bye");
                 stop();
                 break;
             }
             out.println("server got msg:"+inputLine);
         }







    }

    public void stop() throws Exception{
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();



    }


    public static void main(String[] args) {
        try{
            sampleServer server =new sampleServer();
            server.start(6666);

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
