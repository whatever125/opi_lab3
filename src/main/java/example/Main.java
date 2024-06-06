package example;

import example.mbeans.NumberOfPoints;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class Main {
    public static void main(String[] args) throws NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, MalformedObjectNameException {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("example.mbeans:type=NumberOfPoints");
        NumberOfPoints mbean = new NumberOfPoints();
        mbs.registerMBean(mbean, name);

        System.out.println("Successfully launched Main!");
    }
}
