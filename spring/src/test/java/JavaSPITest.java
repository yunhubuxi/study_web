import java.util.ServiceLoader;

public class JavaSPITest {

    public static void main(String[] args) throws Exception{
        new JavaSPITest().sayHello();
    }
    public void sayHello() throws Exception {
        ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
        System.out.println("Java SPI");
        serviceLoader.forEach(Robot::sayHello);
    }
}
