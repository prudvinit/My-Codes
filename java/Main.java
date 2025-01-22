public class Main {
    public static void main(String[] args){
        String s = "a";
        for(int i=0;i<10000;i++){
            s = s;
//            System.out.println("Hi "+s);
//            Thread.sleep(200);
        }
    }
}
