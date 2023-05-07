public class main {
    public static void main(String[] args) {
        try {
            new multiServer().start(6666);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
