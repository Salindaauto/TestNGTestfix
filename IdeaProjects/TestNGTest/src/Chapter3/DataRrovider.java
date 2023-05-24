// Data provider for class


package Chapter3;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class DataRrovider {



    @DataProvider(name = "usernameProvider")
    public static Object [] [] logInData ()
    {
        Object [][] data = new Object [2][3];

        data [0][0] = "TestNG@Framework.com";		data [0][1] = "TestNG1234";		data [0][2] = false;
        data [1][0] = "salindame";			data [1][1] = "Sunday123";		data [1][2] = true;
        //data [2][0] = "Test@AutomationU.com";		data [2][1] = "TAU1234";		data [2][2] = true;

        return data;
    }
}
